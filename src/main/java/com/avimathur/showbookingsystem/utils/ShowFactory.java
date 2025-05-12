package com.avimathur.showbookingsystem.utils;

import com.avimathur.showbookingsystem.model.*;
import com.avimathur.showbookingsystem.pojo.LiveShow;
import org.springframework.stereotype.Component;

import static com.avimathur.showbookingsystem.constant.ShowType.*;

@Component
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
        else if(valueOf(showType) == Movie){
            show = new MovieShow();
        }
        return show;
    }

}
