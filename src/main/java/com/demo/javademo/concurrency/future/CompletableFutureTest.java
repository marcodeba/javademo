package com.demo.javademo.concurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author：marco.pan
 * @ClassName：CompletableFutureTest
 * @Description：
 * @date: 2022年01月19日 10:35 上午
 */
@Slf4j
public class CompletableFutureTest {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static List<Shop> shopList = new ArrayList<>();

    public static void main(String[] args) {
        init();
        log.info("=============");
        List<String> sync = findPriceSync("asdf");
        log.info("sync:{}", sync);
        List<String> async = findPriceAsync("asdf");
        log.info("async:{}", async);
    }

    public static void init() {
        shopList.add(new Shop("BestPrice"));
        shopList.add(new Shop("LetsSaveBig"));
        shopList.add(new Shop("MyFavoriteShop"));
        shopList.add(new Shop("BuyItAll"));
    }

    private static List<String> findPriceSync(String product) {
        return shopList.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    private static List<String> findPriceAsync(String product) {
        List<CompletableFuture<String>> completableFutureList = shopList.stream()
                //转异步执行
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f",
                                shop.getName(), shop.getPrice(product)), executorService))
                .collect(Collectors.toList());
        return completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    @Slf4j
    static class Shop {
        private String name;
        private Random random = new Random();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * 根据产品名查找价格
         */
        public double getPrice(String product) {
            return calculatePrice(product);
        }

        public Shop(String name) {
            this.name = name;
        }

        /**
         * 计算价格
         *
         * @param product
         * @return
         */
        private double calculatePrice(String product) {
            log.info("calculatePrice");
            delay();
            //random.nextDouble()随机返回折扣
            return random.nextDouble() * product.charAt(0) + product.charAt(1);
        }

        /**
         * 通过睡眠模拟其他耗时操作
         */
        private void delay() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
