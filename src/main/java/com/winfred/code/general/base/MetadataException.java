package com.winfred.code.general.base;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author winfred
 */
@NoArgsConstructor
public class MetadataException extends RuntimeException {

    private static final long serialVersionUID = -2419186112996851702L;

    @Getter
    @Setter
    private BaseResponse<?> response;

    public MetadataException(String message) {
        super(message);
        this.response = BaseResponse.send(this);
    }

    public MetadataException(String message, Throwable throwable) {
        super(message, throwable);
        this.response = BaseResponse.send(this);
    }

    public MetadataException(Object data, String message) {
        super(message);
        this.response = BaseResponse.send(this, data);
    }

    public MetadataException(Object data, Throwable throwable) {
        super(throwable);
        this.response = BaseResponse.send(this, data);
    }

    public MetadataException(Object data, String message, Throwable throwable) {
        super(message, throwable);
        this.response = BaseResponse.send(this, data);
    }

    public MetadataException(Throwable throwable) {
        this.response = BaseResponse.send(throwable);
    }
}
