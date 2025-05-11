package com.avimathur.showbookingsystem.model;

import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.pojo.LiveShow;

public class SingingShow extends LiveShow {

    public SingingShow(){
        super();
        type = ShowType.Singing;
    }

}
