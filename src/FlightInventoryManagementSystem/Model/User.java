package FlightInventoryManagementSystem.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String name;
    private double funds;
    private List<BookingDetails>bookingDetails;

    public User(int userId, String name, double funds) {
        this.userId = userId;
        this.name = name;
        this.funds = funds;
        bookingDetails = new ArrayList<>();
    }
    public boolean addFund(double amt){
        funds+=amt;
        return true;
    }
    public boolean reduceFund(double amt){
        if(funds<amt)   return false;
        funds-=amt;
        return true;
    }

    public double getFunds() {
        return funds;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", funds=" + funds +
                '}';
    }

    public List<BookingDetails> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails.add(bookingDetails);
    }
    public void removeBooking(BookingDetails bookingDetail){
        this.bookingDetails.remove(bookingDetail);
    }
}
