package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.jvm.hotspot.StackTrace;

import javax.swing.*;
import java.net.URL;
import java.sql.PseudoColumnUsage;


/**
 * Created by nikhilpalli on 11/30/17.
 */
public class Main extends Application {



    public static void main(String[] args) {
        launch ( args );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Stage EntryWindow = new Stage ();
        EntryWindow.setTitle ( "Ayrlyne" );
        EntryWindow.setMinHeight ( 250 );

        Label welcomeMessage = new Label ( "Welcome to Ayrlyne!" );
        welcomeMessage.setFont(new Font ("Helvetica", 36));
        Button loginButton = new Button("Click to Login!");
        Button registerButton = new Button("Click to Register!");

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
