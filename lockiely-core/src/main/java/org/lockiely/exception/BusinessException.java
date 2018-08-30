package org.lockiely.exception;

public class BusinessException extends PlatformException {

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
