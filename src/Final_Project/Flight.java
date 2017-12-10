package Final_Project;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sun.java2d.pipe.SpanShapeRenderer;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by nikhilpalli on 12/6/17.
 */
public class Flight {

    private SimpleIntegerProperty flightId;
    private SimpleStringProperty fromCity;
    private SimpleStringProperty toCity;
    private SimpleStringProperty flightDate;
    private SimpleStringProperty flightTime;
    private SimpleDoubleProperty flightPrice;

    public Flight(int flightId, String fromCity, String toCity, String flightDate, String flightTime, double flightPrice){

        this.flightId = new SimpleIntegerProperty(flightId);
        this.fromCity = new SimpleStringProperty(fromCity);
        this.toCity = new SimpleStringProperty(toCity);
        this.flightDate = new SimpleStringProperty(flightDate);
        this.flightTime = new SimpleStringProperty(flightTime);
        this.flightPrice = new SimpleDoubleProperty(flightPrice);
    }

    public int getFlightId() {
        return flightId.get();
    }

    public SimpleIntegerProperty flightIdProperty() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId.set(flightId);
    }

    public String getFromCity() {
        return fromCity.get();
    }

    public SimpleStringProperty fromCityProperty() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity.set(fromCity);
    }

    public String getToCity() {
        return toCity.get();
    }

    public SimpleStringProperty toCityProperty() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity.set(toCity);
    }

    public String getFlightDate() {
        return flightDate.get();
    }

    public SimpleStringProperty flightDateProperty() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate.set(flightDate);
    }

    public String getFlightTime() {
        return flightTime.get();
    }

    public SimpleStringProperty flightTimeProperty() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime.set(flightTime);
    }

    public double getFlightPrice() {
        return flightPrice.get();
    }

    public SimpleDoubleProperty flightPriceProperty() {
        return flightPrice;
    }

    public void setFlightPrice(double flightPrice) {
        this.flightPrice.set(flightPrice);
    }
}
