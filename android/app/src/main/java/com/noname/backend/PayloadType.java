// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BackendData.proto

package com.noname.backend;

/**
 * Protobuf enum {@code PayloadType}
 */
public enum PayloadType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>BASE_REQUEST = 0;</code>
   */
  BASE_REQUEST(0),
  /**
   * <code>BASE_RESPONSE = 1;</code>
   */
  BASE_RESPONSE(1),
  /**
   * <code>AUTH_LOGIN = 2;</code>
   */
  AUTH_LOGIN(2),
  /**
   * <code>BASE_PING = 3;</code>
   */
  BASE_PING(3),
  /**
   * <code>BASE_PONG = 4;</code>
   */
  BASE_PONG(4),
  /**
   * <code>USER_MAIL = 100;</code>
   */
  USER_MAIL(100),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>BASE_REQUEST = 0;</code>
   */
  public static final int BASE_REQUEST_VALUE = 0;
  /**
   * <code>BASE_RESPONSE = 1;</code>
   */
  public static final int BASE_RESPONSE_VALUE = 1;
  /**
   * <code>AUTH_LOGIN = 2;</code>
   */
  public static final int AUTH_LOGIN_VALUE = 2;
  /**
   * <code>BASE_PING = 3;</code>
   */
  public static final int BASE_PING_VALUE = 3;
  /**
   * <code>BASE_PONG = 4;</code>
   */
  public static final int BASE_PONG_VALUE = 4;
  /**
   * <code>USER_MAIL = 100;</code>
   */
  public static final int USER_MAIL_VALUE = 100;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static PayloadType valueOf(int value) {
    return forNumber(value);
  }

  public static PayloadType forNumber(int value) {
    switch (value) {
      case 0: return BASE_REQUEST;
      case 1: return BASE_RESPONSE;
      case 2: return AUTH_LOGIN;
      case 3: return BASE_PING;
      case 4: return BASE_PONG;
      case 100: return USER_MAIL;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<PayloadType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      PayloadType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<PayloadType>() {
          public PayloadType findValueByNumber(int number) {
            return PayloadType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.noname.backend.BackendPayload.getDescriptor().getEnumTypes().get(0);
  }

  private static final PayloadType[] VALUES = values();

  public static PayloadType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private PayloadType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:PayloadType)
}
