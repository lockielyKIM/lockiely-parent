package org.lockiely.web.controller;


/**
 * 返回对象类
 */
public class ResponseResult {

    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 失败或成功的提示信息
     */
    private String message;

    /**
     * 返回的数据
     */
    private Object data;

    public ResponseResult(Object data) {
        this(true, null, data);
    }

    public ResponseResult(boolean success, String message) {
        this(success, message, null);
    }

    public ResponseResult(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    
}
