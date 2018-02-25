package com.emuii.ssm.exception;

/**
 * Create by Leslie on 2018\1\10 0010.<br>
 */
public class CustomException extends Exception {

    // 异常信息
    private String message;

    public CustomException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
