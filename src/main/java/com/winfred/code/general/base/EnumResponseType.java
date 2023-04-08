package com.winfred.code.general.base;

/**
 * @author winfred
 */
public enum EnumResponseType {

  /**
   * 返回ok
   */
  SUCCESS(0, "ok", "success"),
  /**
   * 默认返回
   */
  DEFAULT_ERROR(-1, "unknown error", "[service] unknown error"),

  /**
   * 权限错误
   */
  PERMISSION_ERROR(4401, "permission error", "[client] permission error"),

  /**
   * 不支持的操作
   */
  NONSUPPORT_EXCEPTION(6601, "nonsupport operation", "[nonsupport] nonsupport operation"),
  ;

  EnumResponseType(Integer code, String message, String describe) {
    this.code = code;
    this.message = message;
    this.describe = describe;
  }

  private Integer code;
  private String message;
  private String describe;

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public String getDescribe() {
    return describe;
  }
}
