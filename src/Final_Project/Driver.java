package Final_Project;

import GUI.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Driver extends Application {

    public static void main(String[] args) throws Exception, ClassNotFoundException {

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


        Stage EntryWindow = new Stage ();
        EntryWindow.setTitle ( "Ayrlyne" );
        EntryWindow.setMinHeight ( 250 );


        javafx.scene.control.Label welcomeMessage = new javafx.scene.control.Label( "Welcome to Ayrlyne!" );
        welcomeMessage.setFont(new Font("Helvetica", 48));
        javafx.scene.control.Button loginButton = new javafx.scene.control.Button("Click to Login!");
        javafx.scene.control.Button registerButton = new Button("Click to Register!");

        loginButton.setOnAction ( e -> LoginWindow.display () );
        registerButton.setOnAction ( e -> RegisterWindow.dispaly () );
        VBox vb = new VBox(20);
        HBox hb = new HBox(20);

        Scene entryScene;

        hb.getChildren ().addAll (loginButton,registerButton );
        vb.getChildren ().addAll ( welcomeMessage,hb );
        vb.setAlignment ( Pos.CENTER );
        hb.setAlignment ( Pos.CENTER );


        entryScene = new Scene ( vb , 1250  ,  700);


        EntryWindow.setScene ( entryScene );
        primaryStage = EntryWindow;
        primaryStage.show();

    }

}
