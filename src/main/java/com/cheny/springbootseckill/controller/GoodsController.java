package com.cheny.springbootseckill.controller;

import com.cheny.springbootseckill.pojo.User;
import com.cheny.springbootseckill.service.IGoodsService;
import com.cheny.springbootseckill.service.IUserService;
import com.cheny.springbootseckill.service.impl.UserServiceImpl;
import com.cheny.springbootseckill.vo.DetailVo;
import com.cheny.springbootseckill.vo.GoodsVo;
import com.cheny.springbootseckill.vo.RespBean;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;


    @RequestMapping(value = "/toList", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toList(Model model, User user,
                         HttpServletRequest request, HttpServletResponse response) {
        //Redis中获取页面，如果不为空，直接返回页面
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsList");
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        // return "goodsList";
        //如果为空，手动渲染，存入Redis并返回
        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(),
                model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsList", context);
        if (!org.springframework.util.StringUtils.isEmpty(html)) {
            valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
        }
        return html;

    }


//    @RequestMapping(value = "/toDetail2/{goodsId}", produces = "text/html;charset=utf-8")
//    @ResponseBody
//    public String toDetail2(Model model, User user, @PathVariable Long goodsId,
//                            HttpServletRequest request, HttpServletResponse response) {
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        //Redis中获取页面，如果不为空，直接返回页面
//        String html = (String) valueOperations.get("goodsDetail:" + goodsId);
//        if (!org.springframework.util.StringUtils.isEmpty(html)) {
//            return html;
//        }
//        model.addAttribute("user", user);
//        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
//        Date startDate = goodsVo.getStartDate();
//        Date endDate = goodsVo.getEndDate();
//        Date nowDate = new Date();
//        //秒杀状态
//        int secKillStatus = 0;
//        //秒杀倒计时
//        int remainSeconds = 0;
//        //秒杀还未开始
//        if (nowDate.before(startDate)) {
//            remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime()) / 1000));
//        } else if (nowDate.after(endDate)) {
//            //	秒杀已结束
//            secKillStatus = 2;
//            remainSeconds = -1;
//        } else {
//            //秒杀中
//            secKillStatus = 1;
//            remainSeconds = 0;
//        }
//        model.addAttribute("remainSeconds", remainSeconds);
//        model.addAttribute("secKillStatus", secKillStatus);
//        model.addAttribute("goods", goodsVo);
//        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(),
//                model.asMap());
//        html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail", context);
//        if (!StringUtils.isEmpty(html)) {
//            valueOperations.set("goodsDetail:" + goodsId, html, 60, TimeUnit.SECONDS);
//        }
//        return html;
//        // return "goodsDetail";
//    }

    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public RespBean toDetail(User user, @PathVariable Long goodsId) {
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        //秒杀状态
        int secKillStatus = 0;
        //秒杀倒计时
        int remainSeconds = 0;
        //秒杀还未开始
        if (nowDate.before(startDate)) {
            remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime()) / 1000));
        } else if (nowDate.after(endDate)) {
            //	秒杀已结束
            secKillStatus = 2;
            remainSeconds = -1;
        } else {
            //秒杀中
            secKillStatus = 1;
            remainSeconds = 0;
        }
        DetailVo detailVo = new DetailVo();
        detailVo.setUser(user);
        detailVo.setGoodsVo(goodsVo);
        detailVo.setSecKillStatus(secKillStatus);
        detailVo.setRemainSeconds(remainSeconds);
        return RespBean.success(detailVo);
    }
}
//
//}
//
//@Controller
//@RequestMapping("/goods")
//public class GoodsController {
//    @Autowired
//    private UserServiceImpl userService;
//    @Autowired
//    private IGoodsService goodsService;
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Autowired
//    private ThymeleafViewResolver thymeleafViewResolver;
//
//    @RequestMapping(value = "/toList",produces = "text/html;charset = utf-8")
//    @ResponseBody
//    public String toList(Model model, User user) {
////        if(StringUtils.isEmpty(ticket)){
////            return "login";
////        }
//////        User user = (User) session.getAttribute(ticket);
////        User user = userService.getUserByCookie(ticket,request,response);
////        System.out.println(user);
////        if(null == user){
////            return "login";
////        }
//        model.addAttribute("goodsList", goodsService.findGoodsVo());
//        model.addAttribute("user", user);
//        return "goodsList";
//    }
//
//    @RequestMapping("/toDetail/{goodsId}")
//    public String toDetail(Model model, User user, @PathVariable Long goodsId) {
//        model.addAttribute("user", user);
//        GoodsVo goodsVoByGoodsId = goodsService.findGoodsVoByGoodsId(goodsId);
//        Date startDate = goodsVoByGoodsId.getStartDate();
//        Date endDate = goodsVoByGoodsId.getEndDate();
//        Date nowDate = new Date();
//        System.out.println(startDate);
//        System.out.println(endDate);
//        System.out.println(nowDate);
//        int remainSeconds = 0;
//        int seckillStatus = 0;
//        if (nowDate.before(startDate)) {
//            remainSeconds = ((int) (startDate.getTime() - nowDate.getTime()) / 1000);
//            seckillStatus = 0;
//        } else if (nowDate.after(endDate)) {
//            remainSeconds = -1;
//            seckillStatus = 2;
//        } else {
//            remainSeconds = 0;
//            seckillStatus = 1;
//        }
//        model.addAttribute("remainSeconds", remainSeconds);
//        model.addAttribute("goods", goodsVoByGoodsId);
//        model.addAttribute("seckillStatus", seckillStatus);
//        return "goodsDetail";
//    }
//
//
//}
