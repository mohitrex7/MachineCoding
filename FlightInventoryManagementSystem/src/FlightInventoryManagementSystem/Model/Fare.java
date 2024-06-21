package FlightInventoryManagementSystem.Model;

import java.util.List;

public class Fare {
    private String fareType;
    private double rate;
    private int availableSeats;
    List<Seats> seatsList;

    public Fare(String fareType, double rate, int availableSeats, List<Seats> seatsList) {
        this.fareType = fareType;
        this.rate = rate;
        this.availableSeats = availableSeats;
        this.seatsList = seatsList;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getFareType() {
        return fareType;
    }

    public double getRate() {
        return rate;
    }
    public void reduceSeats(int cnt){
        availableSeats-=cnt;
    }
    public void addSeats(int cnt){
        availableSeats+=cnt;
    }
    public List<Seats> getSeatsList() {
        return seatsList;
    }
    public String seatsListAsString(){
        StringBuilder seats =new StringBuilder();
        for(Seats seat : seatsList){
            if(!seat.isBooked())
                seats.append(seat.getSeatNo());
        }
        return seats.toString();
    }
}
