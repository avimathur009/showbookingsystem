package com.avimathur.showbookingsystem.utils;

import com.avimathur.showbookingsystem.constant.RankingType;
import com.avimathur.showbookingsystem.service.DefaultRankingStrategy;
import com.avimathur.showbookingsystem.service.RankingStrategy;
import org.springframework.stereotype.Component;

@Component
public class RankingStrategyFactory {

    RankingStrategy rankingStrategy;

    public RankingStrategy getRankingStrategy(){
        return rankingStrategy;
    }

    public void setRankingStrategy(RankingType rankingType){
        if(rankingType == RankingType.Default){
            rankingStrategy = new DefaultRankingStrategy();
        }
        else{
            rankingStrategy=null;
        }
    }

}
