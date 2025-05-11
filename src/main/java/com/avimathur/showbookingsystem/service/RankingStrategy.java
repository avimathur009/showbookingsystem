package com.avimathur.showbookingsystem.service;

import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.pojo.LiveShow;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public abstract class RankingStrategy {

    public abstract void displayAllShows(Map<String, ArrayList<LiveShow>> listOfShows);
    public abstract void displayAllShowsByShowType(ShowType showType, Map<String, ArrayList<LiveShow>> listOfShows);
    public abstract void displayAvailableShowsByShowType(ShowType showType, Map<String, ArrayList<LiveShow>> listOfShows);

}
