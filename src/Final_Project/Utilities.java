package Final_Project;

import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Utilities {

    private static final String user = "root"; //Used to connect to the DB
    private static final String passwd = "gr8isc00l"; //Used to connect to the DB
    public static Connection connection; //Used to create a connection to the DB
    private static Statement statement; //Used to create a statement to run against the DB

    /**
     * Create a connection to the DB
     */
    public static void createConnnection(){

        try{

            //Uses JDBC driver and connects to our Google Cloud DB, which is linked to the local mySQL one
            connection = DriverManager.getConnection
                    ("jdbc:mysql://35.196.66.63" + "/final-project?"
                            + "user=" + user + "&password=" + passwd + "&autoReconnect=true&useSSL=false");

        }catch(Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

    }

    /**
     * Used to create a statement to run against the DB
     */
    public static void createStatement(){

        try{

            statement = connection.createStatement();

        }catch(Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

    }

}
