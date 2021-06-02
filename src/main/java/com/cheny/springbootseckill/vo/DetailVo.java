package com.cheny.springbootseckill.vo;

import com.cheny.springbootseckill.pojo.User;
import lombok.Data;

/**
 * @ClassName DetailVo
 * @Description 详情返回对象
 * @Author cheny
 **/
@Data
public class DetailVo {
    private User user;
    private GoodsVo goodsVo;
    private int secKillStatus;
    private int remainSeconds;
}
