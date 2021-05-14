package com.cheny.springbootseckill.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.cheny.springbootseckill.validator.IsMobile;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    private String password;
}
