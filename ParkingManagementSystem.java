import java.util.*;
public class ParkingManagementSystem {

    private static final List<String> bikeSpots = new ArrayList<>(Arrays.asList("B-1", "B-2", "B-3"));
    private static final List<String> carSpots = new ArrayList<>(Arrays.asList("C-1", "C-2", "C-3", "C-4"));
    private static final List<String> truckSpots = new ArrayList<>(Arrays.asList("T-1", "T-2"));
    private static final Map<String, Booking> bookings = new HashMap<>();
    private static double totalEarnings = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nWelcome to Smart Parking Management System");
            System.out.println("------------------------------------------");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Vacate Spot");
            System.out.println("3. Display Available Spots");
            System.out.println("4. Search for a Booking");
            System.out.println("5. Administrator Access");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> parkVehicle(scanner);
                case 2 -> vacateSpot(scanner);
                case 3 -> displayAvailableSpots();
                case 4 -> searchBooking(scanner);
                case 5 -> administratorAccess(scanner);
                case 6 -> {
                    System.out.println("Thank you for using the Parking Management System!");
                    isRunning = false;
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }

    private static void parkVehicle(Scanner scanner) {
        System.out.println("\nParking Vehicle...");
        System.out.print("Enter vehicle type (Car/Bike/Truck): ");
        String vehicleType = scanner.nextLine().toLowerCase();

        String allocatedSpot = null;

        switch (vehicleType) {
            case "bike" -> allocatedSpot = allocateSpot(bikeSpots);
            case "car" -> allocatedSpot = allocateSpot(carSpots);
            case "truck" -> allocatedSpot = allocateSpot(truckSpots);
            default -> {
                System.out.println("Invalid vehicle type! Please try again.");
                return;
            }
        }

        if (allocatedSpot == null) {
            System.out.println("No available spots for " + vehicleType + ".");
            return;
        }

        System.out.println("Allocated Spot: " + allocatedSpot);
        System.out.print("Enter vehicle number: ");
        String vehicleNumber = scanner.nextLine();
        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine();

        bookings.put(allocatedSpot, new Booking(vehicleType, vehicleNumber, ownerName));
        System.out.println("Vehicle parked successfully at spot: " + allocatedSpot);
    }

    private static void vacateSpot(Scanner scanner) {
        System.out.print("\nEnter spot number to vacate: ");
        String spot = scanner.nextLine();

        if (bookings.containsKey(spot)) {
            Booking booking = bookings.remove(spot);

            // Free the spot
            if (spot.startsWith("B")) {
                bikeSpots.add(spot);
            } else if (spot.startsWith("C")) {
                carSpots.add(spot);
            } else if (spot.startsWith("T")) {
                truckSpots.add(spot);
            }

            double charge = calculateCharge(booking.vehicleType);
            totalEarnings += charge;

            System.out.println("Spot vacated successfully. Vehicle: " + booking.vehicleNumber);
            System.out.println("Parking charge: $" + charge);
        } else {
            System.out.println("Invalid spot number or the spot is already empty.");
        }
    }

    private static void displayAvailableSpots() {
        System.out.println("\nAvailable Parking Spots:");
        System.out.println("Bike Spots: " + bikeSpots);
        System.out.println("Car Spots: " + carSpots);
        System.out.println("Truck Spots: " + truckSpots);
    }

    private static void searchBooking(Scanner scanner) {
        System.out.print("\nEnter vehicle number to search: ");
        String vehicleNumber = scanner.nextLine();

        for (Map.Entry<String, Booking> entry : bookings.entrySet()) {
            if (entry.getValue().vehicleNumber.equalsIgnoreCase(vehicleNumber)) {
                System.out.println("Booking Found:");
                System.out.println("Spot: " + entry.getKey() + ", Vehicle Type: " + entry.getValue().vehicleType +
                        ", Owner: " + entry.getValue().ownerName);
                return;
            }
        }
        System.out.println("No booking found for vehicle number: " + vehicleNumber);
    }

    private static void administratorAccess(Scanner scanner) {
        final String adminPassword = "admin123";
        System.out.print("\nEnter Administrator Password: ");
        String enteredPassword = scanner.nextLine();

        if (!enteredPassword.equals(adminPassword)) {
            System.out.println("Invalid password! Access denied.");
            return;
        }

        boolean adminSessionActive = true;
        while (adminSessionActive) {
            System.out.println("\nAdministrator Access Granted:");
            System.out.println("1. View Total Earnings");
            System.out.println("2. Monitor Real-Time Occupancy");
            System.out.println("3. Add/Remove Parking Spots");
            System.out.println("4. Exit Administration");
            System.out.print("Choose an option: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1 -> System.out.println("Total Earnings: $" + totalEarnings);
                case 2 -> {
                    int occupied = bookings.size();
                    int totalSpots = bikeSpots.size() + carSpots.size() + truckSpots.size() + occupied;
                    System.out.println("Real-Time Occupancy: " + occupied + "/" + totalSpots);
                }
                case 3 -> modifyParkingSpots(scanner);
                case 4 -> {
                    System.out.println("Exiting Administrator Access...");
                    adminSessionActive = false;
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void modifyParkingSpots(Scanner scanner) {
        System.out.println("\nModify Parking Spots:");
        System.out.println("1. Add Spots");
        System.out.println("2. Remove Spots");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                System.out.print("Enter spot type (Bike/Car/Truck): ");
                String type = scanner.nextLine().toLowerCase();
                System.out.print("Enter spot name: ");
                String spotName = scanner.nextLine();

                if (type.equals("bike")) {
                    bikeSpots.add(spotName);
                } else if (type.equals("car")) {
                    carSpots.add(spotName);
                } else if (type.equals("truck")) {
                    truckSpots.add(spotName);
                } else {
                    System.out.println("Invalid type! No spots added.");
                }
            }
            case 2 -> {
                System.out.print("Enter spot name to remove: ");
                String spotName = scanner.nextLine();
                bikeSpots.remove(spotName);
                carSpots.remove(spotName);
                truckSpots.remove(spotName);
            }
            default -> System.out.println("Invalid option! Returning to menu.");
        }
    }

    private static String allocateSpot(List<String> spots) {
        return spots.isEmpty() ? null : spots.remove(0);
    }

    private static double calculateCharge(String vehicleType) {
        return switch (vehicleType) {
            case "bike" -> 5.0;
            case "car" -> 10.0;
            case "truck" -> 20.0;
            default -> 0.0;
        };
    }

    private static int countOccupiedSpots(List<String> spots) {
        return spots.size();
    }

    private static class Booking {
        String vehicleType;
        String vehicleNumber;
        String ownerName;

        Booking(String vehicleType, String vehicleNumber, String ownerName) {
            this.vehicleType = vehicleType;
            this.vehicleNumber = vehicleNumber;
            this.ownerName = ownerName;
        }
    }
}
  