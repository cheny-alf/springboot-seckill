# springboot-seckill
项目描述

     该系统是用于模拟高并发情况下的秒杀系统，可以解决大量用户在同一时刻访问该系统并且抢购、下订单以及支付，不会出现超卖。宕机等问题。系统主要解决两个问题，一个是并发读，一个是并发写。并发读的核心优化理念是尽量减少用户到服务端来“读”数据，或者让他们读更少的数据；并发写的处理原则也一样，它要求我们在数据库层面独立出来一个库，做特殊的处理。另外，我们还要针对秒杀系统做一些保护，针对意料之外的情况设计兜底方案，以防止最坏的情况发生。

功能总结

      账户安全问题：

             通过两次MD5加密解决账户的安全问题。首先在用户端MD5加密是为了防止用户密码在网络中明文传输。其次服务端MD5加密是为了提高密码安全性，双重保险。

      异常处理：

             在本次项目中使用 @ControllerAdvice 和 @ExceptionHandler 注解。这能将所有类型的异常处理从各处理过程解耦出来，既保证了相关处理过程的功能较单一，也实现了异常信息的统一处理和维护。和使用 ErrorController类来实现相比，该方式可以定义多个拦截方法，拦截不同类型的异常类，并且可以获取抛出的异常信息，自由度较高。

        超卖问题：

               首先通过数据库加唯一索引，避免统一用户重复购买。其次通过进行一个库存数量必须大于0的判断，防止库存数量变为负数，同时做了一个减库存的操作，然后拿到更新的结果去判断是否创建订单。如果更新失败，则取消创建订单，同时也解决了超卖问题。

      安全优化问题：

               首先通过对接口进行隐藏，强迫用户需要先去获取秒杀的地址，而不是直接去秒杀。如果没有获取到秒杀地址的话，是无法真正的去秒杀。其次，利用验证码来避免利用脚本等问题的发生，并且也减轻了同一时刻的秒杀产生的压力。最后也对接口进行限流，控制同一用户在一定时间内的访问次数。
