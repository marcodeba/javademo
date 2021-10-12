package com.demo.javademo.json;


import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

public class JSONTest {
    public static void main(String[] args) {
        String json1 = "{\n" +
                "    \"name\": \"skynet\",\n" +
                "    \"info\": {\n" +
                "        \"s2\": 18,\n" +
                "        \"newList\": [\n" +
                "            \"C++\",\n" +
                "            \"JAVA\",\n" +
                "            \"C#\"\n" +
                "        ],\n" +
                "        \"sub\": {\n" +
                "            \"s3\": true,\n" +
                "            \"sl\": 12.2222,\n" +
                "            \"map\": {\n" +
                "                \"student1\": {\n" +
                "                    \"name\": \"mike\",\n" +
                "                    \"info\": {\n" +
                "                        \"s2\": 14,\n" +
                "                        \"newList\": [\n" +
                "                            \"int\",\n" +
                "                            \"float\",\n" +
                "                            \"char\"\n" +
                "                        ],\n" +
                "                        \"sub\": \"\"\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        System.out.println(json1);
        Students students = JSON.parseObject(json1, Students.class);
        System.out.println(students.toString());
    }
}

class Students {
    private String name;
    private Info info;                //第二层对象


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "name: " + name + " " + "info: " + info.toString();
    }
}

class Info {
    private int s2;
    private List<String> newList;
    private Sub sub;                    //第三层对象


    public int getS2() {
        return s2;
    }

    public void setS2(int s2) {
        this.s2 = s2;
    }

    public List<String> getNewList() {
        return newList;
    }

    public void setNewList(List<String> newList) {
        this.newList = newList;
    }

    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub sub) {
        this.sub = sub;
    }

    @Override
    public String toString() {
        return "s2: " + s2 + " " + "sub: " + sub + " " + "list: " + newList;
    }
}

//继承SL对象
class Sub extends SL {
    private boolean s3;
    private Map<String, Students> map;          //循环嵌套对象

    public boolean isS3() {
        return s3;
    }

    public void setS3(boolean s3) {
        this.s3 = s3;
    }

    public Map<String, Students> getMap() {
        return map;
    }

    public void setMap(Map<String, Students> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "s3: " + s3 + " " + "map: " + map + " " + "sl: " + super.toString();
    }
}

class SL {
    private double sl;

    public double getSl() {
        return sl;
    }

    public void setSl(double sl) {
        this.sl = sl;
    }

    @Override
    public String toString() {
        return "sl: " + sl;
    }
}