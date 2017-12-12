package GUI;

import Final_Project.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by nikhilpalli on 11/30/17.
 */
class LoginWindow {

    public static void display() {

        Stage LoginWindow = new Stage();
        LoginWindow.setTitle ( "Login" );
        LoginWindow.initModality ( Modality.APPLICATION_MODAL );

        Label header = new Label("Login");
        header.setFont ( new Font ( "Helvetica", 36 ) );

        TextField usernametf = new TextField ( );
        usernametf.setPromptText ( "username" );
        TextField passwordtf = new TextField ( );
        passwordtf.setPromptText ( "password" );
        Label usernamel = new Label("Enter Username: " );
        Label passwordl = new Label("Enter Password: " );
        Button login = new Button("Login");
        Button forgotPassword = new Button("Forgot Password");
        Button back = new Button("Back");


        back.setOnAction ( e -> LoginWindow.close() );
        forgotPassword.setOnAction ( e -> forgotPasswordWindow.display () );
        login.setOnAction ( e -> {

            //SearchFlights.display ();

            String username = usernametf.getText ();
            String password = passwordtf.getText();
            Customer.login ( username,password );

            if(Customer.userID != 0){
                LoginWindow.close();
                EntryWindow.close ();
                MainMenu.display();

            }

        } );


        usernametf.setMaxWidth ( 200 );
        passwordtf.setMaxWidth ( 200 );

        HBox hb = new HBox(20);
        hb.getChildren ().addAll (back,forgotPassword,login );
        GridPane grid = new GridPane ();
        grid.setPadding ( new Insets ( 25 ) );
        grid.setVgap ( 8 );
        grid.setHgap ( 10 );

        GridPane.setConstraints ( usernamel,0,0 );
        GridPane.setConstraints ( passwordl,0,1 );
        GridPane.setConstraints ( usernametf,1,0 );
        GridPane.setConstraints ( passwordtf,1,1 );
        grid.getChildren ().setAll ( usernamel,usernametf,passwordl,passwordtf);

        VBox vb = new VBox ( 20 );
        vb.getChildren ().addAll ( header, grid,hb);
        vb.setPadding ( new Insets ( 25 ) );
        grid.setAlignment ( Pos.CENTER );
        hb.setAlignment ( Pos.CENTER );
        Scene LoginScene = new Scene ( vb,400,300 );

        LoginScene.getStylesheets().add( "Theme.css" );
        LoginWindow.setScene ( LoginScene );
        LoginWindow.show();

    }

}
