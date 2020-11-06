import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final HotelResource hotelResource = HotelResource.getHotelResource();

    public void openMainMenu(){

        boolean exit = false;

        while(!exit){
            System.out.println("1. Find and reserve a room\n" +
                    "2. See my reservations\n" +
                    "3. Create an account\n" +
                    "4. Admin\n" +
                    "5. Exit");
            String option = scanner.nextLine();

            switch (option){
                case "1":
                    findAndReserveARoom();
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAnAccount();
                    break;
                case "4":
                    openAdminMenu();
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("Yikes! You gotta choice one of the provided options");
            }

        }

    }

    private void findAndReserveARoom() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Date checkInDate;
        Date checkOutDate;

        for(;;){
            try{
                System.out.println("Enter your check in date (MM/dd/yyyy): ");
                String date = scanner.nextLine();
                checkInDate = dateFormat.parse(date);
                break;
            }catch (Exception e){
                System.out.println("Your date format is wrong! Enter in this format MM/dd/yyyy");
            }
        }

        for(;;){
            try{
                System.out.println("Enter your check out date (MM/dd/yyyy): ");
                String date = scanner.nextLine();
                checkOutDate = dateFormat.parse(date);
                break;
            }catch (Exception e){
                System.out.println("Your date format is wrong! Enter in this format MM/dd/yyyy");
            }
        }

        Collection<IRoom> rooms = hotelResource.findARoom(checkInDate, checkOutDate);

        if (rooms.isEmpty()){
            System.out.println("No room available for your provided date.");
            return;
        }

        System.out.println("Here are the available rooms on that date range.");
        for (IRoom room: rooms){
            System.out.println(room);
        }

        Collection<IRoom> recommendedRooms = hotelResource.findRecommendedRooms(checkInDate, checkOutDate);

        System.out.println("We recommend these rooms below. ");
        for (IRoom room: recommendedRooms){
            System.out.println(room);
        }

        System.out.println("Enter your customer email: ");
        String email = scanner.nextLine();

        System.out.println("You seen what we have available. Enter a room number to book: ");
        String roomNumber = scanner.nextLine();
        IRoom room = hotelResource.getRoom(roomNumber);

        Reservation reservation = hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
        System.out.println(reservation);

        System.out.println("Thank you for booking with us.");

    }

    private void seeMyReservations(){
        System.out.println("Enter customer email: ");
        String email = scanner.nextLine();
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);

        if (reservations.isEmpty()){
            System.out.println("Sorry! No available reservation.");
        }

        for (Reservation reservation: reservations){
            System.out.println(reservation);
        }
    }

    private void createAnAccount(){
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        hotelResource.createACustomer(email, firstName, lastName);
    }

    private void openAdminMenu(){
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.openAdminMenu();
    }
}
