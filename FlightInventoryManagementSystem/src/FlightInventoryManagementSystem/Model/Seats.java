package FlightInventoryManagementSystem.Model;

public class Seats {
    private String seatNo;
    private boolean booked;
    private int bookedById;

    public Seats(String seatNo, boolean booked, int bookedById) {
        this.seatNo = seatNo;
        this.booked = booked;
        this.bookedById = bookedById;
    }
    public Seats(String seatNo) {
        this(seatNo,false,-1);
    }

    public String getSeatNo() {
        return seatNo;
    }

    public boolean isBooked() {
        return booked;
    }

    public int getBookedById() {
        return bookedById;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setBookedById(int bookedById) {
        this.bookedById = bookedById;
    }
}
