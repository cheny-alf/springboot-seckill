package com.cheny.springbootseckill.vo;

import com.cheny.springbootseckill.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName OrderDetailVo
 * @Description 下单对象
 * @Author cheny
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVo {

    private Order order;
    private GoodsVo goodsVo;

}
