package GUI;

import Final_Project.Customer;
import Final_Project.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Final_Project.Flight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by nikhilpalli on 12/6/17.
 */

class SearchFlights {

    public static void display() {

        Stage searchFlightStage = new Stage ();
        searchFlightStage.setTitle ( "AyrLyne-Search Flights" );

        Label title = new Label ( "Search Flights" );
        Label fromL = new Label ( "From " );
        Label toL = new Label ( "To " );
        ComboBox<String> from = new ComboBox ();
        ComboBox<String> to = new ComboBox ();
        Label departtf = new Label ( "Date of Departure" );
        DatePicker departDate = new DatePicker ();
        Button search = new Button("Search");


        Button homeButton = new Button ( "Home" );
        Button logout = new Button ( "Logout" );
        Label user = new Label ( "Hello, " + Customer.fullName);
        GridPane grid2 = new GridPane ();

        title.setStyle ( "-fx-text-fill: #e8e8e8" );
        fromL.setStyle ( "-fx-text-fill: #e8e8e8" );
        toL.setStyle ( "-fx-text-fill: #e8e8e8" );
        departtf.setStyle ( "-fx-text-fill: #e8e8e8" );
        user.setStyle ( "-fx-text-fill: #e8e8e8" );

        title.setFont ( new Font ( "Helvetica", 36 ) );

        from.setPromptText ( "Atlanta (ATL)" );
        to.setPromptText ( "San Fransisco (SFO)" );

        departDate.setStyle ( "-fx-text-fill: #383838" );
        from.getItems ().addAll (
                "Atlanta (ATL)",
                "San Fransisco (SFO)",
                "St. Louis (STL)",
                "New York (JFK)",
                "Dallas (DFW)",
                "Chicago (ORD)"
                //use database to populate
        );
        to.getItems ().addAll (
                "Atlanta (ATL)",
                "San Fransisco (SFO)",
                "St. Louis (STL)",
                "New York (JFK)",
                "Dallas (DFW)",
                "Chicago (ORD)"

                //use database to populate --------------------------
        );


        departDate.setPromptText ( "" + LocalDate.now() );

        departDate.setMaxSize ( 200, 10 );


        GridPane grid = new GridPane ();

        grid.setPadding ( new Insets ( 25 ) );
        grid.setVgap ( 10 );
        grid.setHgap ( 10 );

        grid2.setPadding ( new Insets ( 25 ) );
        grid2.setVgap ( 10 );
        grid2.setHgap ( 10 );

        grid.setAlignment ( Pos.CENTER );
        grid2.setAlignment ( Pos.TOP_CENTER );

        GridPane.setConstraints ( title, 0, 0 );
        GridPane.setConstraints ( user, 25, 0 );
        GridPane.setConstraints ( homeButton, 28, 0 );
        GridPane.setConstraints ( logout, 31, 0 );

        GridPane.setConstraints ( fromL, 0, 3 );
        GridPane.setConstraints ( from, 2, 3 );
        GridPane.setConstraints ( toL, 0, 5 );
        GridPane.setConstraints ( to, 2, 5 );
        GridPane.setConstraints ( departtf, 0, 7 );
        GridPane.setConstraints ( departDate, 2, 7 );
        GridPane.setConstraints ( search,2,11 );


        grid.getChildren ().addAll ( fromL, from, toL, to, departtf, departDate, search);
        grid2.getChildren ().addAll ( title, user, homeButton, logout );

        VBox vb = new VBox ();
        vb.setPadding ( new Insets ( 10 ) );
        vb.setAlignment ( Pos.TOP_CENTER );

        vb.getChildren ().addAll ( grid2, grid );

        Scene scene = new Scene ( vb, 1250, 700 );

        scene.getStylesheets ().add("TableViewTheme.css");


        TableColumn<Flight, Integer> flightIdColumn = new TableColumn<>("Flight ID");
        flightIdColumn.setPrefWidth( 100 );
        flightIdColumn.setCellValueFactory (new PropertyValueFactory<>("flightId") );

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

        TableView<Flight> flightTableView = new TableView ();



        Button addFlight = new Button("Add Flight");
        addFlight.setAlignment ( Pos.BOTTOM_RIGHT );

        VBox vb1 = new VBox (20);
        vb1.setPadding ( new Insets ( 25 ) );
        vb1.getChildren ().addAll (flightTableView, addFlight);

        Scene scene2 = new Scene(vb1,800,600);

        scene2.getStylesheets ().add("TableViewTheme.css");

        Stage chooseFlight = new Stage ();
        chooseFlight.initModality ( Modality.APPLICATION_MODAL );
        chooseFlight.setTitle ( "Choose Flight" );
        chooseFlight.setScene ( scene2 );

        DateTimeFormatter dt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        flightTableView.getColumns().addAll (flightIdColumn, fromCityColumn,toCityColumn, flightDateColumn, flightTimeColumn, flightPriceColumn);

        search.setOnAction ( e-> {

            if(departDate.getValue() == null) {

                Alert alert = new Alert( Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Flight Search");
                alert.setContentText("You have entered an incorrect value!");

                alert.show();
            }else{

                if(from.getValue() == null && to.getValue() == null){
                    flightTableView.setItems ( getFlight("Atlanta (ATL)", "San Fransisco (SFO)", departDate.getValue().format(dt1)));
                }else if(from.getValue() == null){
                    flightTableView.setItems ( getFlight("Atlanta (ATL)", to.getValue(), departDate.getValue().format(dt1)));
                }else if(to.getValue() == null){
                    flightTableView.setItems ( getFlight(from.getValue(), "San Fransisco (SFO)", departDate.getValue().format(dt1)));
                }else{
                    flightTableView.setItems ( getFlight(from.getValue (), to.getValue (), departDate.getValue().format(dt1)));
                }

                chooseFlight.showAndWait ();

            }

        });

        searchFlightStage.setScene ( scene );
        searchFlightStage.show();

        addFlight.setOnAction ( e-> {

            Flight selectedFlights = flightTableView.getSelectionModel().getSelectedItem();

            Customer.addFlight ( selectedFlights.getFlightId (), Customer.userID );

            chooseFlight.close ();

        } );

        homeButton.setOnAction ( e-> searchFlightStage.close ());

        logout.setOnAction ( e ->{

            searchFlightStage.close();
            MainMenu.close();
            Customer.logout ();
        } );

    }

    private static ObservableList<Flight> getFlight(String fromCity, String toCity, String departDate){

        ObservableList<Flight> flights = FXCollections.observableArrayList ();

        try{

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT * FROM flights WHERE from_city " + "= ?" +
                            "AND to_city " + "= ?" + "AND flight_date " + "=?");
            ps.setString(1, fromCity);
            ps.setString(2, toCity);
            ps.setString(3, departDate);
            ResultSet count = ps.executeQuery();

            while(count.next()){

                flights.add(new Flight(Integer.parseInt(count.getString(1)), count.getString(2), count.getString(3),
                        count.getString(4), count.getString(5), Double.parseDouble(count.getString(6))));
            }

        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }


        return flights;
    }

}

