package com.avimathur.showbookingsystem.utils;

import com.avimathur.showbookingsystem.constant.RankingType;
import com.avimathur.showbookingsystem.constant.Slot;
import com.avimathur.showbookingsystem.service.BookingsManager;

import java.util.Scanner;

public class InputVerifier {

    private static final int maxTries = 5;
    private static final BookingsManager bookingsManager = BookingsManager.getInstance();

    String slotString = "";
    String showNameString = "";
    String rankingStrategyString = "";

    Scanner scanner = new Scanner(System.in);


    public Boolean checkSlotInput() {
        int currTries = 1;
        String slotString = scanner.nextLine();
        boolean isSlotStringCorrect = Slot.checkSlotString(slotString);
        while(!isSlotStringCorrect && currTries<=maxTries){
            System.out.println(slotString + " -> Invalid Slot Selection! Choose Slot from 0000-2300 HRS - Each Of 1 Hour");
            slotString = scanner.nextLine();
            isSlotStringCorrect = Slot.checkSlotString(slotString);
            currTries++;
        }
        if(!isSlotStringCorrect) {
            System.out.println("Maximum Tries Reached -> Invalid Slot Selection");
            return false;
        }
        this.slotString=slotString;
        return true;
    }

    public String getCorrectSlotInput(){
        return slotString;
    }

    public Boolean checkShowNameInput() {
        int currTries = 1;
        String showNameString = scanner.nextLine();
        Boolean isShowPresent = bookingsManager.isShowRegistered(showNameString);
        while(!isShowPresent && currTries<=maxTries){
            System.out.println(showNameString + " -> This show isn't registered yet! Try again!");
            showNameString = scanner.nextLine();
            isShowPresent = bookingsManager.isShowRegistered(showNameString);
            currTries++;
        }
        if(!isShowPresent) {
            System.out.println("Maximum Tries Reached -> Invalid Show Name Selection");
            return false;
        }
        this.showNameString=showNameString;
        return true;
    }

    public String getShowNameString(){
        return this.showNameString;
    }

    public Boolean checkRankingStrategyInput() {
        int currTries = 1;
        String rankingStrategyString = scanner.nextLine();
        boolean isRankingPresent = RankingType.checkRankingString(showNameString);
        while(!isRankingPresent && currTries<=maxTries){
            System.out.println(rankingStrategyString + " -> This Ranking Strategy isn't registered yet! Try again!");
            showNameString = scanner.nextLine();
            isRankingPresent = RankingType.checkRankingString(showNameString);
            currTries++;
        }
        if(!isRankingPresent) {
            System.out.println("Maximum Tries Reached -> Invalid Ranking Strategy Selection");
            return false;
        }
        this.rankingStrategyString=rankingStrategyString;
        return true;
    }

    public String getRankingStrategyString(){
        return this.rankingStrategyString;
    }

}
