package Final_Project;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Utilities {

    private static final String user = "root";
    private static final String passwd = "gr8isc00l";
    public static Connection connection;
    private static Statement statement;

    public static void createConnnection(){

        try{

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
