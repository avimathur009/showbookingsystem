package com.avimathur.showbookingsystem.model;

import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.pojo.LiveShow;

public class MovieShow extends LiveShow {

    public MovieShow(){
        super();
        type = ShowType.Movie;
    }

}
