package com.demo.javademo.config;

import com.demo.javademo.bean.TestBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：marco.pan
 * @ClassName：TestConfig
 * @Description：
 * @date: 2022年01月29日 11:31 上午
 */
@Configuration
public class TestConfig {
    @Qualifier
    @Bean("testBean1")
    public TestBean testBean1() {
        return new TestBean("bean1");
    }

    @Bean("testBean2")
    public TestBean testBean2() {
        return new TestBean("bean2");
    }
}
