package model;

import java.util.Objects;

public class Room implements IRoom{
   private final String roomNumber;
    private final Double price;
    private final RoomType roomType;
    private final boolean isFree;
   public Room(String roomNumber, Double price, RoomType roomType){
      this.roomNumber = roomNumber;
      this.roomType = roomType;
      this.price = price;
      if(price == 0){
          this.isFree = true;
      } else {
          this.isFree = false;
      }
   }
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public RoomType getRoomType() { return roomType; }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public Boolean isFree() {
        return isFree;
    }
    @Override
    public String toString(){
        return "Room Number : " + roomNumber + "\tType: " + roomType + "\tPrice(per night) : $" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber) && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, roomType);
    }
}
