package com.example.demo.model;

public final class Station {

    private final String code;
    private final String name;

    public Station(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
