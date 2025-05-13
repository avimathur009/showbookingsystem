package com.avimathur.showbookingsystem.service;

import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.pojo.LiveShow;

import java.util.ArrayList;
import java.util.Map;

public abstract class RankingStrategy {

    public abstract void displayAllBookableShows(Map<String, ArrayList<LiveShow>> listOfShows);

    public abstract void displayAllShowsByShowType(ShowType showType, Map<String, ArrayList<LiveShow>> listOfShows);

    public abstract void displayAvailableShowsByShowType(ShowType showType, Map<String, ArrayList<LiveShow>> listOfShows);

}
