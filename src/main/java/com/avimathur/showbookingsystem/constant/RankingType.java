package com.avimathur.showbookingsystem.constant;

public enum RankingType {
    Default;

    public static void showRankingStrategies(){
        for(RankingType rankingType : RankingType.values()){
            System.out.println(rankingType.toString());
        }
    }

    public static RankingType fromRankingDetails(String input){
        for(RankingType rankingType : RankingType.values()){
            if(rankingType.toString().equals(input)){
                return rankingType;
            }
        }
        return Default;
    }

    public static Boolean checkRankingString(String input) {
        for(RankingType rankingType : RankingType.values()){
            if(rankingType.toString().equals(input)){
                return true;
            }
        }
        return false;
    }

}
