package Final_Project;

import GUI.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Driver extends Application {

    public static void main(String[] args) throws Exception, ClassNotFoundException {

        //Known bug workaround for Windows 10 computers with touchscreens
        System.setProperty("glass.accessible.force", "false");

        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        Utilities.createConnnection();;
        Utilities.createStatement();

        launch ( args );

        // Close the connection
        Utilities.connection.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

       EntryWindow.display ();

    }

    public static void logout(){
        Customer.userID = 0;
        Customer.fullName = "";
        Customer.adminBool = false;
        Customer.adminCheck = 0;

        EntryWindow.display ();
        }

}
