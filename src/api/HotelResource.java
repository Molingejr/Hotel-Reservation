package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static final HotelResource hotelResource = new HotelResource();
    CustomerService customerService = CustomerService.getCustomerService();
    ReservationService reservationService = ReservationService.getReservationService();

    public static HotelResource getHotelResource(){
        return hotelResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        try {
            if (customerService.getCustomer(email) != null){
                System.out.println("Customer with that email already exists!");
            }else {
                customerService.addCustomer(email, firstName, lastName);
                System.out.println("Created customer with email: " + email);
            }
        }catch (IllegalArgumentException e){
            System.out.println("Invalid email. Check your email format please.");
        }
    }
    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer = customerService.getCustomer(customerEmail);

        if (customer == null){
            System.out.println("Customer with email: " + customerEmail + " doesn't exist.");
            return null;
        }

        Reservation reservation = reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
        if (reservation == null){
            System.out.println("Failed to book room id " + room.getRoomNumber() + " for user with email " + customerEmail);
            return null;
        }
        System.out.println("Reservation made for room!");
        return reservation;

    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomerReservations(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }

    public Collection<IRoom> findRecommendedRooms(Date checkIn, Date checkOut) {
        return reservationService.findRecommendedRooms(checkIn, checkOut);
    }
}
