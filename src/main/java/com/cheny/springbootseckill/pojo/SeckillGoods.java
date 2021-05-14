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
@TableName("t_seckill_goods")
public class SeckillGoods implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Integer goodsId;

    private BigDecimal seckillPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;


}
