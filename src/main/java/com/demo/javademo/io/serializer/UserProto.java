// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: User.proto

package com.demo.javademo.io.serializer;

public final class UserProto {
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_demo_javademo_io_serializer_User_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_demo_javademo_io_serializer_User_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        String[] descriptorData = {
                "\n\nUser.proto\022\037com.demo.javademo.io.seria" +
                        "lizer\"!\n\004User\022\014\n\004name\030\001 \002(\t\022\013\n\003age\030\002 \002(\005" +
                        "B,\n\037com.demo.javademo.io.serializerB\tUse" +
                        "rProto"
        };
        descriptor = com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        });
        internal_static_com_demo_javademo_io_serializer_User_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_demo_javademo_io_serializer_User_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_demo_javademo_io_serializer_User_descriptor,
                new String[]{"Name", "Age",});
    }

    private UserProto() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface UserOrBuilder extends
            // @@protoc_insertion_point(interface_extends:com.demo.javademo.io.serializer.User)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>required string name = 1;</code>
         *
         * @return Whether the name field is set.
         */
        boolean hasName();

        /**
         * <code>required string name = 1;</code>
         *
         * @return The name.
         */
        String getName();

        /**
         * <code>required string name = 1;</code>
         *
         * @return The bytes for name.
         */
        com.google.protobuf.ByteString
        getNameBytes();

        /**
         * <code>required int32 age = 2;</code>
         *
         * @return Whether the age field is set.
         */
        boolean hasAge();

        /**
         * <code>required int32 age = 2;</code>
         *
         * @return The age.
         */
        int getAge();
    }

    /**
     * Protobuf type {@code com.demo.javademo.io.serializer.User}
     */
    public static final class User extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:com.demo.javademo.io.serializer.User)
            UserOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int AGE_FIELD_NUMBER = 2;
        @Deprecated
        public static final com.google.protobuf.Parser<User>
                PARSER = new com.google.protobuf.AbstractParser<User>() {
            @Override
            public User parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new User(input, extensionRegistry);
            }
        };
        private static final long serialVersionUID = 0L;
        // @@protoc_insertion_point(class_scope:com.demo.javademo.io.serializer.User)
        private static final User DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE = new User();
        }

        private int bitField0_;
        private volatile Object name_;
        private int age_;
        private byte memoizedIsInitialized = -1;

        // Use User.newBuilder() to construct.
        private User(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private User() {
            name_ = "";
        }

        private User(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 10: {
                            com.google.protobuf.ByteString bs = input.readBytes();
                            bitField0_ |= 0x00000001;
                            name_ = bs;
                            break;
                        }
                        case 16: {
                            bitField0_ |= 0x00000002;
                            age_ = input.readInt32();
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return UserProto.internal_static_com_demo_javademo_io_serializer_User_descriptor;
        }

        public static User parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static User parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static User parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static User parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static User parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static User parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static User parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static User parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static User parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static User parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static User parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static User parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(User prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public static User getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static com.google.protobuf.Parser<User> parser() {
            return PARSER;
        }

        @Override
        @SuppressWarnings({"unused"})
        protected Object newInstance(
                UnusedPrivateParameter unused) {
            return new User();
        }

        @Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        protected FieldAccessorTable
        internalGetFieldAccessorTable() {
            return UserProto.internal_static_com_demo_javademo_io_serializer_User_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            User.class, Builder.class);
        }

        /**
         * <code>required string name = 1;</code>
         *
         * @return Whether the name field is set.
         */
        @Override
        public boolean hasName() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>required string name = 1;</code>
         *
         * @return The name.
         */
        @Override
        public String getName() {
            Object ref = name_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    name_ = s;
                }
                return s;
            }
        }

        /**
         * <code>required string name = 1;</code>
         *
         * @return The bytes for name.
         */
        @Override
        public com.google.protobuf.ByteString
        getNameBytes() {
            Object ref = name_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>required int32 age = 2;</code>
         *
         * @return Whether the age field is set.
         */
        @Override
        public boolean hasAge() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>required int32 age = 2;</code>
         *
         * @return The age.
         */
        @Override
        public int getAge() {
            return age_;
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            if (!hasName()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasAge()) {
                memoizedIsInitialized = 0;
                return false;
            }
            memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (((bitField0_ & 0x00000001) != 0)) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
            }
            if (((bitField0_ & 0x00000002) != 0)) {
                output.writeInt32(2, age_);
            }
            unknownFields.writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) != 0)) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
            }
            if (((bitField0_ & 0x00000002) != 0)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(2, age_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof User)) {
                return super.equals(obj);
            }
            User other = (User) obj;

            if (hasName() != other.hasName()) return false;
            if (hasName()) {
                if (!getName()
                        .equals(other.getName())) return false;
            }
            if (hasAge() != other.hasAge()) return false;
            if (hasAge()) {
                if (getAge()
                        != other.getAge()) return false;
            }
            if (!unknownFields.equals(other.unknownFields)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            if (hasName()) {
                hash = (37 * hash) + NAME_FIELD_NUMBER;
                hash = (53 * hash) + getName().hashCode();
            }
            if (hasAge()) {
                hash = (37 * hash) + AGE_FIELD_NUMBER;
                hash = (53 * hash) + getAge();
            }
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        @Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        protected Builder newBuilderForType(
                BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        @Override
        public com.google.protobuf.Parser<User> getParserForType() {
            return PARSER;
        }

        @Override
        public User getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        /**
         * Protobuf type {@code com.demo.javademo.io.serializer.User}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:com.demo.javademo.io.serializer.User)
                UserOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private int age_;

            // Construct using com.demo.javademo.io.serializer.UserProto.User.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return UserProto.internal_static_com_demo_javademo_io_serializer_User_descriptor;
            }

            @Override
            protected FieldAccessorTable
            internalGetFieldAccessorTable() {
                return UserProto.internal_static_com_demo_javademo_io_serializer_User_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                User.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @Override
            public Builder clear() {
                super.clear();
                name_ = "";
                bitField0_ = (bitField0_ & ~0x00000001);
                age_ = 0;
                bitField0_ = (bitField0_ & ~0x00000002);
                return this;
            }

            @Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return UserProto.internal_static_com_demo_javademo_io_serializer_User_descriptor;
            }

            @Override
            public User getDefaultInstanceForType() {
                return User.getDefaultInstance();
            }

            @Override
            public User build() {
                User result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public User buildPartial() {
                User result = new User(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) != 0)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.name_ = name_;
                if (((from_bitField0_ & 0x00000002) != 0)) {
                    result.age_ = age_;
                    to_bitField0_ |= 0x00000002;
                }
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override
            public Builder clone() {
                return super.clone();
            }

            @Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return super.setField(field, value);
            }

            @Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return super.clearField(field);
            }

            @Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return super.clearOneof(oneof);
            }

            @Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, Object value) {
                return super.setRepeatedField(field, index, value);
            }

            @Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return super.addRepeatedField(field, value);
            }

            @Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof User) {
                    return mergeFrom((User) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(User other) {
                if (other == User.getDefaultInstance()) return this;
                if (other.hasName()) {
                    bitField0_ |= 0x00000001;
                    name_ = other.name_;
                    onChanged();
                }
                if (other.hasAge()) {
                    setAge(other.getAge());
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!hasName()) {
                    return false;
                }
                if (!hasAge()) {
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                User parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (User) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            /**
             * <code>required string name = 1;</code>
             *
             * @return Whether the name field is set.
             */
            public boolean hasName() {
                return ((bitField0_ & 0x00000001) != 0);
            }

            /**
             * <code>required string name = 1;</code>
             *
             * @return The name.
             */
            public String getName() {
                Object ref = name_;
                if (!(ref instanceof String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        name_ = s;
                    }
                    return s;
                } else {
                    return (String) ref;
                }
            }

            /**
             * <code>required string name = 1;</code>
             *
             * @param value The name to set.
             * @return This builder for chaining.
             */
            public Builder setName(
                    String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000001;
                name_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>required string name = 1;</code>
             *
             * @return The bytes for name.
             */
            public com.google.protobuf.ByteString
            getNameBytes() {
                Object ref = name_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (String) ref);
                    name_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>required string name = 1;</code>
             *
             * @param value The bytes for name to set.
             * @return This builder for chaining.
             */
            public Builder setNameBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000001;
                name_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>required string name = 1;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearName() {
                bitField0_ = (bitField0_ & ~0x00000001);
                name_ = getDefaultInstance().getName();
                onChanged();
                return this;
            }

            /**
             * <code>required int32 age = 2;</code>
             *
             * @return Whether the age field is set.
             */
            @Override
            public boolean hasAge() {
                return ((bitField0_ & 0x00000002) != 0);
            }

            /**
             * <code>required int32 age = 2;</code>
             *
             * @return The age.
             */
            @Override
            public int getAge() {
                return age_;
            }

            /**
             * <code>required int32 age = 2;</code>
             *
             * @param value The age to set.
             * @return This builder for chaining.
             */
            public Builder setAge(int value) {
                bitField0_ |= 0x00000002;
                age_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>required int32 age = 2;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearAge() {
                bitField0_ = (bitField0_ & ~0x00000002);
                age_ = 0;
                onChanged();
                return this;
            }

            @Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:com.demo.javademo.io.serializer.User)
        }

    }

    // @@protoc_insertion_point(outer_class_scope)
}
