package com.cheny.springbootseckill.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.springbootseckill.exception.GlobalException;
import com.cheny.springbootseckill.mapper.UserMapper;
import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.service.IUserService;
import com.cheny.springbootseckill.utils.CookieUtil;
import com.cheny.springbootseckill.utils.MD5Util;
import com.cheny.springbootseckill.utils.UUIDUtil;
import com.cheny.springbootseckill.vo.LoginVo;
import com.cheny.springbootseckill.vo.RespBean;
import com.cheny.springbootseckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cheny
 * @since 2021-05-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(LoginVo vo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = vo.getMobile();
        String password = vo.getPassword();
//        if(StringUtils.isEmpty(mobile)){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        if(!ValidatorUtil.isMobile(mobile)){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }
        User user = userMapper.selectById(mobile);
        if(null == user){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        if(!MD5Util.formPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        String ticket = UUIDUtil.uuid();
//        request.getSession().setAttribute(ticket,user);
        redisTemplate.opsForValue().set("user:"+ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);

        return RespBean.success();

    }

    @Override
    public User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response) {
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:"+userTicket);
        if(user!=null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }


}
