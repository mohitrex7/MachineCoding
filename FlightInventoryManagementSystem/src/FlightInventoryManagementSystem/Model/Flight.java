package FlightInventoryManagementSystem.Model;

import java.util.List;

public class Flight {
    private String flightNumber;
    private String flightName;
    private List<FlightDetails> flightDetailsList;

    public Flight(String flightNumber, String flightName, List<FlightDetails> flightDetailsList) {
        this.flightNumber = flightNumber;
        this.flightName = flightName;
        this.flightDetailsList = flightDetailsList;
    }

    public void setFlightDetailsList(FlightDetails flightDetails) {
        this.flightDetailsList.add(flightDetails);
    }

    public List<FlightDetails> getFlightDetailsList() {
        return flightDetailsList;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getFlightName() {
        return flightName;
    }
}
