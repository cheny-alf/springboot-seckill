package com.cheny.springbootseckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheny.springbootseckill.pojo.Goods;
import com.cheny.springbootseckill.vo.GoodsVo;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cheny
 * @since 2021-05-13
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
