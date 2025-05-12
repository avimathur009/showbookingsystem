package com.avimathur.showbookingsystem.service;

import com.avimathur.showbookingsystem.constant.RankingType;
import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.constant.Slot;
import com.avimathur.showbookingsystem.pojo.Booking;
import com.avimathur.showbookingsystem.pojo.LiveShow;
import com.avimathur.showbookingsystem.pojo.User;
import com.avimathur.showbookingsystem.repository.BookingsRepo;
import com.avimathur.showbookingsystem.repository.ShowsRepo;
import com.avimathur.showbookingsystem.utils.ShowFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class BookingsManager {

    @Autowired
    private ShowsRepo showsRepo;

    @Autowired
    private BookingsRepo bookingsRepo;

    @Autowired
    private ShowFactory showFactory;

    public void initialize(RankingType rankingStrategy){
        showsRepo.setRankingStrategy(rankingStrategy);
    }

    public Boolean isShowRegistered(String showName){
        return showsRepo.isShowNamePresent(showName);
    }

    public void registerNewLiveShow(String showName, ShowType showType){
        showsRepo.addShow(showName,showType);
    }

    public void addSlotsForLiveShow(String showName, Slot slot, Integer capacity, Integer price){
        ShowType showType = showsRepo.getShowType(showName);
        LiveShow show = showFactory.getShow(showType.toString());
        show.setShowDetails(showName,price,capacity,slot);
        Boolean showAdded = showsRepo.addShowInSlot(slot,show);
        if(showAdded){
            System.out.println("Status: Slot Registered! -> "+"Slot: "+slot.toString()+" ("+slot.getSlotDetail()
                    +") || Capacity: "+capacity);
        }
        else{
            System.out.println("Status: Slot Full - Slot NOT Registered -> "+"Slot: "
                    +slot.toString()+"("+slot.getSlotDetail()+")");
        }
    }

    public void showAllAvailableShowsByType(String type){
        ShowType showType = ShowType.valueOf(type);
        showsRepo.printAllAvailableShowsByShowType(showType);
    }

    public void bookLiveShow(User user, Slot slot, String showName, Integer numPeople){

        boolean slotAvailable = showsRepo.isSlotFreeForShowName(slot,showName);

        if(!slotAvailable){
            return;
        }

        boolean showFound=false;

        Booking newBooking = new Booking();
        newBooking.setBookingDetails(slot,showName,numPeople,user);

        showsRepo.updateShowNameToFreq(showName,numPeople);

        if(!showsRepo.isShowNamePresent(showName)){
            System.out.println("Enter a valid Show Name!");
        }

        for(LiveShow show : showsRepo.getShowsByShowName(showName)){
            if(show.getShowSlot()== slot){
                showFound=true;

                System.out.println("User: "+user.getUserName());
                System.out.println("Requested Show to Book -> "+"Booking ID: "+newBooking.getBookingId()
                        +" || ShowName: "+showName
                        +" || "+slot.getSlotDetail()+" HRS");

                if(show.getCurrCapacity()>=numPeople){
                    show.decreaseCurrCapacity(numPeople);
                    showsRepo.updateLiveShowBySlot(slot,show);
                    bookingsRepo.registerNewConfirmedBooking(newBooking);
                    System.out.println("Booking Status: Booking Done");
                }
                else {
                    bookingsRepo.registerNewUnconfirmedBooking(newBooking);
                    System.out.println("Booking Status: Show capacity full! Added you in Waitlist.");
                }
            }
        }

        if(!showFound){
            System.out.println("Enter a valid Show Name");
        }
    }

    private Booking chooseLiveBookingToCancel(User user){
        Scanner scanner = new Scanner(System.in);

        List<Booking> usersBooking = bookingsRepo.getUsersConfirmedBooking(user);
        System.out.println("Choose the Booking number(#), for which you want to Cancel the show");

        int count=1;
        for(Booking booking : usersBooking){
            System.out.println("Booking #"+count+" -> Booking ID: "+booking.getBookingId()+" || ShowName: "+booking.getShowName()
                    +" || "+booking.getShowSlot().getSlotDetail()+" HRS");
            count++;
        }

        int bookingNum = scanner.nextInt();
        return usersBooking.get(bookingNum-1);
    }

    public void cancelLiveBooking(User user){
        Booking bookingToCancel = chooseLiveBookingToCancel(user);
        System.out.println("Booking To Cancel ->"+ " Booking ID: "+bookingToCancel.getBookingId()
                +" || ShowName: "+bookingToCancel.getShowName()
                +" || "+bookingToCancel.getShowSlot().getSlotDetail()+" HRS");

        Integer numPeople = bookingToCancel.getNumOfUsers();
        Slot slot = bookingToCancel.getShowSlot();
        String showName = bookingToCancel.getShowName();

        LiveShow show = showsRepo.getShowBySlotAndName(slot,showName);

        show.increaseCurrCapacity(numPeople);

        System.out.println("Booking Status: Booking CANCELLED!");
        showsRepo.updateLiveShowBySlot(slot,show);

        showsRepo.updateShowNameToFreq(showName,-numPeople);

        makeBookingAvailableFromWaitList(slot,show);
    }

    public void makeBookingAvailableFromWaitList(Slot slot, LiveShow show){
        System.out.println("Start of Checking Wait-List Users");
        for(Booking booking : bookingsRepo.getListOfUnconfirmedBookings()){
            if(booking.getShowSlot()==slot && booking.getShowName().equals(show.getShowName())){
                if(booking.getNumOfUsers()<=show.getCurrCapacity()){
                    bookLiveShow(booking.getUserDetails(),slot, booking.getShowName(), booking.getNumOfUsers());
                    bookingsRepo.getListOfUnconfirmedBookings().remove(booking);
                }
            }
        }
        System.out.println("End of Checking Wait-List Users");

    }

    public Boolean isSlotAvailable(Slot slot, String showName) {
        return showsRepo.isSlotFreeForShowName(slot,showName);
    }

    public void findTrendingLiveShow(){
        int maxFreq = 0;
        String trendingShowName = "";
        for(String showName : showsRepo.getListOfShowToFreq().keySet()){
            if(maxFreq<showsRepo.getListOfShowToFreq().get(showName)){
                maxFreq=showsRepo.getListOfShowToFreq().get(showName);
                trendingShowName = showName;
            }
        }
        System.out.println("Trending Show: "+trendingShowName);
    }

}
