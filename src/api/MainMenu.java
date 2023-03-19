package api;

import model.IRoom;
import model.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    static Scanner scanner = new Scanner(System.in);

    public MainMenu() {
    }
    public static void PrintMainMenu(){
        String mainInput = "";
        do{
            System.out.println("""
                    Main Menu
                    ---------------------------------------- 
                    1. Find and reserve a room\s
                    2. See my reservations\s
                    3. Create an account\s
                    4. Admin\s
                    5. Exit
                    Please select a number for the menu option.""");

            mainInput= scanner.nextLine().strip();
            switch (mainInput) {
                case "1" -> findAndReserveARoom();
                case "2" -> seeMyReservations();
                case "3" -> createAnAccount();
                case "4" -> AdminMenu.PrintAdminMenu();
                case "5" -> System.out.println("Exiting...");
                default -> System.out.println("please enter a valid option");
            }
        }while (!mainInput.equals("4") && !mainInput.equals("5"));

    }

    
    private static void findAndReserveARoom(){
        String input;
        Date checkInDate = new Date();
        Date checkOutDate = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            System.out.println("Enter check in date(mm/dd/yyyy):");
            checkInDate = dateFormat.parse(scanner.nextLine());
            System.out.println("Enter check out date(mm/dd/yyyy):");
            checkOutDate = dateFormat.parse(scanner.nextLine());
            searchRooms(checkInDate,checkOutDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void searchRooms(Date checkInDate, Date checkOutDate){
        List<IRoom> availableRooms= new ArrayList<>();

        availableRooms= HotelResource.getInstance().findARoom(checkInDate, checkOutDate).stream().toList();
        if (!availableRooms.isEmpty()) {
            System.out.println("Following rooms are available for dates from: " + checkInDate.toString() + " to: " + checkOutDate.toString());
            System.out.println(availableRooms);
            reserveARoom(checkInDate,checkOutDate);
        }else{
            System.out.println("No rooms available for given dates..");
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkInDate);
            cal.add(Calendar.DAY_OF_MONTH,7);
            Date newCheckInDate = cal.getTime();
            cal.setTime(checkOutDate);
            cal.add(Calendar.DAY_OF_MONTH,7);
            Date newCheckOutDate = cal.getTime();
            searchRooms(newCheckInDate,newCheckOutDate);
        }

    }
    private static void reserveARoom(Date checkInDate, Date checkOutDate) {
        String input;
        do {
            System.out.println("Do you want to book a room? y/n");
            input = scanner.nextLine().toLowerCase().strip();
            switch (input) {
                case "y" -> {
                    //Reserve a room
                    System.out.println("Enter your email ID(eg. name@domain.com) :");
                    String email = scanner.nextLine().strip();
                    if (!AdminResource.getInstance().customerExists(email)) {
                        createAnAccount();
                    }
                    System.out.println("Enter Room No");
                    String roomNo = scanner.nextLine().strip();
                    System.out.println(HotelResource.getInstance().bookARoom(email, HotelResource.getInstance().getRoom(roomNo), checkInDate, checkOutDate));
                    System.out.println("Thank you for staying with us!!");
                }
                case "n" -> System.out.println("Going back to the menu...");
                default -> System.out.println("please enter a valid option");
            }
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

    }

    private static void seeMyReservations() {
        try {
            System.out.println("Please Enter your Email ID(eg. name@domain.com) :");
            String email = scanner.nextLine();
            List<Reservation> myReservations = (List<Reservation>) HotelResource.getInstance().getCustomersReservations(email);
            for (Reservation listElement : myReservations) {
                System.out.println(listElement.toString());
            }
        } catch (Exception e) {
            System.out.println("Couldn't get the Reservations. Please contact support. ");
        }
    }
    private static void createAnAccount() {

        try {
            System.out.println("Please Enter a first name :");
            String firstName = scanner.nextLine();
            System.out.println("Please Enter a last name :");
            String lastName = scanner.nextLine();
            System.out.println("Please Enter an Email ID(eg. name@domain.com) :");
            String email = scanner.nextLine();
            HotelResource.getInstance().createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully.");
        } catch (Exception e) {
            System.out.println("Couldn't create an account. Please contact support. ");
        }
    }



}


