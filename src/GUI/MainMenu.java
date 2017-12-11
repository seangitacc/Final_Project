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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu {

    static Stage stage = new Stage();

    public static void display(){

        Label header = new Label("AyrLyne");
        Button searchButton = new Button("Search Flights");
        Button logout = new Button("Logout");
        Button manageFlights = new Button("Admin Console");
        Button manageMyFlights = new Button("Manage My Flights");
        Label user = new Label("Hello, " + Customer.fullName);
        GridPane grid = new GridPane ();

        header.setFont ( new Font ( "Helvetica", 36 ) );

        grid.setAlignment ( Pos.CENTER_LEFT );
        grid.setPadding ( new Insets ( 25 ));
        grid.setHgap ( 15 );
        grid.setVgap ( 25 );


        GridPane.setConstraints ( header,0,0 );
        GridPane.setConstraints ( user,0,3 );
        GridPane.setConstraints ( searchButton,0,5 );
        GridPane.setConstraints(manageMyFlights,0, 7 );
        if(Customer.adminCheck == 1){
            GridPane.setConstraints(manageFlights, 0 ,15);
            grid.getChildren().add(manageFlights);
        }
        GridPane.setConstraints ( logout,0,9 );


        grid.getChildren ().addAll ( header,user,searchButton, manageMyFlights, logout);

       Scene scene = new Scene ( grid,1250,700 );

       stage.setTitle ( "AyrLyne" );
       stage.setScene ( scene );
       stage.show();

        searchButton.setOnAction ( e -> SearchFlights.display () );

        TableColumn<Flight, Integer> flightIdColumn = new TableColumn<Flight, Integer> ( "Flight ID" );
        flightIdColumn.setPrefWidth( 100 );
        flightIdColumn.setCellValueFactory ( new PropertyValueFactory<Flight, Integer>("flightId") );

        TableColumn<Flight, String> fromCityColumn = new TableColumn<Flight, String> ( "Departure City" );
        fromCityColumn.setPrefWidth ( 200 );
        fromCityColumn.setCellValueFactory ( new PropertyValueFactory<Flight, String> ("fromCity") );

        TableColumn<Flight, String> toCityColumn = new TableColumn<Flight, String> ( "Arrival City" );
        toCityColumn.setPrefWidth ( 200 );
        toCityColumn.setCellValueFactory ( new PropertyValueFactory<Flight, String> ("toCity") );

        TableColumn<Flight, String> flightDateColumn = new TableColumn<Flight, String> ( "Departure Date" );
        flightDateColumn.setPrefWidth ( 200 );
        flightDateColumn.setCellValueFactory ( new PropertyValueFactory<Flight, String> ("flightDate") );

        TableColumn<Flight, String> flightTimeColumn = new TableColumn<Flight, String> ( "Flight Time" );
        flightTimeColumn.setPrefWidth ( 200 );
        flightTimeColumn.setCellValueFactory ( new PropertyValueFactory<> ("flightTime") );

        TableColumn<Flight, Double> flightPriceColumn = new TableColumn<Flight, Double> ( "Price" );
        flightPriceColumn.setPrefWidth ( 100 );
        flightPriceColumn.setCellValueFactory ( new PropertyValueFactory<> ("flightPrice") );

        TableView<Flight> searchFlightTableView = new TableView ();

        Button deleteFlight = new Button("Delete Flight");


        VBox vb = new VBox(20);
        vb.getChildren().addAll(searchFlightTableView, deleteFlight);

        Scene manageMyFlightScene = new Scene(vb,800,600);
        Stage manageMyFlighStage = new Stage();
        manageMyFlighStage.setTitle("Manage My Flights");
        manageMyFlighStage.setScene(manageMyFlightScene);

        searchFlightTableView.getColumns().addAll (flightIdColumn, fromCityColumn,toCityColumn, flightDateColumn, flightTimeColumn, flightPriceColumn);


        manageMyFlights.setOnAction ( e-> {

            searchFlightTableView.setItems (Customer.searchMyFlights(Customer.userID));
            manageMyFlighStage.showAndWait (); });

        deleteFlight.setOnAction(e -> {Flight selectedFlight = searchFlightTableView.getSelectionModel().getSelectedItem();

        Customer.deleteFlight ( selectedFlight.getFlightId (), Customer.userID );

            ObservableList<Flight> selectedFlights, allFlights;
            allFlights = searchFlightTableView.getItems();
            selectedFlights = searchFlightTableView.getSelectionModel().getSelectedItems();

            selectedFlights.forEach(allFlights:: remove);
        });

        logout.setOnAction ( e ->{
            MainMenu.close();
            Driver.logout ();
        } );

        manageFlights.setOnAction ( e-> {
            ManageAllFlights.display ();
        } );

    }

    public static void close(){

        stage.close();
    }


}






