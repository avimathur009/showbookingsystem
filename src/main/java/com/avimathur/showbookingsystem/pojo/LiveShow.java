package com.avimathur.showbookingsystem.model;

import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.constant.Slot;

import java.util.PriorityQueue;

public abstract class LiveShow {

    protected
        ShowType type;
        String name;
        String uniqueShowId;
        Integer price;
        Integer capacity;
        Slot slot;

    public LiveShow(){
        this.name="";
        this.price=0;
        this.capacity=0;
        this.slot=Slot.Slot0;
    }

    public void setShowDetails(String name, Integer price, Integer capacity, String slot){
        this.name=name;
        this.price=price;
        this.capacity=capacity;
        this.slot=Slot.valueOf(slot);
        this.uniqueShowId = 
    }

    public ShowType getShowType(){
        return type;
    }

    public String getShowName(){
        return name;
    }

    public Integer getShowPrice(){
        return price;
    }

    public Integer getCapacity(){
        return capacity;
    }

    public Slot getShowSlot(){
        return slot;
    }

    public void updateCapacity(Integer bookedUsers){
        capacity=capacity-bookedUsers;
    }
}
