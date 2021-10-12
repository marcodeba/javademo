package com.demo.javademo.io.serializer;

import java.io.*;

public class JavaSerializer implements ISerializer {

    // 序列化
    @Override
    public <T> byte[] serialize(T object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                    objectOutputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                byteArrayOutputStream.close();
                byteArrayOutputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }

    // 反序列化
    @Override
    public <T> T deserialize(byte[] data) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                    objectInputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                byteArrayInputStream.close();
                byteArrayInputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
