import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;



class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Reservation {
    private int reservationId;
    private Room room;
    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalPrice;
    private boolean isPaid;

    public Reservation(int reservationId, Room room, String guestName, Date checkInDate, Date checkOutDate, double totalPrice) {
        this.reservationId = reservationId;
        this.room = room;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.isPaid = false;
    }

    
    public int getReservationId() {
        return reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}

class Hotel {
    private String name;
    private String address;
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
       
        for (int i = 1; i <= 10; i++) {
            rooms.add(new Room(i, "Single"));
            rooms.add(new Room(i + 10, "Double"));
            rooms.add(new Room(i + 20, "Suite"));
        }
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(Room room, String guestName, Date checkInDate, Date checkOutDate, double totalPrice) {
        room.setAvailable(false);
        Reservation reservation = new Reservation(reservations.size() + 1, room, guestName, checkInDate, checkOutDate, totalPrice);
        reservations.add(reservation);
        return reservation;
    }

    public void cancelReservation(Reservation reservation) {
        reservation.getRoom().setAvailable(true);
        reservations.remove(reservation);
    }

    public void processPayment(Reservation reservation) {
        // Logic for payment processing
        reservation.setPaid(true);
    }

    public void viewBookingDetails(Reservation reservation) {
        // Display booking details
        System.out.println("Booking ID: " + reservation.getReservationId());
        System.out.println("Guest Name: " + reservation.getGuestName());
        System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
        System.out.println("Check-in Date: " + reservation.getCheckInDate());
        System.out.println("Check-out Date: " + reservation.getCheckOutDate());
        System.out.println("Total Price: " + reservation.getTotalPrice());
        System.out.println("Paid: " + (reservation.isPaid() ? "Yes" : "No"));
    }

   
}





public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Hotel Name", "Hotel Address"); // Providing appropriate values for the Hotel constructor
        Scanner scanner = new Scanner(System.in);
System.out.println("Welcome, Ansh's Residence.");
System.out.println("Enter your name:");
String a=scanner.next();
System.out.println("Namaste"+a);
System.out.println("1.Press 1 to Book Room and 2. Exit");
int s=scanner.nextInt();
if(s==1){
System.out.println("Enter the duration of stay :");
int sc=scanner.nextInt();
 System.out.println("Enter start date of stay:");
 String ho=scanner.next();
 System.out.println("Enter end date of stay:");
 String ea=scanner.next();

        // Display available rooms
        List<Room> availableRooms = hotel.getAvailableRooms();
        System.out.println("Available Rooms:");
        for (Room room : availableRooms) {
            System.out.println(room.getRoomNumber() + " - " + room.getCategory());
        }

        // Ask user to choose a room
        System.out.print("Enter room number to reserve: ");
        int roomNumber = scanner.nextInt();

        // Find the selected room
        Room selectedRoom = null;
        for (Room room : availableRooms) {
            if (room.getRoomNumber() == roomNumber) {
                selectedRoom = room;
                break;
            }
        }

        // Make reservation if room is found
        if (selectedRoom != null) {
              
            // Example check-in and check-out dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkInDate = new Date(); // Today's date
            Date checkOutDate = null;
            try {
                checkOutDate = dateFormat.parse("2024-02-22"); // Example check-out date
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Reservation reservation = hotel.makeReservation(selectedRoom, a, checkInDate, checkOutDate, 0.0); // Passing additional parameters
            System.out.println("Reservation successful! Your reservation ID is: " + reservation.getReservationId());
            System.out.println("Stay Details:\n  Duration: "+sc +"days\n Check-in:"+ ho+"\n Check-out:"+ea +"days");
            System.out.println();
        } else {
            System.out.println("Invalid room number.");
        }
    }
    else
    {
        System. exit(0);
    }

        scanner.close();
    }
}
