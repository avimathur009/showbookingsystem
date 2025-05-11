package com.avimathur.showbookingsystem.pojo;

import com.avimathur.showbookingsystem.constant.Slot;
import org.springframework.stereotype.Component;

@Component
public class Booking {

    Slot showSlot;
    String showName;
    Integer numOfUsers;
    String bookingId;
    User user;

    public Booking(){
        bookingId = java.util.UUID.randomUUID().toString();
    }

    public void setBookingDetails(Slot showSlot, String showName, Integer numOfUsers, User user){
        this.showSlot=showSlot;
        this.showName=showName;
        this.numOfUsers=numOfUsers;
        this.user=user;
    }

    public Slot getShowSlot(){return showSlot;}

    public String getShowName(){return showName;}

    public Integer getNumOfUsers(){
        return numOfUsers;
    }

    public User getUserDetails(){
        return user;
    }

    public String getBookingId(){ return bookingId;}

}
