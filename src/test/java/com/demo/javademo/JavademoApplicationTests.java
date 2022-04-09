package com.demo.javademo;

import com.demo.javademo.util.OkHttp3Util;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@SpringBootTest
@Slf4j
class JavademoApplicationTests {

    @Test
    public void testReduceStock() {
        String result = OkHttp3Util.sendByGetUrl("http://localhost:8080/reduce_stock");
        log.info("result: {}", result);
    }

}
