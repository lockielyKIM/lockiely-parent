package org.lockiely.exception;

/**
 * @author: lockiely
 * @Date: 2018/7/21 14:20
 * @email: lockiely@163.com
 */
public class ToolBoxException extends RuntimeException {

    private static final long serialVersionUID = 8247610319171014183L;

    public ToolBoxException(Throwable e) {
        super(e.getMessage(), e);
    }

    public ToolBoxException(String message) {
        super(message);
    }

    public ToolBoxException(String messageTemplate, Object... params) {
        super(String.format(messageTemplate, params));
    }

    public ToolBoxException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ToolBoxException(Throwable throwable, String messageTemplate, Object... params) {
        super(String.format(messageTemplate, params), throwable);
    }

}
