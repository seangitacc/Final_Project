package Final_Project;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;

public class Admin extends Customer {

    public static void deleteFlight(String input){
        try{

            flightID = Integer.parseInt ( input );

            String query = "Delete from flights where flight_id = ?";

            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setInt(1, flightID);

            ps.execute();

            ps.getWarnings ();

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
            System.out.println(ex);
        }
    }

}
