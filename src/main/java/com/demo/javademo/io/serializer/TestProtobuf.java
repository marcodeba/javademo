package com.demo.javademo.io.serializer;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 进入 /usr/local/protobuf-3.14.0/examples
 * 编写 User.proto
 * 在 /usr/local/protobuf-3.14.0/examples 路径下执行 protoc ./User.proto --java_out=./
 * 在 /usr/local/protobuf-3.14.0/examples 下生成 package，里面有 .java 文件
 */
public class TestProtobuf {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        UserProto.User user = UserProto.User.newBuilder().setName("Serializer").setAge(20).build();
        ByteString byteString = user.toByteString();
        System.out.println(byteString.size());

        UserProto.User userInfo = UserProto.User.parseFrom(byteString);
        System.out.println(userInfo);
    }
}
