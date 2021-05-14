package com.cheny.springbootseckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.springbootseckill.mapper.SeckillOrderMapper;
import com.cheny.springbootseckill.pojo.SeckillOrder;
import com.cheny.springbootseckill.service.ISeckillOrderService;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cheny
 * @since 2021-05-13
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {

    @Override
    public SeckillOrder getOne(QueryWrapper<Object> eq) {
        return null;
    }
}
