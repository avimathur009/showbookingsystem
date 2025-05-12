package com.avimathur.showbookingsystem.constant;

public enum ShowType {
    Comedy,
    Theatre,
    Tech,
    Singing,
    Movie;

    public static void showShowTypes(){
        for(ShowType showType : ShowType.values()){
            System.out.println(showType.toString());
        }
    }

    public static ShowType fromShowType(String input){
        for(ShowType showType : ShowType.values()){
            if(showType.toString().equals(input)){
                return showType;
            }
        }
        return Comedy;
    }

    public static Boolean checkShowTypeString(String input) {
        for(ShowType showType : ShowType.values()){
            if(showType.toString().equals(input)){
                return true;
            }
        }
        return false;
    }

}
