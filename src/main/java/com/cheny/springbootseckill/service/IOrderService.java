package com.cheny.springbootseckill.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cheny.springbootseckill.pojo.Order;
import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cheny
 * @since 2021-05-13
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goods);
}
