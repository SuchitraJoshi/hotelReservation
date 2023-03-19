package service;

import model.Customer;

import java.util.*;

 public class CustomerService {
     private static CustomerService INSTANCE;
     private static final Map<String, Customer> mapOfCustomers = new HashMap<>();
    private CustomerService(){

    }
     public static CustomerService getInstance() {
         if(INSTANCE == null) {
             INSTANCE = new CustomerService();
         }

         return INSTANCE;
     }

     public void addCustomer(String email, String firstName, String lastName){
        try{
            Customer newCustomer = new Customer(firstName,lastName,email);
            mapOfCustomers.put(email,newCustomer);
        } catch (IllegalArgumentException e){
            System.out.println("Email address is not valid. Please try again.");
        }

    }
    public Customer getCustomer( String customerEmail){
        return mapOfCustomers.get(customerEmail);
    }

    public boolean isCustomer(String customerEmail){
        return mapOfCustomers.containsKey(customerEmail);
    }
    public static Collection<Customer> getAllCustomers(){
        return mapOfCustomers.values().stream().toList();
    }

}
