package service;

import model.*;
import java.util.*;
import java.util.stream.Collectors;


public class  ReservationService {
    private static ReservationService INSTANCE;
    private static final Map<String, IRoom> mapOfRooms = new HashMap<>();

    private static final List<Reservation> listOfReservations = new LinkedList<>();
    //private static final Map<String, Reservation> mapOfReservations = new HashMap<>();

    private ReservationService(){

    }
    public static ReservationService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ReservationService();
        }

        return INSTANCE;
    }

    public void addRoom(IRoom room){
        mapOfRooms.put(room.getRoomNumber(),room);
    }
    public IRoom getARoom(String roomId){
        return mapOfRooms.get(roomId);
    }
    public Collection<IRoom> getAllRooms(){
        return mapOfRooms.values().stream().toList();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        if (checkAvailability(checkInDate,checkOutDate,room)) {
            Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
            listOfReservations.add(reservation);
            return reservation;
        }
        return null;
    }

    private boolean checkAvailability(Date checkInDate, Date checkOutDate, IRoom room) {
        //both check in and check out > today and checkout date > check in date
        // check in date != checkout date,
        // check in > Rcheckout or checkout< rcheckin
        // check in mapofrooms and reservations
        if (checkInDate.before(new Date()) || checkOutDate.before(checkInDate) || checkInDate.equals(checkOutDate) || checkOutDate.before(new Date())) {
            return false;
        }
        if(listOfReservations.isEmpty()) {
            return true;
            }
        for (Reservation reservation : listOfReservations) {
            if (reservation.getRoom().equals(room)){
                if ((checkInDate.after(reservation.getCheckOutDate()) || checkOutDate.before(reservation.getCheckInDate()))){
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> noRooms = new ArrayList<>();
        if (checkInDate.before(new Date()) || checkOutDate.before(checkInDate)) {
            return noRooms;
        }
        if (checkInDate.equals(checkOutDate) || checkInDate.after(checkOutDate)){
            return noRooms;
        }
        if(listOfReservations.isEmpty()) {
            return mapOfRooms.values().stream().toList();
        }
        List<IRoom> unAvailableRooms = listOfReservations.stream().filter(r -> checkInDate.before(r.getCheckOutDate()) && checkOutDate.after(r.getCheckInDate())).map(Reservation::getRoom).collect(Collectors.toList());
        List<IRoom> availableRooms = mapOfRooms.values().stream().filter(r -> !unAvailableRooms.contains(r)).collect(Collectors.toList());

//        for (IRoom room : mapOfRooms.values()) {
//          for (Reservation reservation : listOfReservations) {
//              if (reservation.getRoom().equals(room)) {
//                  if ((checkInDate.after(reservation.getCheckOutDate()) || checkOutDate.before(reservation.getCheckInDate()))) {
//                    availableRooms.add(room);
//                }
//            }
//        }

        return availableRooms;
    }


    public Collection<Reservation> getCustomersReservation(Customer customer){
        List<Reservation> tempList = new LinkedList<>();
        for (Reservation listElement : listOfReservations) {
            if (listElement.getCustomer().equals(customer)){
                tempList.add(listElement);
            }
        }
        return tempList;
    }


    public static void printAllReservations(){
        if (!listOfReservations.isEmpty()) {
            for (Reservation listElement : listOfReservations) {
                System.out.println(listElement.toString());
            }
        }else{
                System.out.println("There are no reservations in the system.");
            }
        }



}
