package com.cheny.springbootseckill.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SeckillMessage
 * @Description 秒杀信息
 * @Author cheny
 * @Date 2021/5/17 19:45
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillMessage {

    private User user;

    private Long goodsId;

}
