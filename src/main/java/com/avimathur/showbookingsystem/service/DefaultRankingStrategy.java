package com.avimathur.showbookingsystem.service;

import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.constant.Slot;
import com.avimathur.showbookingsystem.pojo.LiveShow;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class DefaultRankingStrategy extends RankingStrategy{

    @Override
    public void displayAllShows(Map<String, ArrayList<LiveShow>> listOfShows) {
        for(String showName : listOfShows.keySet()){
            for(LiveShow show : listOfShows.get(showName)){
                Slot slot = show.getShowSlot();
                System.out.println(slot.getSlotDetail()+" HRS || ShowName: "+show.getShowName()+
                        " || Capacity: "+show.getMaxCapacity()+" || Price: "+show.getShowPrice());
            }
        }
    }

    @Override
    public void displayAllShowsByShowType(ShowType requestedShowType, Map<String, ArrayList<LiveShow>> listOfShows) {
        for(String showName : listOfShows.keySet()){
            for(LiveShow show : listOfShows.get(showName)){
                if(show.getShowType()==requestedShowType){
                    Slot slot = show.getShowSlot();
                    System.out.println(slot.getSlotDetail()+" HRS || ShowName: "+show.getShowName()+
                            " || Capacity: "+show.getMaxCapacity()+" || Price: "+show.getShowPrice());
                }
            }
        }
    }

    @Override
    public void displayAvailableShowsByShowType(ShowType requestedShowType, Map<String, ArrayList<LiveShow>> listOfShows) {
        for(String showName : listOfShows.keySet()){
            for(LiveShow show : listOfShows.get(showName)){
                if(show.getShowType()==requestedShowType && show.getCurrCapacity()!=0){
                    Slot slot = show.getShowSlot();
                    System.out.println(slot.getSlotDetail()+" HRS || ShowName: "+show.getShowName()+
                            " || Capacity: "+show.getCurrCapacity()+" || Price: "+show.getShowPrice());
                }
            }
        }
    }
}
