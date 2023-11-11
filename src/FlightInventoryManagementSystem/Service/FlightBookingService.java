package FlightInventoryManagementSystem.Service;

import FlightInventoryManagementSystem.Model.*;

import java.awt.print.Book;
import java.util.*;
import java.util.function.BiFunction;

public class FlightBookingService {
    HashMap<Integer, User> users = new HashMap<>();
    HashMap<String, Flight> flights = new HashMap<>();
    HashMap<String, BookingDetails> bookingDetails= new HashMap<>();

    public String addUser(int userId,String name,double fund){
        users.put(userId, new User(userId, name, fund));
        return users.get(userId).toString();
    }
    public void addFlights(String flightNo, String flightName, String from, String to, int availableSeats, int departureDate,
                           int departureTime, int arrivalTime, double price, String FareType, String seats){
        List<Seats> seatsList = new ArrayList<>();
        for(String s:seats.split(",")){
            seatsList.add(new Seats(s.trim(),false,-1));
        }
        Fare fare = new Fare(FareType,price,availableSeats,seatsList);
        FlightDetails flightDetails = new FlightDetails(departureDate, departureTime, arrivalTime, from, to,fare);

        Flight flight = flights.getOrDefault(flightNo, new Flight(flightNo,flightName,new ArrayList<>()));
        flight.setFlightDetailsList(flightDetails);
        flights.put(flightNo,flight);
    }

    public void searchFlight(String from,String to,int departDate,int paxCount){
        List<String> finalSearchResult = new ArrayList<>();
        flights.forEach((s, flight) -> {
            flight.getFlightDetailsList().forEach(flightDetails -> {
                if(flightDetails.getFrom() == from &&  flightDetails.getTo() == to && flightDetails.getDepartureDate() == departDate
                        && flightDetails.getFare().getAvailableSeats()>=paxCount)

                    finalSearchResult.add(flight.getFlightNumber() + ", " + flight.getFlightName() + ", " + from + ", " + to + ", "+
                        departDate + ", " + flightDetails.getFare().getRate() + ", " + flightDetails.getFare().getFareType() + ", "
                            + flightDetails.getFare().seatsListAsString());
            });
        });
        if(finalSearchResult.isEmpty()) System.out.println( "No Result found");
        else finalSearchResult.forEach(System.out::println);
    }
    public String bookFlight(int userId, String flightNumber, int departDate, String fareType, String seats){
        boolean haveEnoughFund = true;
        boolean availableSeats = true;
        List<String> askedSeats= new ArrayList<>();
        List.of(seats.split(",")).forEach(s -> askedSeats.add(s.trim()));
        List<Seats> requiredSeats = new ArrayList<>();
        Fare askedFare = null;
        Flight flight = flights.getOrDefault(flightNumber, null);
        if(flight == null) {
            System.out.println("No such flight is present");
            return "";
        }
        for(FlightDetails flightDetail: flight.getFlightDetailsList()){
            if(flightDetail.getDepartureDate() == departDate && flightDetail.getFare().getFareType() == fareType){
                askedFare = flightDetail.getFare();
                List<Seats> seatsList = flightDetail.getFare().getSeatsList();
                for(Seats seat: seatsList) {
                    if (askedSeats.contains(seat.getSeatNo()) && !seat.isBooked()) {
                        requiredSeats.add(seat);
                    } else if (askedSeats.contains(seat.getSeatNo()) && seat.isBooked()) {
                        System.out.println("Seat " + seat.getSeatNo() + " is booked");
                        return "";
                    }
                }
            }
        }
        if(requiredSeats.size() != askedSeats.size()) {
            System.out.println("All Seats not found"); //can make it for which is not available
            return "";
        }
        User user = users.get(userId);
        double requiredFund = requiredSeats.size() * askedFare.getRate();
        if(user.getFunds()<= requiredFund) {
            System.out.println("Funds insufficient");
            return "";
        }
        user.reduceFund(requiredFund);
        requiredSeats.forEach(seat -> {
            seat.setBookedById(userId);
            seat.setBooked(true);
        });
        askedFare.reduceSeats(requiredSeats.size());
        String uuid = UUID.randomUUID().toString();
        bookingDetails.put(uuid,new BookingDetails(uuid,user,flightNumber,askedFare,requiredSeats,departDate,requiredFund));
        user.setBookingDetails(bookingDetails.get(uuid));
        System.out.println("Booking successful with Id- " + uuid);
        return uuid;
    }
    public void cancelFlight(int userId,String bookingId){
        BookingDetails bookingDetail = bookingDetails.get(bookingId);
        User user = users.get(userId);
        user.addFund(bookingDetail.getPrice());
        bookingDetail.getSeatsList().forEach(seats -> {
            seats.setBooked(false);
            seats.setBookedById(-1);
        });
        bookingDetail.getFare().addSeats(bookingDetail.getSeatsList().size());
        users.remove(bookingId);
        user.removeBooking(bookingDetail);
    }

    public void  GetUserBooking(int userId){
        User user = users.get(userId);
        user.getBookingDetails().forEach(bookingDetail ->{
            System.out.println(bookingDetail.getBookingId() + ", " + bookingDetail.getFlightNo() + ", " + bookingDetail.getDepartureDate() + ", " + bookingDetail.getPrice() +
                    ", " + bookingDetail.getFare().getFareType() + ", " + bookingDetail.seatsAsString());
        } );
    }

}
