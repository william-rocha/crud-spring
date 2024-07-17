package com.example.crud_spring.enums;

public enum Category {
//    enum ordinal
//    BACKEND, FRONTEND
//    enum construtor
    BACK_END("back-end"), FRONT_END("front-end");

    private String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
