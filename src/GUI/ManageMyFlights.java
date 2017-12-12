package GUI;

import Final_Project.Customer;
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

/**
 * Created by nikhilpalli on 12/12/17.
 */
public class ManageMyFlights {

    public static void display(){

        TableColumn<Flight, Integer> flightIdColumn = new TableColumn<>("Flight ID");
        flightIdColumn.setPrefWidth( 100 );
        flightIdColumn.setCellValueFactory (new PropertyValueFactory<> ("flightId") );

        TableColumn<Flight, String> fromCityColumn = new TableColumn<>("Departure City");
        fromCityColumn.setPrefWidth ( 200 );
        fromCityColumn.setCellValueFactory (new PropertyValueFactory<>("fromCity") );

        TableColumn<Flight, String> toCityColumn = new TableColumn<>("Arrival City");
        toCityColumn.setPrefWidth ( 200 );
        toCityColumn.setCellValueFactory (new PropertyValueFactory<>("toCity") );

        TableColumn<Flight, String> flightDateColumn = new TableColumn<>("Departure Date");
        flightDateColumn.setPrefWidth ( 200 );
        flightDateColumn.setCellValueFactory (new PropertyValueFactory<>("flightDate") );

        TableColumn<Flight, String> flightTimeColumn = new TableColumn<>("Flight Time");
        flightTimeColumn.setPrefWidth ( 200 );
        flightTimeColumn.setCellValueFactory ( new PropertyValueFactory<> ("flightTime") );

        TableColumn<Flight, Double> flightPriceColumn = new TableColumn<>("Price");
        flightPriceColumn.setPrefWidth ( 100 );
        flightPriceColumn.setCellValueFactory ( new PropertyValueFactory<> ("flightPrice") );

        TableView<Flight> searchFlightTableView = new TableView ();
        searchFlightTableView.setItems (Customer.searchMyFlights(Customer.userID));

        Label title = new Label ( "Manage My Flights" );
        title.setFont ( new Font ( "Helvetica", 36 ) );
        title.setStyle ( "-fx-text-fill: #e8e8e8" );

        Button homeButton = new Button ( "Home" );
        Button log = new Button ( "Logout" );
        Label user1 = new Label ( "Hello, " + Customer.fullName);
        user1.setStyle ( "-fx-text-fill: #e8e8e8" );



        GridPane g = new GridPane ();
        g.setPadding ( new Insets ( 25 ) );
        g.setHgap ( 15 );
        g.setVgap ( 10 );


        GridPane.setConstraints ( title, 0, 0 );
        GridPane.setConstraints ( user1, 25, 0 );
        GridPane.setConstraints ( homeButton, 28, 0 );
        GridPane.setConstraints ( log, 31, 0 );

        g.getChildren ().addAll ( title,user1,homeButton,log );

        Button deleteFlight = new Button("Delete Flight");
        Button addFlight = new Button("Add Flight");

        HBox hb = new HBox ( 20 );

        hb.getChildren ().addAll ( addFlight,deleteFlight );
        hb.setAlignment ( Pos.BOTTOM_RIGHT );

        VBox vb = new VBox(20);
        vb.getChildren().addAll(g,searchFlightTableView, hb);

        Scene manageMyFlightScene = new Scene(vb,1250,700);
        Stage manageMyFlightStage = new Stage();
        manageMyFlightStage.setTitle("Manage My Flights");

        manageMyFlightStage.setScene(manageMyFlightScene);

        vb.setPadding ( new Insets ( 25 ) );

        manageMyFlightScene.getStylesheets ().add("TableViewTheme.css");

        searchFlightTableView.getColumns().addAll (flightIdColumn, fromCityColumn,toCityColumn, flightDateColumn, flightTimeColumn, flightPriceColumn);


        manageMyFlightStage.show();


        deleteFlight.setOnAction(e -> {Flight selectedFlight = searchFlightTableView.getSelectionModel().getSelectedItem();

            Customer.deleteFlight ( selectedFlight.getFlightId (), Customer.userID );

            ObservableList<Flight> selectedFlights, allFlights;
            allFlights = searchFlightTableView.getItems();
            selectedFlights = searchFlightTableView.getSelectionModel().getSelectedItems();

            selectedFlights.forEach(allFlights:: remove);
        });

        addFlight.setOnAction ( e -> {
            manageMyFlightStage.close ();
            SearchFlights.display ();
        } );

        log.setOnAction ( e ->{

            MainMenu.close();
            Customer.logout ();
        } );

        homeButton.setOnAction ( e -> {
            manageMyFlightStage.close ();
            MainMenu.display ();
        } );

        log.setOnAction ( e->{

            manageMyFlightStage.close ();
            Customer.logout ();

        } );

    }
}

