package com.cheny.springbootseckill.exception;

import com.cheny.springbootseckill.vo.RespBean;
import com.cheny.springbootseckill.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException{

    private RespBeanEnum respBeanEnum;

}
