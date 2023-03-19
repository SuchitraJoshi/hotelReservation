package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.*;
public class AdminResource {
    private static AdminResource INSTANCE;
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();
    private AdminResource(){

    }
    //get INSTANCE
    public static AdminResource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new AdminResource();
        }
        return INSTANCE;
    }

    public Boolean customerExists(String email){
        return customerService.isCustomer(email);
    }
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms){
            ReservationService.getInstance().addRoom(room);
        }
    }
    public Collection<IRoom> getAllRooms(){
     return reservationService.getAllRooms();
    }
    public Collection<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }
    public void displayAllReservations(){
        ReservationService.printAllReservations();
    }
}
