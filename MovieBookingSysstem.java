import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static HashMap<String, Integer> movies = new HashMap<>();
    static String seats[] = {
            "A1", "A2", "A3", "A4", "A5",
            "B1", "B2", "B3", "B4", "B5"
    };

    static ArrayList<String> bookings = new ArrayList<>();

    static int ticketCounter = 100;

    public static void main(String[] args) {

        movies.put("LEO", 150);
        movies.put("VIKRAM", 180);
        movies.put("GOAT", 200);

        System.out.println("====================================");
        System.out.println("     SMART CINEMA BOOKING");
        System.out.println("====================================");

        System.out.println("\nAvailable Commands:");
        System.out.println("SHOW      -> Show movies");
        System.out.println("BOOK      -> Book ticket");
        System.out.println("CANCEL    -> Cancel ticket");
        System.out.println("MYTICKET  -> View bookings");
        System.out.println("SEATS     -> Show seats");
        System.out.println("HELP      -> Show commands");
        System.out.println("EXIT      -> Exit program");

        while (true) {

            System.out.print("\nEnter Command: ");
            String command = sc.nextLine().toUpperCase();

            switch (command) {

                case "SHOW":
                    showMovies();
                    break;

                case "BOOK":
                    bookTicket();
                    break;

                case "CANCEL":
                    cancelTicket();
                    break;

                case "MYTICKET":
                    viewBookings();
                    break;

                case "SEATS":
                    displaySeats();
                    break;

                case "HELP":
                    help();
                    break;

                case "EXIT":
                    System.out.println("Thank You For Visiting!");
                    return;

                default:
                    System.out.println("Invalid Command!");
            }
        }
    }

    // SHOW MOVIES
    public static void showMovies() {

        System.out.println("\n===== MOVIES AVAILABLE =====");
        System.out.println("LEO     - 10:00 AM - Rs.150");
        System.out.println("VIKRAM  - 02:00 PM - Rs.180");
        System.out.println("GOAT    - 06:00 PM - Rs.200");
    }

    // DISPLAY SEATS
    public static void displaySeats() {

        System.out.println("\n========== SCREEN ==========");

        for (int i = 0; i < seats.length; i++) {

            System.out.print("[" + seats[i] + "] ");

            if ((i + 1) % 5 == 0)
                System.out.println();
        }
    }

    // BOOK TICKET
    public static void bookTicket() {

        System.out.print("\nEnter Customer Name: ");
        String name = sc.nextLine();

        showMovies();

        System.out.print("\nEnter Movie Name: ");
        String movie = sc.nextLine().toUpperCase();

        if (!movies.containsKey(movie)) {
            System.out.println("Movie Not Found!");
            return;
        }

        displaySeats();

        System.out.print("\nEnter Seat Number: ");
        String seat = sc.nextLine().toUpperCase();

        boolean found = false;

        for (int i = 0; i < seats.length; i++) {

            if (seats[i].equals(seat)) {

                found = true;

                seats[i] = "X";

                String ticketId = "MOV" + ticketCounter++;

                String booking =
                        ticketId + " | " +
                        name + " | " +
                        movie + " | " +
                        seat + " | Rs." +
                        movies.get(movie);

                bookings.add(booking);

                System.out.println("\n==============================");
                System.out.println("     TICKET CONFIRMED");
                System.out.println("==============================");
                System.out.println("Ticket ID : " + ticketId);
                System.out.println("Customer  : " + name);
                System.out.println("Movie     : " + movie);
                System.out.println("Seat      : " + seat);
                System.out.println("Amount    : Rs." + movies.get(movie));
                System.out.println("==============================");

                break;
            }
        }

        if (!found) {
            System.out.println("Seat Not Available!");
        }
    }

    // CANCEL TICKET
    public static void cancelTicket() {

        if (bookings.size() == 0) {
            System.out.println("No Bookings Available!");
            return;
        }

        System.out.print("Enter Ticket ID: ");
        String id = sc.nextLine().toUpperCase();

        boolean found = false;

        for (int i = 0; i < bookings.size(); i++) {

            String booking = bookings.get(i);

            if (booking.startsWith(id)) {

                String data[] = booking.split("\\|");

                String seat = data[3].trim();

                for (int j = 0; j < seats.length; j++) {

                    if (seats[j].equals("X")) {
                        seats[j] = seat;
                        break;
                    }
                }

                bookings.remove(i);

                System.out.println("Ticket Cancelled Successfully!");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Ticket ID Not Found!");
        }
    }

    // VIEW BOOKINGS
    public static void viewBookings() {

        if (bookings.size() == 0) {
            System.out.println("No Bookings Available!");
            return;
        }

        System.out.println("\n====== YOUR BOOKINGS ======");

        for (String booking : bookings) {
            System.out.println(booking);
        }
    }

    // HELP
    public static void help() {

        System.out.println("\nAvailable Commands:");
        System.out.println("SHOW");
        System.out.println("BOOK");
        System.out.println("CANCEL");
        System.out.println("MYTICKET");
        System.out.println("SEATS");
        System.out.println("HELP");
        System.out.println("EXIT");
    }
}