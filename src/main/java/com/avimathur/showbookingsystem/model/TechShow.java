package com.avimathur.showbookingsystem.model;

import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.pojo.LiveShow;

public class TechShow extends LiveShow {

    public TechShow(){
        super();
        type = ShowType.Tech;
    }

}
