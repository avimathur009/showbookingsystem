package com.avimathur.showbookingsystem;

import com.avimathur.showbookingsystem.constant.RankingType;
import com.avimathur.showbookingsystem.constant.ShowType;
import com.avimathur.showbookingsystem.constant.Slot;
import com.avimathur.showbookingsystem.pojo.User;
import com.avimathur.showbookingsystem.repository.ShowsRepo;
import com.avimathur.showbookingsystem.repository.UserRepo;
import com.avimathur.showbookingsystem.service.BookingsManager;
import com.avimathur.showbookingsystem.utils.InputVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ShowBookingSystemApplication implements CommandLineRunner {

    @Autowired
    private BookingsManager bookingsManager;

    @Autowired
    private ShowsRepo showsRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private InputVerifier verifyInput;

    public static void main(String[] args) {
        SpringApplication.run(ShowBookingSystemApplication.class, args);
    }

    @Override
	public void run(String[] args) {

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

        Instructions:
        while(!instruction.equals("STOP")){

            switch (instruction) {
                case "registerShow" -> {
                    System.out.println("Choose ShowType");
                    if(verifyInput.checkShowTypeInput()){
                        break;
                    }
                    ShowType showType = ShowType.valueOf(verifyInput.getShowTypeInput());
                    System.out.println("Choose ShowName");
                    String showName = scanner.nextLine().trim();
                    bookingsManager.registerNewLiveShow(showName, showType);
                }
                case "registerShowSlots" -> {
                    System.out.println("Choose Show Name from these Registered Shows");
                    showsRepo.printAllRegisteredShows();
                    if(!verifyInput.checkShowNameInput()){
						break;
					}
					String showName = verifyInput.getShowNameString();
                    System.out.println("Choose number of slots to add");
                    int numOfSlots = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 1; i <= numOfSlots; i++) {
						System.out.println("Enter Details for Slot#"+i);
                        System.out.println("Choose Slot from 0000-2300 HRS - Each Of 1 Hour");
						if(!verifyInput.checkSlotInput()){
							continue;
						}
                        Slot slot = Slot.fromSlotDetails(verifyInput.getCorrectSlotInput());
						Boolean isSlotAvailable = bookingsManager.isSlotAvailableForShowName(slot,showName);
						if(!isSlotAvailable){
							continue;
						}
                        System.out.println("Choose the capacity of the show");
                        Integer capacity = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Choose ShowPrice");
                        Integer price = scanner.nextInt();
                        scanner.nextLine();
                        bookingsManager.addSlotsForLiveShow(showName, slot, capacity, price);
                    }
                }
                case "bookTicket" -> {
                    System.out.println("Enter your name");
                    String userName = scanner.nextLine().trim();
                    User user = userRepo.getUser(userName);
					System.out.println("Choose Show Name from available Shows");
                    showsRepo.printAllBookableShows();
                    System.out.println("Note: If Booking is not confirmed, you'll be put in Waitlist");
					String showName = scanner.nextLine().trim();
                    Boolean showRegistered = bookingsManager.isShowRegistered(showName);
                    if(!showRegistered){
                        break;
                    }
					System.out.println("Enter number of People you want to do booking for");
					Integer numPeople = scanner.nextInt();
					scanner.nextLine();
                    System.out.println("Choose ShowSlot from Slot0 - Slot23 (Each Of 1 Hour)");
					if(!verifyInput.checkSlotInput()){
						break;
					}
                    Slot slot = Slot.fromSlotDetails(verifyInput.getCorrectSlotInput());
                    Boolean isShowRegisteredForSlot = bookingsManager.isShowRegisteredForSlot(slot,showName);
                    if(!isShowRegisteredForSlot){
                        break;
                    }
					bookingsManager.bookLiveShow(user,slot,showName,numPeople);
                }
                case "showAvailableShowsByGenre" -> {
					System.out.println("Enter Show Type");
                    if(verifyInput.checkShowTypeInput()){
                        break;
                    }
					bookingsManager.showAllAvailableShowsByType(verifyInput.getShowTypeInput());
                }
                case "cancelBooking" -> {
					System.out.println("Enter your name");
					String name = scanner.nextLine().trim();
                    Boolean userPresent = userRepo.isUserPresent(name);
                    if(!userPresent){
                        System.out.println("Can't Cancel!");
                        break;
                    }
					User user = userRepo.getUser(name);
					bookingsManager.cancelLiveBooking(user);
                }
                case "trendingLiveShow" -> {
                    bookingsManager.findTrendingLiveShow();
                }
                default -> {
                    System.out.println("Choose from these instructions: ");
                    System.out.println("registerShow || registerShowSlots || bookTicket || showAvailableShowsByGenre " +
                            "|| cancelBooking || trendingLiveShow");
                }
            }

            System.out.println("Enter next Instruction");
            instruction = scanner.nextLine().trim();
        }

        System.out.println("END OF THE DAY");
	}
}
