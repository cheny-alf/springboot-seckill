package com.cheny.springbootseckill.exception;


import com.baomidou.mybatisplus.extension.api.R;
import com.cheny.springbootseckill.vo.RespBean;
import com.cheny.springbootseckill.vo.RespBeanEnum;
import org.apache.tomcat.jni.BIOCallback;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public RespBean ExceptionHandler(Exception e){
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException)e;
            return RespBean.error(ex.getRespBeanEnum());
        }else if(e instanceof BindException){
            BindException ex = (BindException)e;
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
            respBean.setMessage("参数校验异常："+ ex);
            return respBean;
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }
}
