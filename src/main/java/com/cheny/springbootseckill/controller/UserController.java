package com.cheny.springbootseckill.controller;

import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.vo.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cheny
 * @since 2021-05-10
 */
@Controller
@RequestMapping("/cheny/user")
public class UserController {

    @ResponseBody
    @RequestMapping("info")
    public RespBean info(User user){
        return RespBean.success(user);
    }

}
