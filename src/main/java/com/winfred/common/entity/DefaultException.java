package com.winfred.common.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author winfred
 */
@NoArgsConstructor
public class DefaultException extends RuntimeException {

  private static final long serialVersionUID = -2419186112996851702L;

  @Getter
  @Setter
  private BaseResponse<?> response;

  public DefaultException(String message) {
    super(message);
    this.response = BaseResponse.send(this);
  }

  public DefaultException(String message, Throwable throwable) {
    super(message, throwable);
    this.response = BaseResponse.send(this);
  }

  public DefaultException(Object data, String message) {
    super(message);
    this.response = BaseResponse.send(this, data);
  }

  public DefaultException(Object data, Throwable throwable) {
    super(throwable);
    this.response = BaseResponse.send(this, data);
  }

  public DefaultException(Object data, String message, Throwable throwable) {
    super(message, throwable);
    this.response = BaseResponse.send(this, data);
  }

  public DefaultException(Throwable throwable) {
    this.response = BaseResponse.send(throwable);
  }
}
