package model;

import java.util.Date;
import java.util.Objects;

public class Reservation {

    Customer customer;

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    public Customer getCustomer() {
        return customer;
    }

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.customer = customer;
        this.room = room;
    }

    @Override
    public String toString(){
        return "Customer : " + customer + "\nRoom : " + room +
                "\nCheck in Date :" + checkInDate + "\tCheck out Date :" + checkOutDate +"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return customer.equals(that.customer) && room.equals(that.room) && checkInDate.equals(that.checkInDate) && checkOutDate.equals(that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }
}
