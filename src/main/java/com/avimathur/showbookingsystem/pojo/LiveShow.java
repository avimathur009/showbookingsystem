package com.avimathur.showbookingsystem.pojo;

import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.constant.Slot;

public abstract class LiveShow {

    protected
        ShowType type;
        String name;
        String uniqueShowId;
        Integer price;
        Integer maxCapacity;
        Integer currCapacity;
        Slot slot;

    public LiveShow(){
        this.name="";
        this.price=0;
        this.maxCapacity=0;
        this.currCapacity=0;
        this.slot=Slot.Slot0;
        this.uniqueShowId = java.util.UUID.randomUUID().toString();
    }

    public void setShowDetails(String name, Integer price, Integer capacity, Slot slot){
        this.name=name;
        this.price=price;
        this.maxCapacity=capacity;
        this.currCapacity=capacity;
        this.slot=slot;
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

    public Integer getMaxCapacity(){
        return maxCapacity;
    }

    public Integer getCurrCapacity(){
        return currCapacity;
    }

    public Slot getShowSlot(){
        return slot;
    }

    public void increaseCurrCapacity(Integer cancelledUsers){
        currCapacity+=cancelledUsers;
    }

    public void decreaseCurrCapacity(Integer bookedUsers){
        currCapacity-=bookedUsers;
    }
}
