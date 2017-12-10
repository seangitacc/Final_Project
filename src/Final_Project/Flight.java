package Final_Project;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by nikhilpalli on 12/6/17.
 */
public class Flight {

    private int flightId;
    private String fromCity;
    private String toCity;
    private Date flightDate;
    private Time flightTime;
    private double flightPrice;

    public Flight(){
        this.flightId = 0000;
        this.fromCity = "Atlanta, GA";
        this.toCity = null;
        this.flightDate = null;
        this.flightTime = null;
        this.flightPrice = 0;
    }

    public Flight(int flightId, String fromCity, String toCity, Date flightDate, Time flightTime, double flightPrice){

        this.flightId = flightId;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.flightDate = flightDate;
        this.flightTime = flightTime;
        this.flightPrice = flightPrice;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Time getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Time flightTime) {
        this.flightTime = flightTime;
    }

    public double getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(double flightPrice) {
        this.flightPrice = flightPrice;
    }
}
