package com.cheny.springbootseckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.springbootseckill.exception.GlobalException;
import com.cheny.springbootseckill.mapper.OrderMapper;
import com.cheny.springbootseckill.mapper.SeckillOrderMapper;
import com.cheny.springbootseckill.pojo.Order;
import com.cheny.springbootseckill.pojo.SeckillGoods;
import com.cheny.springbootseckill.pojo.SeckillOrder;
import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.service.IGoodsService;
import com.cheny.springbootseckill.service.IOrderService;
import com.cheny.springbootseckill.service.ISeckillGoodsService;
import com.cheny.springbootseckill.service.ISeckillOrderService;
import com.cheny.springbootseckill.vo.GoodsVo;
import com.cheny.springbootseckill.vo.OrderDetailVo;
import com.cheny.springbootseckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Scanner;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cheny
 * @since 2021-05-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 秒杀
     *
     * @param user
     * @param goods
     * @return
     */
    @Override
    @Transactional
    public Order seckill(User user, GoodsVo goods) {
        //秒杀商品表减库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new
                QueryWrapper<SeckillGoods>().eq("goods_id",
                goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        seckillGoodsService.updateById(seckillGoods);
        boolean result = seckillGoodsService.update(new UpdateWrapper<SeckillGoods>()
                .setSql("stock_count = stock_count -1 ")
                .eq("goods_id", goods.getId())
                .gt("stock_count", 0));
        if (!result) {
            return null;
        }
        //生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setId(7L);
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setUserId(user.getId());
        seckillOrder.setGoodsId(goods.getId());
        System.out.println(seckillOrder);
        try {
            int insert = seckillOrderMapper.insert(seckillOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set("order:" + user.getId() + ":" + goods.getId(), seckillOrder);
        return order;
    }

    @Override
    public OrderDetailVo detail(Long orderId) {

        if (orderId == null) {
            throw new GlobalException(RespBeanEnum.ORDER_NOT_EXITS);
        }
        Order order = orderMapper.selectById(orderId);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(order.getGoodsId());
        OrderDetailVo detail = new OrderDetailVo();
        detail.setOrder(order);
        detail.setGoodsVo(goodsVo);
        return detail;
    }
}
