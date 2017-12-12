package Final_Project;

import javafx.scene.control.Alert;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin extends Customer {


    /**
     * Admins are able to delete a flight by inputting a flight's ID.
     * @param input
     */
    public static void deleteFlight(String input){
        try{

            flightID = Integer.parseInt ( input );

            String query = "Delete from flights where flight_id = ?";

            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setInt(1, flightID);

            ps.execute();

            //Inform the user the flight was deleted
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

    /**
     * An admin is able to add a new flight by inputting flight details.
     * @param fromCity
     * @param toCity
     * @param departDate
     * @param flightTime
     * @param flightPrice
     */
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

                //Let the user know the flight was successfully created
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

    /**
     * Be able to search for a specific flight so that the flight information can pre-populate
     * the update scene.
     * @param flightID
     * @return f1
     */
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

                //Need to pass a flight to handle an instance where a user may input an incorrect flight ID
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

    /**
     * An admin is able to update a flight using the flight's details and modifying them any way they may need to.
     * @param fromCity
     * @param toCity
     * @param departDate
     * @param flightTime
     * @param flightPrice
     * @param flightId
     */
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

            //Let the user know they successfully updated a flight
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
