package service;

import model.*;
import java.util.*;

public class ServiceDriver {

    public static void main(String[] args){
         CustomerService customerService= CustomerService.getInstance();
        customerService.addCustomer("a@b.com","Abby","Clinton");
        customerService.addCustomer("c@d.com","Sandra","Johnson");
        customerService.addCustomer("e@f.com","Trisha","Donald");

        System.out.println(customerService.getCustomer("c@d.com"));

        System.out.println(service.CustomerService.getAllCustomers());

        /*IRoom room = new Room("103", 150.0, RoomType.DOUBLE);
        Reservation reservation = new Reservation(customer,room, new Date(2023,06,20,15,00),new Date(2023,06,27,11,00));
        System.out.println(reservation);*/
    }
}
