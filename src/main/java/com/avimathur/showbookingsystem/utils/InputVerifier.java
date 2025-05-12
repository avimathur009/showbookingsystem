package com.avimathur.showbookingsystem.utils;

import com.avimathur.showbookingsystem.constant.RankingType;
import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.constant.Slot;
import com.avimathur.showbookingsystem.service.BookingsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputVerifier {

    private static final int maxTries = 5;

    @Autowired
    private BookingsManager bookingsManager;

    String slotString = "";
    String showNameString = "";
    String rankingStrategyString = "";
    String showTypeString = "";

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
        boolean isRankingPresent = RankingType.checkRankingString(rankingStrategyString);
        while(!isRankingPresent && currTries<=maxTries){
            System.out.println(rankingStrategyString + " -> This Ranking Strategy isn't registered yet! Try again!");
            rankingStrategyString = scanner.nextLine();
            isRankingPresent = RankingType.checkRankingString(rankingStrategyString);
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

    public boolean checkShowTypeInput() {
        int currTries = 1;
        String showTypeString = scanner.nextLine();
        boolean isShowTypeStringCorrect = ShowType.checkShowTypeString(showTypeString);
        while(!isShowTypeStringCorrect && currTries<=maxTries){
            System.out.println(showTypeString
                    + " -> Invalid Show Type Selection! Choose From these Show Types.");
            ShowType.showShowTypes();
            showTypeString = scanner.nextLine();
            isShowTypeStringCorrect = ShowType.checkShowTypeString(showTypeString);
            currTries++;
        }
        if(!isShowTypeStringCorrect) {
            System.out.println("Maximum Tries Reached -> Invalid Show Type Selection");
            return false;
        }
        this.showTypeString=showTypeString;
        return true;
    }

    public String getShowTypeInput() {
        return showTypeString;
    }
}
