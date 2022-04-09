package com.demo.javademo.concurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/reduce_stock")
    public String reduceStock() {
        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
        // check then act
        if (stock > 0) {
            stock--;
            stringRedisTemplate.opsForValue().set("stock", String.valueOf(stock));
            System.out.println("扣减成功，库存stock:" + stock);
        } else {
            System.out.println("扣减失败，库存不足");
        }
        return "end";
    }
}
