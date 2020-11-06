import api.AdminResource;
import model.*;

import java.util.*;

public class AdminMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final AdminResource adminResource = AdminResource.getAdminResource();

    public void openAdminMenu() {

        boolean exit = false;

        while (!exit) {
            System.out.println("1. See all Customers\n" +
                    "2. See all Rooms\n" +
                    "3. See all Reservations\n" +
                    "4. Add a Room\n" +
                    "5. Back to Main Menu");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    SeeAllReservations();
                    break;
                case "4":
                    AddARoom();
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("Yikes! You gotta choice one of the provided options");
            }

        }

    }

    private void AddARoom() {
        System.out.print("Enter the room's number: ");
        String roomNumber = scanner.nextLine();

        System.out.print("Enter the room's price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        RoomType roomType = null;

        boolean exit = false;
        do {
            System.out.println("Which type of room do you want to create.");
            System.out.println("1. Single\n2. Double");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    roomType = RoomType.SINGLE;
                    exit = true;
                    break;
                case "2":
                    roomType = RoomType.DOUBLE;
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter a valid choice");
            }
        } while (!exit);

        IRoom room;
        if (price == 0){
            room = new FreeRoom(roomNumber, roomType);
        }else{
            room = new Room(roomNumber, price, roomType);
        }

        List<IRoom> rooms = new LinkedList<>();
        rooms.add(room);
        adminResource.addRoom(rooms);
    }

    private void SeeAllReservations() {
        adminResource.displayAllReservations();
    }

    private void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        
        if (rooms.isEmpty()){
            System.out.println("Sorry! No available rooms.");
        }

        for (IRoom room: rooms){
            System.out.println(room);
        }
    }

    private void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();

        if (customers.isEmpty()){
            System.out.println("Sorry! No available customers.");
        }

        for (Customer customer: customers){
            System.out.println(customer);
        }
    }
}
