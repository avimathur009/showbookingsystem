package com.avimathur.showbookingsystem.pojo;

public class Organiser {

    String organiserName;
    String showName;

    public Organiser(String organiserName){
        this.organiserName=organiserName;
    }

    public void setShowName(String showName){
        this.showName=showName;
    }

    public String getShowName(){
        return showName;
    }

}
