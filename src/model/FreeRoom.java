package model;

import java.util.Objects;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, RoomType roomType){
        super(roomNumber, 0.0, roomType);

    }
    @Override
    public String toString(){
        return super.toString() + " This is a free room....";
    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
