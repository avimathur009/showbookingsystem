package com.avimathur.showbookingsystem.model;

import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.pojo.LiveShow;

public class TheatreShow extends LiveShow {

    public TheatreShow(){
        super();
        type = ShowType.Theatre;
    }

}
