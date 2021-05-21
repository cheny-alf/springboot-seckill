//package com.cheny.springbootseckill.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * @ClassName RabbitMQConfig
// * @Description rabbitMQ 配置类
// * @Author cheny
// * @Date 2021/5/17 13:27
// * @Version 1.0
// **/
//@Configuration
//public class RabbitMQConfig {
//
//    private static final String QUEUE01 = "queue_fanout01";
//    private static final String QUEUE02 = "queue_fanout02";
//    private static final String EXCHANGE = "fanoutExchange";
//
//    @Bean
//    public Queue queue(){
//        return new Queue("queue",true);
//    }
//    @Bean
//    public Queue queue01(){
//        return new Queue("queue_fanout01");
//    }
//    @Bean
//    public Queue queue02(){
//        return new Queue("queue_fanout02");
//    }
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding binding01(){
//        return BindingBuilder.bind(queue01()).to(fanoutExchange());
//    }
//    @Bean
//    public Binding binding02(){
//        return BindingBuilder.bind(queue02()).to(fanoutExchange());
//    }
//
//}
