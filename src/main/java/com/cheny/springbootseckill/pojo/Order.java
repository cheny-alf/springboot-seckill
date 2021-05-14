package com.cheny.springbootseckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author cheny
 * @since 2021-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;

    private Long goodsId;

    /**
     * 收货地址
     */
    private Long deliveryAddrId;

    private String goodsName;

    /**
     * 商品数量
     */
    private Integer goodsCount;

    private BigDecimal goodsPrice;

    /**
     * 下单渠道

     */
    private Integer orderChannel;

    /**
     * 订单状态 
     */
    private Integer status;

    /**
     * 创建订单时间
     */
    private Date createDate;

    /**
     * 支付时间
     */
    private Date payDate;


}
