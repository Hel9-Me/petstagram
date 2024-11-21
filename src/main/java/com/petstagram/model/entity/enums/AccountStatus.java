package com.petstagram.model.entity.enums;

public enum AccountStatus {
    Y("계정 활성화"),
    N("계정 비활성화 ");

    private final String message;


    AccountStatus(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
