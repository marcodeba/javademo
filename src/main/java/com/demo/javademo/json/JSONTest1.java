package com.demo.javademo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Map;

public class JSONTest1 {

    public static void main(String[] args) {
        JSONTest1 t = new JSONTest1();
        t.testJson2();
    }

    public void testJson2() {
        String str = "{\"name\":\"张三\",\"id\":3000}";

        // 反序列化时Long被转为了Integer
        Map map = JSON.parseObject(str, Map.class);

        // 转换成 User时 Integer 转为了Long
        User user = JSON.toJavaObject(new JSONObject(map), User.class);
        System.out.println(user);
    }
}


@Data
class User {
    private Long id;

    private String name;
}
