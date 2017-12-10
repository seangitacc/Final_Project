package GUI;
/*
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
 * Created by nikhilpalli on 12/4/17.
 */

import Final_Project.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenu {

    public static void display(){

        Label header = new Label("AyrLyne");
        Button searchButton = new Button("Search Flights");
        Button logout = new Button("Logout");
        Button manageFlights = new Button("Manage All Flights");
        Label user = new Label("Hello, " + Customer.fullName);
        GridPane grid = new GridPane ();

        grid.setAlignment ( Pos.CENTER );
        grid.setPadding ( new Insets ( 25 ));
        grid.setHgap ( 15 );
        grid.setVgap ( 25 );


        GridPane.setConstraints ( header,0,0 );
        GridPane.setConstraints ( user,0,3 );
        GridPane.setConstraints ( searchButton,0,5 );
        GridPane.setConstraints ( logout,0,7 );

        if(Customer.adminCheck == 1){
            GridPane.setConstraints(manageFlights, 0 ,9);
            grid.getChildren().add(manageFlights);
        }

        grid.getChildren ().addAll ( header,user,searchButton,logout);

       Scene scene = new Scene ( grid,1250,700 );

       Stage stage = new Stage ();
       stage.setTitle ( "AyrLyne" );
        stage.setScene ( scene );
       stage.show();

        searchButton.setOnAction ( e -> stage.setScene(SearchFlights.display ()) );



    }


}






