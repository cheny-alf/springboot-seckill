package com.cheny.springbootseckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheny.springbootseckill.pojo.Goods;
import com.cheny.springbootseckill.vo.GoodsVo;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cheny
 * @since 2021-05-13
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long vo);
}
