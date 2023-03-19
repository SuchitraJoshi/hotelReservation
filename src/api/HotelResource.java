package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.*;
public class HotelResource {
    // Static reference
    private static HotelResource INSTANCE;
    // Get instances of CustomerService and ReservationService classes
    private static CustomerService customerService = CustomerService.getInstance();
    private static ReservationService reservationService = ReservationService.getInstance();
    // private constructor
    private HotelResource(){

    }
    //get INSTANCE
    public static HotelResource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new HotelResource();
        }
        return INSTANCE;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email,firstName,lastName);
    }
    public IRoom getRoom(String roomNumber){
       return reservationService.getARoom(roomNumber);
    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }
    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return reservationService.getCustomersReservation(getCustomer(customerEmail));
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){

        return reservationService.findRooms(checkIn,checkOut);
    }
}
