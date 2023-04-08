package com.winfred.code.general.base;


import org.apache.commons.lang3.StringUtils;

/**
 * @author winfred
 */
public class BaseResponse<T> {
  private Integer code;
  private String message;

  private T data;

  public BaseResponse() {
    this.code = EnumResponseType.SUCCESS.getCode();
    this.message = EnumResponseType.SUCCESS.getMessage();
  }

  public BaseResponse(T data) {
    this.code = EnumResponseType.SUCCESS.getCode();
    this.message = EnumResponseType.SUCCESS.getMessage();
    this.data = data;
  }

  public static <T> BaseResponse<T> success(T data) {
    BaseResponse<T> response = new BaseResponse<>();
    response.setData(data);
    return response;
  }

  public static <T> BaseResponse<T> send(EnumResponseType type) {
    BaseResponse<T> response = new BaseResponse<>();
    response.setCode(type.getCode());
    response.setMessage(type.getMessage());
    return response;
  }

  public static <T> BaseResponse<T> send(EnumResponseType type, T data) {
    BaseResponse<T> response = new BaseResponse<>();
    response.setCode(type.getCode());
    response.setMessage(type.getMessage());
    response.setData(data);
    return response;
  }

  public static <T> BaseResponse<T> send(Throwable throwable) {
    BaseResponse<T> response = new BaseResponse<>();
    setResponseMessage(throwable, response);
    response.setData(null);
    return response;
  }

  private static <T> void setResponseMessage(Throwable throwable, BaseResponse<T> response) {
    if (throwable instanceof DefaultException) {
      response.setMessage(throwable.getMessage());
    } else if (!StringUtils.isBlank(throwable.getMessage())) {
      response.setMessage(throwable.getMessage());
    } else {
      response.setMessage(EnumResponseType.DEFAULT_ERROR.getMessage());
    }
    response.setCode(EnumResponseType.DEFAULT_ERROR.getCode());
  }

  public static <T> BaseResponse<T> send(Throwable throwable, T data) {
    BaseResponse<T> response = new BaseResponse<>();
    setResponseMessage(throwable, response);
    response.setCode(EnumResponseType.DEFAULT_ERROR.getCode());
    response.setData(data);
    return response;
  }


  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return this.data;
  }

  /**
   * 常规返回, 非分页
   *
   * @param data
   */
  public void setData(T data) {
    this.data = data;
  }


}
