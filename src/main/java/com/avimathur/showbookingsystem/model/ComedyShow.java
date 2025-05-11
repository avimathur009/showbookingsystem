package com.avimathur.showbookingsystem.model;

import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.pojo.LiveShow;

public class ComedyShow extends LiveShow {

    public ComedyShow(){
        super();
        type = ShowType.Comedy;
    }

}
