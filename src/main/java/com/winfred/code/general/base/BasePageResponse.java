package com.winfred.code.general.base;


/**
 * @author winfred
 */
public class BasePageResponse<T extends PageData<?>> extends BaseResponse<T> {

  private T data;

  public BasePageResponse() {
    super();
  }

  public BasePageResponse(T data) {
    super();
    this.data = data;
  }

  public static <T extends PageData<?>> BasePageResponse<T> send(T data) {
    BasePageResponse<T> pageResponse = new BasePageResponse<T>();
    EnumResponseType type = EnumResponseType.SUCCESS;
    pageResponse.setCode(type.getCode());
    pageResponse.setMessage(type.getMessage());
    pageResponse.setData(data);
    return pageResponse;
  }

  public static <T extends PageData<?>> BasePageResponse<T> send(EnumResponseType type, T data) {
    BasePageResponse<T> pageResponse = new BasePageResponse<T>();
    pageResponse.setCode(type.getCode());
    pageResponse.setMessage(type.getMessage());
    pageResponse.setData(data);
    return pageResponse;
  }

  public static <T extends PageData<?>> BasePageResponse<T> send(Throwable throwable, T data) {
    BasePageResponse<T> pageResponse = new BasePageResponse<T>();
    pageResponse.setCode(EnumResponseType.DEFAULT_ERROR.getCode());
    pageResponse.setMessage(throwable.getMessage());
    pageResponse.setData(data);
    return pageResponse;
  }

  @Override
  public T getData() {
    return data;
  }

  /**
   * 设置分页数据返回
   *
   * @param data
   */
  @Override
  public void setData(T data) {
    this.data = data;
  }

}

