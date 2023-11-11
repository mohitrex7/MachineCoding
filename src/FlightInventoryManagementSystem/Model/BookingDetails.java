package FlightInventoryManagementSystem.Model;

import java.util.List;

public class BookingDetails {
    private String bookingId;
    private User user;
    private String flightNo;
    private Fare fare;
    private List<Seats> seatsList;
    private int departureDate;
    private double price;

    public BookingDetails(String bookingId, User user, String flightNo, Fare fare, List<Seats> seatsList, int departureDate, double price) {
        this.bookingId = bookingId;
        this.user = user;
        this.flightNo = flightNo;
        this.fare = fare;
        this.seatsList = seatsList;
        this.departureDate = departureDate;
        this.price = price;
    }

    public Fare getFare() {
        return fare;
    }

    public List<Seats> getSeatsList() {
        return seatsList;
    }

    public double getPrice() {
        return price;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public int getDepartureDate() {
        return departureDate;
    }
    public String seatsAsString(){
        StringBuilder res = new StringBuilder();
        seatsList.forEach(seat -> res.append(seat.getSeatNo()).append(", "));
        res.delete(res.length() -2 ,res.length()-1);
        return res.toString();
    }
}
