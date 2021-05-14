package com.cheny.springbootseckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cheny.springbootseckill.pojo.Order;
import com.cheny.springbootseckill.pojo.SeckillOrder;
import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.service.*;
import com.cheny.springbootseckill.service.impl.OrderServiceImpl;
import com.cheny.springbootseckill.vo.GoodsVo;
import com.cheny.springbootseckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName seckillController
 * @Description this is a seckillController
 * @Author cheny
 * @Date 2021/5/13 18:54
 * @Version 1.0
 **/

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, User user, Long goodsId) {
        System.out.println("daozhele");
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if (goods.getStockCount() < 1) {
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "seckillFail";
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()).eq("goods_id", goods.getId()));
        System.out.println(seckillOrder);
        if (seckillOrder != null) {
            model.addAttribute("errmsg", RespBeanEnum.REPEAT_ERROR.getMessage());
            return "seckillFail";
        }
        Order order = orderService.seckill(user, goods);
        System.out.println("this is order" + order);
        model.addAttribute("order", order);
        model.addAttribute("goods", goods);
        return "orderDetail";
    }

//    @Controller
//    @RequestMapping("/seckill")
//    public class seckillController {
//
//        @Autowired
//        private IGoodsService goodsService;
//        @Autowired
//        private ISeckillOrderService seckillOrderService;
//        @Autowired
//        private IOrderService orderService;
//
//        @RequestMapping("/doSeckill")
//        public String doSeckill(Model model, User user, int goodsid) {
//            if (user == null) {
//                return "login";
//            }
//            model.addAttribute("user", user);
//            GoodsVo goods = goodsService.findGoodsVoByGoodsId((long) goodsid);
//
//            //判断库存
//            if (goods.getStockCount() < 1) {
//                model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK);
//                return "seckillFail";
//            }
//            //判断是否重复抢购
//            SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<>().eq("user_id", user.getId()).eq("goods_id", goodsid));
//            if (seckillOrder != null) {
//                model.addAttribute("errmsg", RespBeanEnum.REPEAT_ERROR);
//                return "seckillFail";
//            }
//            Order order = orderService.seckill(user, goods);
//            model.addAttribute("order", order);
//            model.addAttribute("goods", goods);
//            return "orderDetail";
//        }
//    }
}
