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
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long vo);
}
