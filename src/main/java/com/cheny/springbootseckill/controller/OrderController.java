package com.cheny.springbootseckill.controller;

import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.service.IOrderService;
import com.cheny.springbootseckill.vo.OrderDetailVo;
import com.cheny.springbootseckill.vo.RespBean;
import com.cheny.springbootseckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(User user , Long orderId){
        if (user == null){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        OrderDetailVo detail = orderService.detail(orderId);
        return RespBean.success(detail);
    }
}
