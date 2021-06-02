package com.cheny.springbootseckill.controller;

import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.rabbitmq.MQSender;
import com.cheny.springbootseckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MQSender mqSender;

    @ResponseBody
    @RequestMapping("info")
    public RespBean info(User user) {
        return RespBean.success(user);
    }


//    @ResponseBody
//    @RequestMapping("/mq")
//    public void mq() {
//        mqSender.send("hello");
//    }
//    @ResponseBody
//    @RequestMapping("/mq/fanout")
//    public void mq01() {
//        mqSender.send("hello");
//    }
}
