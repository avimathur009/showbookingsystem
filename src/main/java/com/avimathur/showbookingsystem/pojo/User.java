package com.avimathur.showbookingsystem.pojo;

public class User {

    String name;
    Integer age;


    public User(String name){
        this.name=name;
    }

    public User(String name, Integer age){
        this.name=name;
        this.age=age;
    }

    public String getUserName(){
        return name;
    }

    public Integer getUserAge(){
        return age;
    }

}
