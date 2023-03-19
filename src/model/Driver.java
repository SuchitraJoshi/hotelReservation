package model;

import java.util.Date;

public class Driver {
    public static void main(String[] args){
        Customer customer = new Customer("Abby","Clinton", "a@b.com");
        Room room = new Room("103", 150.0, RoomType.DOUBLE);
        Reservation reservation = new Reservation(customer,room, new Date(2023,06,20,15,00),new Date(2023,06,27,11,00));
        System.out.println(reservation);
    }
}
