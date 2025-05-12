package com.avimathur.showbookingsystem.repository;

import com.avimathur.showbookingsystem.constant.RankingType;
import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.constant.Slot;
import com.avimathur.showbookingsystem.pojo.LiveShow;
import com.avimathur.showbookingsystem.service.RankingStrategy;
import com.avimathur.showbookingsystem.utils.RankingStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ShowsRepo {

    protected
        Map<String, ArrayList<LiveShow>> listOfShows;

        Map<String,ShowType> showNameToType;

        Map<String,Integer> listOfShowNameToFreq;

        @Autowired
        RankingStrategy rankingStrategy;

        @Autowired
        RankingStrategyFactory rankingStrategyFactory;

    @Autowired
    public ShowsRepo(){
        listOfShows = new HashMap<>();
        showNameToType = new HashMap<>();
        listOfShowNameToFreq = new HashMap<>();
    }

    public void setRankingStrategy(RankingType rankingType){
        rankingStrategyFactory.setRankingStrategy(rankingType);
        rankingStrategy = rankingStrategyFactory.getRankingStrategy();
    }

    public void addShow(String showName, ShowType showType){
        if(listOfShows.containsKey(showName)){
            System.out.println("Status: Already Registered! -> Show Name: "+showName+" || Show Type: "+showType);
        }
        else{
            System.out.println("Status: Registered! -> Show Name: "+showName+" || Show Type: "+showType);
            listOfShows.put(showName,new ArrayList<>());
            showNameToType.put(showName,showType);
        }
    }

    public ShowType getShowType(String showName){
        return showNameToType.getOrDefault(showName, null);
    }

    public Boolean addShowInSlot(Slot slot, LiveShow show){
        String showName = show.getShowName();
        if(listOfShows.isEmpty() || !listOfShows.containsKey(showName)){
            ArrayList<LiveShow> setOfShows = new ArrayList<>();
            setOfShows.add(show);
            listOfShows.put(showName,setOfShows);
            return true;
        }
        else if(isSlotFree(slot,showName)){
            listOfShows.get(showName).add(show);
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean isShowNamePresent(String showName){
        if(!listOfShows.containsKey(showName)){
            System.out.println("Status: Invalid Show Name -> "+showName+" NOT Registered");
        }
        return listOfShows.containsKey(showName);
    }

    public Boolean isSlotFree(Slot slot, String showName){
        for(LiveShow availableShow : listOfShows.get(showName)){
            if(availableShow.getShowSlot().equals(slot)){
                System.out.println(slot.toString() + " ("+slot.getSlotDetail()
                        +") HRS Slot NOT available for the Show "+showName);
                return false;
            }
        }
        return true;
    }

    public ArrayList<LiveShow> getShowsByShowName(String showName){
        if(listOfShows.containsKey(showName)){
            return listOfShows.get(showName);
        }
        return null;
    }

    public LiveShow getShowBySlotAndName(Slot slot, String showName){
        if(listOfShows.containsKey(showName)){
            for(LiveShow show : listOfShows.get(showName)){
                if(show.getShowSlot() == slot){
                    return show;
                }
            }
        }
        return null;
    }

    public void updateLiveShowBySlot(Slot slot, LiveShow updatedShow){

        String showName = updatedShow.getShowName();

        for(LiveShow show : listOfShows.get(showName)){
            if(show.getShowSlot() == slot){
                int currIndex = listOfShows.get(showName).indexOf(show);
                listOfShows.get(showName).set(currIndex,updatedShow);
                return;
            }
        }

        listOfShows.get(showName).add(updatedShow);
    }

    public void printAllShowsByShowType(ShowType requestedShowType){
        rankingStrategy.displayAllShowsByShowType(requestedShowType,listOfShows);
    }

    public void printAllAvailableShowsByShowType(ShowType requestedShowType){
        rankingStrategy.displayAvailableShowsByShowType(requestedShowType,listOfShows);
    }

    public boolean isSlotFreeForShowName(Slot slot, String showName) {
        for(LiveShow show : listOfShows.get(showName)){
            if(show.getShowSlot() == slot){
                return true;
            }
        }
        System.out.println(slot.toString() + " ("+slot.getSlotDetail()
                +") HRS Slot NOT available for the Show "+showName);
        return true;
    }

    public void updateShowNameToFreq(String showName, Integer numUsers){
        if(!listOfShowNameToFreq.containsKey(showName)){
            listOfShowNameToFreq.put(showName,numUsers);
        }
        else{
            listOfShowNameToFreq.put(showName, listOfShowNameToFreq.get(showName)+numUsers);
        }
    }

    public Map<String,Integer> getListOfShowToFreq(){
        return listOfShowNameToFreq;
    }

}
