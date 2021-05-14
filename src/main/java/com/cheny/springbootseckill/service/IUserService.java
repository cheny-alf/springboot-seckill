package com.cheny.springbootseckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.vo.LoginVo;
import com.cheny.springbootseckill.vo.RespBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cheny
 * @since 2021-05-10
 */
@Service
public interface IUserService extends IService<User> {

    public RespBean doLogin(LoginVo vo, HttpServletRequest request, HttpServletResponse response);

    public User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);
}
