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
import Final_Project.Driver;
import Final_Project.Flight;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

class MainMenu {

    private static final Stage stage = new Stage();

    public static void display() {

        Label header = new Label ( "AyrLyne" );
        Button searchButton = new Button ( "Search Flights" );
        Button logout = new Button ( "Logout" );
        Button manageFlights = new Button ( "Admin Console" );
        Button manageMyFlight = new Button ( "Manage My Flights" );
        Label user = new Label ( "Hello, " + Customer.fullName );
        GridPane grid = new GridPane ();

        header.setFont ( new Font ( "Helvetica", 36 ) );

        grid.setAlignment ( Pos.CENTER_LEFT );
        grid.setPadding ( new Insets ( 25 ) );
        grid.setHgap ( 15 );
        grid.setVgap ( 25 );


        GridPane.setConstraints ( header, 0, 0 );
        GridPane.setConstraints ( user, 0, 3 );
        GridPane.setConstraints ( searchButton, 0, 5 );
        GridPane.setConstraints ( manageMyFlight, 0, 7 );
        GridPane.setConstraints ( logout, 0, 9 );
        if (Customer.adminCheck == 1) {
            GridPane.setConstraints ( manageFlights, 0, 15 );
            grid.getChildren ().add ( manageFlights );
        }

        grid.getChildren ().addAll ( header, user, searchButton, manageMyFlight, logout );

        Scene mainMenuScene = new Scene ( grid, 1250, 700 );
        mainMenuScene.getStylesheets ().add ( "Theme.css" );

        stage.setTitle ( "AyrLyne" );
        stage.setScene ( mainMenuScene );
        stage.show ();

        searchButton.setOnAction ( e -> SearchFlights.display () );

        manageMyFlight.setOnAction ( e-> ManageAllFlights.display ());

        manageFlights.setOnAction ( e-> ManageAllFlights.display ());

        logout.setOnAction ( e ->{
            MainMenu.close();
            Customer.logout ();
        } );
    }

    public static void close(){

        stage.close();
    }
}






