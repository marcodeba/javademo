package com.demo.javademo.concurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author：marco.pan
 * @ClassName：CompletableFutureTest2
 * @Description：
 * @date: 2022年01月19日 1:23 下午
 */
@Slf4j
public class CompletableFutureTest2 {
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//        UserInfoService userInfoService = new UserInfoService();
//        MedalService medalService = new MedalService();
//        long userId =666L;
//        long startTime = System.currentTimeMillis();
//
//        //调用用户服务获取用户基本信息
//        FutureTask<UserInfoService.UserInfo> userInfoFutureTask = new FutureTask<>(new Callable<UserInfoService.UserInfo>() {
//            @Override
//            public UserInfoService.UserInfo call() throws Exception {
//                return userInfoService.getUserInfo(userId);
//            }
//        });
//        executorService.submit(userInfoFutureTask);
//
//        Thread.sleep(300); //模拟主线程其它操作耗时
//
//        FutureTask<MedalService.MedalInfo> medalInfoFutureTask = new FutureTask<>(new Callable<MedalService.MedalInfo>() {
//            @Override
//            public MedalService.MedalInfo call() throws Exception {
//                return medalService.getMedalInfo(userId);
//            }
//        });
//        executorService.submit(medalInfoFutureTask);
//
//        userInfoFutureTask.get();//获取个人信息结果
//        medalInfoFutureTask.get();//获取勋章信息结果
//
//        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
//    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long userId = 666L;
        long startTime = System.currentTimeMillis();
        log.info("main start");
        //调用用户服务获取用户基本信息
        CompletableFuture<UserInfoService.UserInfo> completableUserInfoFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return userInfoService.getUserInfo(userId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        TimeUnit.SECONDS.sleep(3); //模拟主线程其它操作耗时
        CompletableFuture<MedalService.MedalInfo> completableMedalInfoFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return medalService.getMedalInfo(userId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        completableUserInfoFuture.get(2, TimeUnit.SECONDS);//获取个人信息结果
        completableMedalInfoFuture.get();//获取勋章信息结果
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");

    }

    @Slf4j
    static class UserInfoService {

        private class UserInfo {
            private String id;
            private String name;
            private int age;

            public UserInfo(String id, String name, int age) {
                this.id = id;
                this.name = name;
                this.age = age;
            }
        }

        public UserInfo getUserInfo(Long userId) throws InterruptedException {
            TimeUnit.SECONDS.sleep(3);//模拟调用耗时
            log.info("getUserInfo finish");
            return new UserInfo("666", "捡田螺的小男孩", 27); //一般是查数据库，或者远程调用返回的
        }

    }

    @Slf4j
    static class MedalService {

        private class MedalInfo {
            private String id;
            private String name;

            public MedalInfo(String id, String name) {
                this.id = id;
                this.name = name;
            }
        }

        public MedalInfo getMedalInfo(long userId) throws InterruptedException {
            TimeUnit.SECONDS.sleep(5); //模拟调用耗时
            log.info("getMedalInfo finish");
            return new MedalInfo("666", "守护勋章");
        }
    }
}
