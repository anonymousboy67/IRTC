package org.example;

import org.example.entities.Train;
import org.example.entities.User;
import org.example.services.UserBookingService;
import org.example.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        Train trainSelectedForBooking = null; // Moved outside loop and initialized as null
        UserBookingService userBookingService;

        try {
            userBookingService = new UserBookingService();
        } catch (IOException ex) {
            System.out.println("There is something wrong with initializing the service: " + ex.getMessage());
            return;
        }

        while (option != 7) {
            System.out.println("\n=== Train Booking System ===");
            System.out.println("Choose option:");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            System.out.print("Enter your choice: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Enter the username to signup: ");
                    String nameToSignUp = scanner.nextLine().trim();
                    System.out.print("Enter the password to signup: ");
                    String passwordToSignUp = scanner.nextLine().trim();

                    if (nameToSignUp.isEmpty() || passwordToSignUp.isEmpty()) {
                        System.out.println("Username and password cannot be empty!");
                        break;
                    }

                    User userToSignup = new User(nameToSignUp, passwordToSignUp,
                            UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(),
                            UUID.randomUUID().toString());

                    try {
                        userBookingService.signUp(userToSignup);
                        System.out.println("User signed up successfully!");
                    } catch (Exception e) {
                        System.out.println("Error during signup: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter the username to Login: ");
                    String nameToLogin = scanner.nextLine().trim();
                    System.out.print("Enter the password to login: ");
                    String passwordToLogin = scanner.nextLine().trim();

                    if (nameToLogin.isEmpty() || passwordToLogin.isEmpty()) {
                        System.out.println("Username and password cannot be empty!");
                        break;
                    }

                    User userToLogin = new User(nameToLogin, passwordToLogin,
                            UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(),
                            UUID.randomUUID().toString());

                    try {
                        userBookingService = new UserBookingService(userToLogin);
                        System.out.println("Login successful!");
                    } catch (IOException ex) {
                        System.out.println("Login failed: " + ex.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Fetching your bookings...");
                    try {
                        userBookingService.fetchBookings();
                    } catch (Exception e) {
                        System.out.println("Error fetching bookings: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Type your source station: ");
                    String source = scanner.nextLine().trim();
                    System.out.print("Type your destination station: ");
                    String dest = scanner.nextLine().trim();

                    if (source.isEmpty() || dest.isEmpty()) {
                        System.out.println("Source and destination cannot be empty!");
                        break;
                    }

                    try {
                        List<Train> trains = userBookingService.getTrains(source, dest);

                        if (trains.isEmpty()) {
                            System.out.println("No trains found for this route from " + source + " to " + dest);
                            break;
                        }

                        System.out.println("\nAvailable trains:");
                        for (int i = 0; i < trains.size(); i++) {
                            Train t = trains.get(i);
                            System.out.println((i + 1) + ". Train ID: " + t.getTrainId());
                            System.out.println("   Schedule:");
                            for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                                System.out.println("   Station: " + entry.getKey() + " - Time: " + entry.getValue());
                            }
                            System.out.println();
                        }

                        System.out.print("Select a train by typing 1, 2, 3... (or 0 to go back): ");
                        int trainChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (trainChoice == 0) {
                            System.out.println("Going back to main menu...");
                        } else if (trainChoice > 0 && trainChoice <= trains.size()) {
                            trainSelectedForBooking = trains.get(trainChoice - 1);
                            System.out.println("Train selected: " + trainSelectedForBooking.getTrainId());
                        } else {
                            System.out.println("Invalid train selection!");
                        }
                    } catch (Exception e) {
                        System.out.println("Error searching trains: " + e.getMessage());
                    }
                    break;

                case 5:
                    if (trainSelectedForBooking == null) {
                        System.out.println("Please search and select a train first (option 4)!");
                        break;
                    }

                    try {
                        System.out.println("Available seats for train: " + trainSelectedForBooking.getTrainId());
                        List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);

                        System.out.println("Seat map (0 = available, 1 = booked):");
                        for (int i = 0; i < seats.size(); i++) {
                            System.out.print("Row " + (i + 1) + ": ");
                            for (int j = 0; j < seats.get(i).size(); j++) {
                                System.out.print(seats.get(i).get(j) + " ");
                            }
                            System.out.println();
                        }

                        System.out.print("Enter the row number (1-" + seats.size() + "): ");
                        int row = scanner.nextInt();
                        System.out.print("Enter the column number (1-" + (seats.isEmpty() ? 0 : seats.get(0).size()) + "): ");
                        int col = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (row < 1 || row > seats.size() || col < 1 || col > seats.get(0).size()) {
                            System.out.println("Invalid seat selection!");
                            break;
                        }

                        System.out.println("Booking your seat...");
                        Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row - 1, col - 1); // Convert to 0-based indexing

                        if (Boolean.TRUE.equals(booked)) {
                            System.out.println("Seat booked successfully! Enjoy your journey!");
                        } else {
                            System.out.println("Can't book this seat. It might already be taken.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error booking seat: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Cancel booking functionality - to be implemented");
                    // Add your cancel booking logic here
                    break;

                case 7:
                    System.out.println("Thank you for using Train Booking System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option! Please choose between 1-7.");
                    break;
            }
        }

        scanner.close();
    }
}