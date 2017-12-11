package Final_Project;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin extends Customer {

    public static void deleteFlight(String input){
        try{

            flightID = Integer.parseInt ( input );

            String query = "Delete from flights where flight_id = ?";

            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setInt(1, flightID);

            ps.execute();

            String incrementQuery = "UPDATE flights\n" +
                    "SET flights_seats = flights_seats + 1\n" +
                    "Where flight_id = ?";
            PreparedStatement incrementCount = Utilities.connection.prepareStatement(incrementQuery);
            incrementCount.setInt(1, flightID);
            incrementCount.execute();

            Alert alert = new Alert ( Alert.AlertType.INFORMATION );
            alert.setTitle ( "Information" );
            alert.setHeaderText ( "Flight Deletion" );
            alert.setContentText ( "You've successfully deleted this flight!" );

            alert.showAndWait ();


        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }
    }

    public static void addNewFlight(String fromCity, String toCity, String departDate, String flightTime, String flightPrice){

        try{

            String query = "INSERT Into flights (from_city, to_city, flight_date, flight_time, flight_price) values (?,?,?,?,?)";
            PreparedStatement ps = Utilities.connection.prepareStatement(query);

                ps.setString(1, fromCity);
                ps.setString(2, toCity);
                ps.setString(3, departDate);
                ps.setString(4, flightTime);
                ps.setString(5, flightPrice);

                ps.execute();

                Alert alert = new Alert ( Alert.AlertType.INFORMATION );
                alert.setTitle ( "Information" );
                alert.setHeaderText ( "Flight Creation" );
                alert.setContentText ( "You've successfully created this flight!" );

                alert.showAndWait ();


        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

    }

    public static Flight searchUpdateFlights(int flightID){

        Flight f1 = new Flight();

        try {

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT * from flights where flight_id = ?");
            ps.setInt(1, flightID);
            ResultSet count = ps.executeQuery();


            if(count.next()){

                f1 = new Flight(Integer.parseInt(count.getString(1)), count.getString(2), count.getString(3),
                        count.getString(4), count.getString(5), Double.parseDouble(count.getString(6)));
            }else{
                f1 = new Flight(0 , "A", "B", "C", "D", 1.0);
            }

        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

        return f1;
    }

    public static void updateFlight(String fromCity, String toCity, String departDate, String flightTime, String flightPrice, int flightId){

        System.out.println(fromCity);

        try{

            String query = "UPDATE flights\n" +
                    "SET from_city = ?,\n" +
                    "to_city = ?,\n" +
                    "flight_date = ?,\n" +
                    "flight_time = ?,\n" +
                    "flight_price = ?\n" +
                    "where flight_id = ?";
            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setString(1, fromCity);
            ps.setString(2, toCity);
            ps.setString(3, departDate);
            ps.setString(4, flightTime);
            ps.setString(5, flightPrice);
            ps.setInt(6, flightId);

            ps.executeUpdate();

            Alert alert = new Alert ( Alert.AlertType.INFORMATION );
            alert.setTitle ( "Information" );
            alert.setHeaderText ( "Flight Update" );
            alert.setContentText ( "You've successfully updated this flight!" );

            alert.showAndWait ();


        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

    }


}
