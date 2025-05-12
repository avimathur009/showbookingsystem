package com.avimathur.showbookingsystem.repository;
import com.avimathur.showbookingsystem.pojo.Booking;
import com.avimathur.showbookingsystem.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookingsRepo {

    Map<User, ArrayList<Booking>> listOfConfirmedBookings;
    Deque<Booking> listOfUnconfirmedBookings;

    public BookingsRepo(){
        listOfUnconfirmedBookings = new ArrayDeque<>();
        listOfConfirmedBookings = new HashMap<>();
    }

    public void registerNewConfirmedBooking(Booking booking){
        User currUser = booking.getUserDetails();
        if(!listOfConfirmedBookings.containsKey(currUser)) {
            ArrayList<Booking> listOfUserBookings = new ArrayList<>();
            listOfUserBookings.add(booking);
            listOfConfirmedBookings.put(currUser,listOfUserBookings);
        }
        else{
            listOfConfirmedBookings.get(currUser).add(booking);
        }
    }

    public void registerNewUnconfirmedBooking(Booking booking){
        listOfUnconfirmedBookings.addLast(booking);
    }

    public ArrayList<Booking> getUsersConfirmedBooking(User user){
        return listOfConfirmedBookings.get(user);
    }

    public Deque<Booking> getListOfUnconfirmedBookings(){
        return listOfUnconfirmedBookings;
    }

}
