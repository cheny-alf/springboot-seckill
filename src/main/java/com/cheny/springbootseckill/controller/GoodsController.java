package com.cheny.springbootseckill.controller;

import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.service.IGoodsService;
import com.cheny.springbootseckill.service.impl.UserServiceImpl;
import com.cheny.springbootseckill.vo.GoodsVo;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("/toList")
    public String toList(Model model, User user) {
//        if(StringUtils.isEmpty(ticket)){
//            return "login";
//        }
////        User user = (User) session.getAttribute(ticket);
//        User user = userService.getUserByCookie(ticket,request,response);
//        System.out.println(user);
//        if(null == user){
//            return "login";
//        }
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        model.addAttribute("user", user);
        return "goodsList";
    }

    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId) {
        model.addAttribute("user", user);
        GoodsVo goodsVoByGoodsId = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVoByGoodsId.getStartDate();
        Date endDate = goodsVoByGoodsId.getEndDate();
        Date nowDate = new Date();
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(nowDate);
        int remainSeconds = 0;
        int seckillStatus = 0;
        if (nowDate.before(startDate)) {
            remainSeconds = ((int) (startDate.getTime() - nowDate.getTime()) / 1000);
            seckillStatus = 0;
        } else if (nowDate.after(endDate)) {
            remainSeconds = -1;
            seckillStatus = 2;
        } else {
            remainSeconds = 0;
            seckillStatus = 1;
        }
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("goods", goodsVoByGoodsId);
        model.addAttribute("seckillStatus", seckillStatus);
        return "goodsDetail";
    }


}
