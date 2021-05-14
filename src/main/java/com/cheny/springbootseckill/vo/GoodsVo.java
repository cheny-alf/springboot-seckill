package com.cheny.springbootseckill.vo;

import com.cheny.springbootseckill.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName GoodsVo
 * @Description 商品返回对象
 * @Author cheny
 * @Date 2021/5/13 11:36
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo {
    private Long id;
    private String goodsName;
    private BigDecimal goods_price;
    private String goodsImg;

    private BigDecimal seckillPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;

}
