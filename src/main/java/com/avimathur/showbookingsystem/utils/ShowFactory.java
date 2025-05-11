package com.avimathur.showbookingsystem.utils;

import com.avimathur.showbookingsystem.model.*;
import com.avimathur.showbookingsystem.pojo.LiveShow;

import static com.avimathur.showbookingsystem.constant.ShowType.*;

public class ShowFactory {

    public LiveShow getShow(String showType){

        LiveShow show = null;

        if(valueOf(showType) == Comedy){
            show = new ComedyShow();
        }
        else if(valueOf(showType) == Singing){
            show = new SingingShow();
        }
        else if(valueOf(showType) == Tech){
            show = new TechShow();
        }
        else if(valueOf(showType) == Theatre){
            show = new TheatreShow();
        }
        return show;
    }

    //Comedy,
    //    Theatre,
    //    Tech,
    //    Singing,
    //    Movie;

}
