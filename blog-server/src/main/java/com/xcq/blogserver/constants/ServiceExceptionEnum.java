package com.xcq.blogserver.constants;

/**
 * 枚举项目中错误码
 */
public enum ServiceExceptionEnum {

    //========系统级别================
    SUCCESS(0, "success"),
    SYS_ERROR(2001001000, "server error"),
    MISSING_REQUEST_PARAM_ERROR(2001001001, "missing param"),
    REDIS_NOT_FOUND(2001001002,"not found key in redis"),

    //========用户模块================
    USER_NOT_FOUND(100100200, "user is not exist"),
    PASSWORD_NOT_TRUE(100100201,"password is error"),
    ARTICLE_ADD_FAILURE(100100202,"add article failure"),
    ;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误提示
     */
    private String message;

    ServiceExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
