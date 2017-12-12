package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by nikhilpalli on 12/11/17.
 */
public class EntryWindow {

    private static final Stage EntryWindow = new Stage ();
    static Scene entryScene;

    public static void display(){

        //declare stage
        EntryWindow.setTitle ( "Ayrlyne" );
        EntryWindow.setMinHeight ( 250 );

        //declare properties
        Label welcomeMessage = new Label( "Welcome to Ayrlyne!" );
        welcomeMessage.setFont(new Font ("Helvetica", 48));
        welcomeMessage.setStyle ( "-fx-text-fill: e8e8e8" );
        javafx.scene.control.Button loginButton = new javafx.scene.control.Button("Click to Login!");
        javafx.scene.control.Button registerButton = new Button ("Click to Register!");

        //add event handlers to buttons via lambda
        loginButton.setOnAction ( e -> LoginWindow.display ());
        registerButton.setOnAction ( e -> RegisterWindow.dispaly () );

        //declare pane for scene
        VBox vb = new VBox(20);
        HBox hb = new HBox(20);


        //add nodes to pane
        hb.getChildren ().addAll (loginButton,registerButton );
        vb.getChildren ().addAll ( welcomeMessage,hb);
        vb.setAlignment ( Pos.CENTER );
        hb.setAlignment ( Pos.CENTER );

        //declare scene, add style to scene, and set scene to stage
        entryScene = new Scene ( vb , 1250  ,  700);
        entryScene.getStylesheets ().add ( "Theme.css" );

        EntryWindow.setScene ( entryScene );

        //display scene
        EntryWindow.show();
    }

    public static void close(){
        EntryWindow.close();
    }

    public static Scene getScene(){
        return entryScene;
    }
}
