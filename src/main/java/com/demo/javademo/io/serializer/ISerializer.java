package com.demo.javademo.io.serializer;

public interface ISerializer {
    <T> byte[] serialize(T object);

    <T> T deserialize(byte[] data);
}
