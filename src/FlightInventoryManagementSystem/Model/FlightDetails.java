package FlightInventoryManagementSystem.Model;

public class FlightDetails {
    private int departureDate;
    private int departureTime;
    private int arrivalTime;
    private String from;
    private String to;
    private Fare fare;

    public FlightDetails(int departureDate, int departureTime, int arrivalTime, String from, String to,Fare fare) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.from = from;
        this.to = to;
        this.fare = fare;
    }

    public int getDepartureDate() {
        return departureDate;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Fare getFare() {
        return fare;
    }
}
