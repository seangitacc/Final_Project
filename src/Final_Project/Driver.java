package Final_Project;

import GUI.*;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Driver extends Application {

    public static void main(String[] args) {

        try{

            //Known bug workaround for Windows 10 computers with touchscreens
            System.setProperty("glass.accessible.force", "false");

            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            //Create a connection
            Utilities.createConnnection();
            Utilities.createStatement();

            //Launch the display
            launch ( args );

            // Close the connection
            Utilities.connection.close();

        }catch(Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }
    }

    @Override
    /**
     * Display the entry window
     */
    public void start(Stage primaryStage) {

       EntryWindow.display ();

    }

}
