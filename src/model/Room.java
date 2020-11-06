package model;

import java.util.Objects;

public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType roomType;

    public Room (String roomNumber, double price, RoomType roomType){
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return price == 0;
    }

    @Override
    public String toString() {
        return "Room Number:" + roomNumber + " Price:" + price + " Room type:" + roomType;
    }
}
