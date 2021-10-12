package com.demo.javademo.io.excel.util;

@Excel(name = "学生标签页")
public class Student {

    @Excel(name = "姓名")
    private String name;

    private boolean male;

    @Excel(name = "身高")
    private int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
