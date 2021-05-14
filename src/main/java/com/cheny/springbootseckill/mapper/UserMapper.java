package com.cheny.springbootseckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheny.springbootseckill.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cheny
 * @since 2021-05-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
