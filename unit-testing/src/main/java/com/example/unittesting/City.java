package com.example.unittesting;

public enum City {
    ANDONG("안동시"),
    ANSAN("안산시"),
    ANSEONG("안성시"),
    ANYANG("안양시"),
    ASAN("아산시");

    private String description;

    City(String desc){
        description = desc;
    }
}
