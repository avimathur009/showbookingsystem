package com.avimathur.showbookingsystem.utils;

import com.avimathur.showbookingsystem.constant.RankingType;
import com.avimathur.showbookingsystem.service.DefaultRankingStrategy;
import com.avimathur.showbookingsystem.service.RankingStrategy;

public class RankingStrategyFactory {

    RankingStrategy rankingStrategy;

    public RankingStrategyFactory(RankingType rankingType){
        if(rankingType == RankingType.Default){
            rankingStrategy = new DefaultRankingStrategy();
        }
        else{
            rankingStrategy=null;
        }
    }

    public RankingStrategy getRankingStrategy(){
        return rankingStrategy;
    }

}
