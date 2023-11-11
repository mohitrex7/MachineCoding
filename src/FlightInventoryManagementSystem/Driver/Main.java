package FlightInventoryManagementSystem.Driver;

import FlightInventoryManagementSystem.Model.FlightDetails;
import FlightInventoryManagementSystem.Service.FlightBookingService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FlightBookingService flightBookingService = new FlightBookingService();
        addFlights(flightBookingService);
        flightBookingService.addUser(1,"Mohit", 199000);
        flightBookingService.addUser(2,"Naval", 199000);

        flightBookingService.searchFlight("DEL", "BLR", 2, 2);

        String uuid = flightBookingService.bookFlight(2, "123", 2, "F1", "2c,4e");
        flightBookingService.searchFlight("DEL", "BLR", 2, 0);

        flightBookingService.GetUserBooking(2);

        flightBookingService.cancelFlight(2, uuid);

        flightBookingService.GetUserBooking(2);
        flightBookingService.searchFlight("DEL", "BLR", 2, 0);
    }
    static void addFlights(FlightBookingService flightBookingService){
        flightBookingService.addFlights("123", "6E", "DEL", "BLR", 3, 2, 10,
                11, 2000, "F1", "1b,2c,4e");
        flightBookingService.addFlights("156", "6E", "DEL", "BLR", 1, 2, 14,
                15, 4000, "F2", "4e");
        flightBookingService.addFlights("234", "6E", "DEL", "HYD", 4, 2, 15,
                16, 1000, "F3", "29a, 40e, 1e, 4e");
        flightBookingService.addFlights("456", "6E", "AMD", "CCU", 10, 2, 15,
                16, 1000, "F3", "29a, 40e, 1e, 4e");
        flightBookingService.addFlights("234", "6E", "DEL", "HYD", 4, 2, 18,
                22, 10000, "F5", "7c, 7d, 12c, 5f, 8e, 7e, 4d,3e, 4a, 10a");
        flightBookingService.addFlights("987", "SJ", "DEL", "BLR", 6, 2, 11,
                16, 2500, "F11", "12c, 5f, 4d, 3e, 4a, 10a");
        flightBookingService.addFlights("1001", "SJ", "DEL", "HYD", 5, 2, 9,
                12, 2300 , "F2", "45e, 30a, 1e, 4e, 7c, 7d, 12c");
        flightBookingService.addFlights("890", "SJ", "DEL", "AMD", 7, 2, 12,
                18, 2100, "F3", "29a, 40e, 1e, 4e");
        flightBookingService.addFlights("999", "SJ", "DEL", "HYD", 12, 2, 8,
                12, 2900, "F1", "1e, 4e, 7c, 7d, 12c, 5f, 8e,7e, 4d, 3e, 4a, 10a");
    }
}
