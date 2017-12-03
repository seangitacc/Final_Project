package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by nikhilpalli on 12/1/17.
 */
public class forgotPasswordWindow {

    public static void display() {

        Stage forgotPasswordWindow = new Stage ();
        forgotPasswordWindow.setTitle ( "Forgot Password" );
        forgotPasswordWindow.initModality ( Modality.APPLICATION_MODAL );



        Label enterun = new Label("Enter Username: ");
        Label enterssn = new Label("Enter SSN: ");
        TextField usInput = new TextField (  );
        TextField ssnInput = new TextField (  );
        Button retrieveQuestion = new Button("Retrieve Security Question");
        Button back = new Button("Back");



        Label questionL = new Label ("Question: " );
        Label questiontf = new Label("<question here>");
        Label ans = new Label("Enter Answer");
        TextField answer = new TextField (  );
        Button retrievePassword = new Button("Retrieve Password");
        Button back1 = new Button("Back");



        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets ( 25 ));
        grid1.setVgap ( 8 );
        grid1.setHgap ( 10 );
        grid1.setAlignment ( Pos.CENTER );

        GridPane.setConstraints ( enterun,0,0 );
        GridPane.setConstraints ( enterssn,0,1 );
        GridPane.setConstraints ( usInput,1,0 );
        GridPane.setConstraints ( ssnInput,1,1 );
        GridPane.setConstraints ( retrieveQuestion,1,3 );
        GridPane.setConstraints ( back,0,3 );


        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets ( 25 ));
        grid2.setVgap ( 8 );
        grid2.setHgap ( 10 );
        grid2.setAlignment ( Pos.CENTER );

        GridPane.setConstraints ( questionL,0,0 );
        GridPane.setConstraints ( questiontf,1,0 );
        GridPane.setConstraints ( ans,0,1);
        GridPane.setConstraints ( answer,1,1 );
        GridPane.setConstraints ( retrievePassword,1,3 );
        GridPane.setConstraints ( back1,0,3);

        grid1.getChildren ().addAll ( enterun,enterssn,usInput,ssnInput, back, retrieveQuestion );
        grid2.getChildren ().addAll( questionL,questiontf, ans, answer, retrievePassword,back1);

        Scene scene1 = new Scene(grid1, 350,200);
        Scene scene2 = new Scene(grid2, 350,200);

        retrieveQuestion.setOnAction ( e -> {
                 // Customer.getPassword(usInput.getText (), ssnInput.getText ());
               if(true) {
                    forgotPasswordWindow.setScene ( scene2 );
               }
               else {
                   Alert alert = new Alert( Alert.AlertType.ERROR);
                   alert.setTitle ( "Warning" );
                   alert.setHeaderText ( "Invalid Input!" );
                   alert.setContentText ( "Please Enter correct username and SSN" );

                   alert.showAndWait ();
               }

        }


            );
        back.setOnAction ( e -> forgotPasswordWindow.close () );
        back1.setOnAction ( e -> forgotPasswordWindow.setScene ( scene1 ) );


        forgotPasswordWindow.setScene ( scene1 );
        forgotPasswordWindow.show ();






    }
}
