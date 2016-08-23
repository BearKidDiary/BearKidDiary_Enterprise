package com.bearkiddiary.enterprise.model.bean;

public class Result<T> {

    public static final String RESULTCODE = "resultCode";
    public static final String RESULTMESSAGE = "resultMessage";
    public static final String DATA = "data";

    private int resultCode;
    private String resultMessage;
    private T data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}