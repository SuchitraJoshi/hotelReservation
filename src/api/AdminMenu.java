package api;

import model.*;
//import service.CustomerService;
//import service.ReservationService;

import java.util.*;

public class AdminMenu {
    static Scanner adminScanner = new Scanner(System.in);

    public AdminMenu() {
        }
    public static void PrintAdminMenu(){
        String adminInput = "";
        do{
            System.out.println("""
                Admin Menu
                ---------------------------------------
                1. See all Customers\s
                2. See all Rooms\s
                3. See all Reservations\s
                4. Add a Room\s
                5. Back to Main Menu
                Please select a number for the menu option.""");

            adminInput= adminScanner.nextLine().strip();
            switch (adminInput) {
                case "1" -> seeAllCustomers();
                case "2" -> seeAllRooms();
                case "3" -> seeAllReservations();
                case "4" -> addARoom();
                case "5" -> MainMenu.PrintMainMenu();
                default -> System.out.println("please enter a valid option");
            }
        }while (!adminInput.equals("5"));

    }

    private static void addARoom() {
        RoomType roomType = null;
        List<IRoom> rooms = new ArrayList<>();
        String addNew="";
        do {
            try {
                System.out.println("Please Enter the room number :");
                String roomNo = adminScanner.nextLine();
                System.out.println("Please Enter the price($): ");
                Double roomPrice = Double.valueOf(adminScanner.nextLine());
                String input;
                do{
                    System.out.println("please enter 1 for single room or 2 for double room:");
                     input = adminScanner.nextLine().strip();
                    switch (input) {
                        case "1" -> roomType = RoomType.SINGLE;
                        case "2" -> roomType = RoomType.DOUBLE;
                        default -> System.out.println("please enter a valid option");
                    }
                }while (!input.equals("1") && !input.equals("2"));

                Room room = new Room(roomNo, roomPrice, roomType);
                rooms.add(room);
                System.out.println("The room added successfully.");
                do {
                    System.out.println("Do you want to add more rooms? type y for yes / n for no..");
                    String temp = adminScanner.nextLine().toLowerCase().strip();
                    if(temp.equals("y")||temp.equals("n")) {
                        addNew = temp;
                        break;
                    }else {
                        System.out.println("please enter a valid option");
                    }
                } while (!(addNew.equals("y") && !addNew.equals("n")));

            } catch (Exception e) {
                System.out.println("Wrong input!!! Couldn't add the room. Please try again.");
            }
        } while (!(addNew.equals("n")));

        AdminResource.getInstance().addRoom(rooms);
    }

    private static void seeAllReservations() {
        AdminResource.getInstance().displayAllReservations();
    }

    private static void seeAllRooms() {
        List<IRoom> listOfRooms = (List<IRoom>) AdminResource.getInstance().getAllRooms();
        if (listOfRooms.size() > 0) {
            for (IRoom listElement : listOfRooms) {
                System.out.println(listElement.toString());
            }
        } else {
            System.out.println("There are no rooms in the list.");
        }

    }

    public static void seeAllCustomers() {
        List<Customer> listOfCustomers = (List<Customer>) AdminResource.getInstance().getAllCustomers();
        if (listOfCustomers.size() > 0) {
            for (Customer listElement : listOfCustomers) {
                System.out.println(listElement.toString());
            }
        } else {
            System.out.println("There are no customers in the list.");
        }
    }

}
