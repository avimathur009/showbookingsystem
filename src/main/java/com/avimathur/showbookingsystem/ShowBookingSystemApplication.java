package com.avimathur.showbookingsystem;

import com.avimathur.showbookingsystem.constant.RankingType;
import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.constant.Slot;
import com.avimathur.showbookingsystem.pojo.User;
import com.avimathur.showbookingsystem.repository.UserRepo;
import com.avimathur.showbookingsystem.service.BookingsManager;
import com.avimathur.showbookingsystem.utils.InputVerifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ShowBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowBookingSystemApplication.class, args);

		BookingsManager bookingsManager = BookingsManager.getInstance();
		UserRepo userRepo = UserRepo.getInstance();

		InputVerifier verifyInput = new InputVerifier();
		Scanner scanner = new Scanner(System.in);

		System.out.println("OPENING THE DAY");

        System.out.println("Choose Ranking Strategy from these available strategies");
        RankingType.showRankingStrategies();
        if(!verifyInput.checkRankingStrategyInput()){
            return;
        }
        RankingType rankingType = RankingType.fromRankingDetails(verifyInput.getRankingStrategyString());
        bookingsManager.initialize(rankingType);

		System.out.println("Enter Instruction");
		String instruction = scanner.nextLine();

        label:
        while(!instruction.equals("STOP")){
            switch (instruction) {
                case "registerShow" -> {
                    System.out.println("Choose ShowType");
                    String type = scanner.nextLine();
                    ShowType showType = ShowType.valueOf(type);
                    System.out.println("Choose ShowName");
                    String showName = scanner.nextLine();
                    bookingsManager.registerNewLiveShow(showName, showType);
                }
                case "registerShowSlots" -> {
                    System.out.println("Choose ShowName");
                    if(!verifyInput.checkShowNameInput()){
						break;
					}
					String showName = verifyInput.getShowNameString();
                    System.out.println("Choose number of slots to add");
                    int numOfSlots = scanner.nextInt();
                    for (int i = 1; i <= numOfSlots; i++) {
						System.out.println("Enter Details for Slot#"+i);
                        System.out.println("Choose ShowPrice");
                        Integer price = scanner.nextInt();
						scanner.nextLine();
                        System.out.println("Choose Slot from 0000-2300 HRS - Each Of 1 Hour");
						if(!verifyInput.checkSlotInput()){
							continue;
						}
                        Slot slot = Slot.fromSlotDetails(verifyInput.getCorrectSlotInput());
						Boolean isSlotAvailable = bookingsManager.isSlotAvailable(slot,showName);
						if(!isSlotAvailable){
							continue;
						}
                        System.out.println("Choose the capacity of the show");
                        Integer capacity = scanner.nextInt();
                        bookingsManager.addSlotsForLiveShow(showName, slot, capacity, price);
                    }
                }
                case "bookTicket" -> {
                    System.out.println("Enter your name");
                    String userName = scanner.nextLine();
                    User user = userRepo.getUser(userName);
					System.out.println("Enter Show Name");
					String showName = scanner.nextLine();
					System.out.println("Enter number of People you want to do booking for");
					Integer numPeople = scanner.nextInt();
					scanner.nextLine();
                    System.out.println("Choose ShowSlot from Slot0 - Slot23 (Each Of 1 Hour)");
					if(!verifyInput.checkSlotInput()){
						continue;
					}
					Slot slot = Slot.valueOf(verifyInput.getCorrectSlotInput());
					bookingsManager.bookLiveShow(user,slot,showName,numPeople);
                }
                case "showAvailableShowsByGenre" -> {
					System.out.println("Enter Show Type");
					String type = scanner.nextLine();
					bookingsManager.showAllAvailableShowsByType(type);
                }
                case "cancelBooking" -> {
					System.out.println("Enter your name");
					String name = scanner.nextLine();
					User user = userRepo.getUser(name);
					bookingsManager.cancelLiveBooking(user);
                }
                case "trendingLiveShow" -> {
                    bookingsManager.findTrendingLiveShow();
                }
            }
            System.out.println("Enter next Instruction");
            instruction = scanner.nextLine();
        }
	}

}
