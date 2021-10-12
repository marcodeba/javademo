package com.demo.javademo.io.serializer;

public class TestJavaSerializer {
    public static void main(String[] args) {
        User user = new User("Serializer", 18);
        ISerializer iSerializer = new JavaSerializer();
        byte[] bytes = iSerializer.serialize(user);
        System.out.println(bytes.length);

        User user1 = iSerializer.deserialize(bytes);
        System.out.println(user1);
    }
}
