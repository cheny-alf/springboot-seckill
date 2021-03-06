package com.cheny.springbootseckill.rabbitmq;

import com.cheny.springbootseckill.pojo.SeckillMessage;
import com.cheny.springbootseckill.pojo.SeckillOrder;
import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.service.IGoodsService;
import com.cheny.springbootseckill.service.IOrderService;
import com.cheny.springbootseckill.utils.JsonUtil;
import com.cheny.springbootseckill.vo.GoodsVo;
import com.cheny.springbootseckill.vo.RespBean;
import com.cheny.springbootseckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName MQReceiver
 * @Description 消息接收者
 * @Author cheny
 **/
@Service
@Slf4j
public class MQReceiver {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IOrderService orderService;
    /**
     * 下单操作
     */
    @RabbitListener(queues = "seckillQueue")
    public void receive(String message){
        log.info("接收到消息："+message);
        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(message, SeckillMessage.class);
        Long goodsId = seckillMessage.getGoodsId();
        User user = seckillMessage.getUser();
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        if(goodsVo.getStockCount()<1){
            return;
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if (seckillOrder != null) {
            return;
        }
        orderService.seckill(user,goodsVo);
    }

//
//    @RabbitListener(queues = "queue")
//    public void receive(Object msg){
//        log.info("接收消息:"+msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout01")
//    public void receive01(Object msg){
//        log.info("01receive:"+ msg);
//    }
//    @RabbitListener(queues = "queue_fanout02")
//    public void receive02(Object msg){
//        log.info("02receive:"+ msg);
//    }
}
