package com.avimathur.showbookingsystem.model;

public class User {

    String name;
    Integer age;

    public User(String name, Integer age){
        this.name=name;
        this.age=age;
    }

    String getUserName(){
        return name;
    }

    Integer getUserAge(){
        return age;
    }

}
