package com.demo.javademo.bean;

import lombok.Data;

/**
 * @author：marco.pan
 * @ClassName：TestBean
 * @Description：
 * @date: 2022年01月29日 11:31 上午
 */
@Data
public class TestBean {
    private String beanName;

    public TestBean(String beanName) {
        this.beanName = beanName;
    }
}
