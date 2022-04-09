package com.demo.javademo.controller;

import com.demo.javademo.bean.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author：marco.pan
 * @ClassName：TestController
 * @Description：
 * @date: 2022年01月29日 11:31 上午
 */
@RestController
@Slf4j
public class TestController {
    @Qualifier
    @Autowired
    List<TestBean> testBeans = Collections.emptyList();

    @GetMapping("/test")
    public List<TestBean> test() {
        return testBeans;
    }
}
