package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;


public class ReservationService {

    Collection<Reservation> reservations = new LinkedList<>();  // our reservation container
    Collection<IRoom> rooms = new LinkedList<>();  // our room container

    private static final ReservationService reservationService = new ReservationService();

    public static ReservationService getReservationService(){ return reservationService; }

    public void addRoom(IRoom room){
        rooms.add(room);
    }

    public IRoom getARoom(String roomId){
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public Collection<IRoom> getAllRooms(){
        return rooms;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> openRooms = new LinkedList<>();

        // Want to ensure the date hasn't passed yet
        Date today = new Date();
        if (today.after(checkOutDate)){
            System.out.println("Your requested date range has already passed.");
            return openRooms;
        }

        // Find open rooms
        for (IRoom room: rooms){
            boolean reserved = false;
            for (Reservation reservation: reservations){
                if (reservation.getRoom().equals(room) && reservation.getCheckOutDate().after(checkInDate)){
                    reserved = true;
                    break;
                }
            }
            if (!reserved){
                openRooms.add(room);
            }
        }
        return openRooms;
    }

    public Collection<IRoom> findRecommendedRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> recommendedRooms = findRooms(checkInDate, checkOutDate);

        // Get the last 3 rooms as recommended rooms
        for (IRoom room: recommendedRooms){
            if (recommendedRooms.size() > 3){
                recommendedRooms.remove(room);
            }
        }
        return recommendedRooms;
    }

    public Collection<Reservation> getCustomerReservations(Customer customer){
        Collection<Reservation> filteredReservations = new LinkedList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                filteredReservations.add(reservation);
            }
        }
        return filteredReservations;
    }

    public void printAllReservations(){
        System.out.println("Here are the reservations: ");

        if (reservations.isEmpty()){
            System.out.println("Sorry! No available reservation.");
        }

        for (Reservation reservation: reservations){
            System.out.println(reservation);
        }
    }

}

