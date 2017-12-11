package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by nikhilpalli on 12/11/17.
 */
public class EntryWindow {

    static Stage EntryWindow = new Stage ();

    public static void display(){


        EntryWindow.setTitle ( "Ayrlyne" );
        EntryWindow.setMinHeight ( 250 );

        Label welcomeMessage = new Label( "Welcome to Ayrlyne!" );
        welcomeMessage.setFont(new Font ("Helvetica", 48));
        welcomeMessage.setStyle ( "-fx-text-fill: e8e8e8" );
        javafx.scene.control.Button loginButton = new javafx.scene.control.Button("Click to Login!");
        javafx.scene.control.Button registerButton = new Button ("Click to Register!");

        loginButton.setOnAction ( e -> {
            LoginWindow.display ();
        } );
        registerButton.setOnAction ( e -> RegisterWindow.dispaly () );

        VBox vb = new VBox(20);
        HBox hb = new HBox(20);

        Scene entryScene;

        hb.getChildren ().addAll (loginButton,registerButton );
        vb.getChildren ().addAll ( welcomeMessage,hb );
        vb.setAlignment ( Pos.CENTER );
        hb.setAlignment ( Pos.CENTER );


        entryScene = new Scene ( vb , 1250  ,  700);

        entryScene.getStylesheets ().add ( "Theme.css" );

        EntryWindow.setScene ( entryScene );

        EntryWindow.show();
    }

    public static void close(){
        EntryWindow.close();
    }
}
