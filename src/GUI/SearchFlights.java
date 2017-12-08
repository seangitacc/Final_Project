package GUI;

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

import java.sql.Date;
import java.sql.Time;

/**
 * Created by nikhilpalli on 12/6/17.
 */
public class SearchFlights {


    public static Scene display() {

        Label title = new Label ( "Search Flights" );
        Label fromL = new Label ( "From " );
        Label toL = new Label ( "To " );
        ComboBox<String> from = new ComboBox ();
        ComboBox<String> to = new ComboBox ();
        Label departtf = new Label ( "Date of Departure" );
        Label arrivetf = new Label ( "Date of Arrival" );
        DatePicker departDate = new DatePicker ();
        DatePicker arriveDate = new DatePicker ();
        Button search = new Button("Search");


        Button homeButton = new Button ( "Home" );
        Button logout = new Button ( "Logout" );
        Label user = new Label ( "Hello, <name>" );
        GridPane grid2 = new GridPane ();

        title.setFont ( new Font ( "Helvetica", 36 ) );

        from.setPromptText ( "ATL" );
        to.setPromptText ( "SFO" );

        from.getItems ().addAll (
                "Atlanta (ATL)",
                "San Fransisco(SFO)",
                "St. Louis (STL)",
                "New York (NYC)",
                "Dallas (DFW)",
                "Chicago (ORD)"
                //use database to populate
        );
        to.getItems ().addAll (
                "Atlanta (ATL)",
                "San Fransisco(SFO)",
                "St. Louis (STL)",
                "New York (NYC)",
                "Dallas (DFW)",
                "Chicago (ORD)"

                //use database to populate --------------------------
        );


        departDate.setPromptText ( "Date of Departure" );
        arriveDate.setPromptText ( "Date of Return" );

        departDate.setMaxSize ( 200, 10 );
        arriveDate.setMaxSize ( 200, 10 );

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
        GridPane.setConstraints ( arrivetf, 0, 9 );
        GridPane.setConstraints ( arriveDate, 2, 9 );
        GridPane.setConstraints ( search,2,11 );


        grid.getChildren ().addAll ( fromL, from, toL, to, departtf, departDate, arrivetf, arriveDate, search);
        grid2.getChildren ().addAll ( title, user, homeButton, logout );

        VBox vb = new VBox ();
        vb.setPadding ( new Insets ( 10 ) );
        vb.setAlignment ( Pos.TOP_CENTER );

        vb.getChildren ().addAll ( grid2, grid );

        Scene scene = new Scene ( vb, 1250, 700 );


        TableView<Flight> flightTableView = new TableView ();

        TableColumn<Flight, Integer> flightIdColumn = new TableColumn<> ( "Flight ID" );
        flightIdColumn.setPrefWidth( 100 );
        flightIdColumn.setCellValueFactory ( new PropertyValueFactory<> ("flightId") );

        TableColumn<Flight, String> fromCityColumn = new TableColumn<> ( "Departure City" );
        fromCityColumn.setPrefWidth ( 200 );
        fromCityColumn.setCellValueFactory ( new PropertyValueFactory<> ("fromCity") );

        TableColumn<Flight, String> toCityColumn = new TableColumn<> ( "Arrival City" );
        fromCityColumn.setPrefWidth ( 200 );
        fromCityColumn.setCellValueFactory ( new PropertyValueFactory<> ("toCity") );

        TableColumn<Flight, Date> flightDateColumn = new TableColumn<> ( "Departure Date" );
        fromCityColumn.setPrefWidth ( 200 );
        fromCityColumn.setCellValueFactory ( new PropertyValueFactory<> ("flightDate") );

        TableColumn<Flight, Time> flightTimeColumn = new TableColumn<> ( "Flight Time" );
        flightIdColumn.setPrefWidth ( 200 );
        flightIdColumn.setCellValueFactory ( new PropertyValueFactory<> ("flightTime") );

        TableColumn<Flight, Double> flightPriceColumn = new TableColumn<> ( "Price" );
        flightIdColumn.setPrefWidth ( 100 );
        flightIdColumn.setCellValueFactory ( new PropertyValueFactory<> ("flightPrice") );


        flightTableView.setItems ( getFlight() );

        flightTableView.getColumns().addAll (flightIdColumn, fromCityColumn,toCityColumn, flightDateColumn, flightTimeColumn,flightPriceColumn);

        Button addFlight = new Button("Add Flight");
        addFlight.setAlignment ( Pos.BOTTOM_RIGHT );

        VBox vb1 = new VBox (20);
        vb1.setPadding ( new Insets ( 25 ) );
        vb1.getChildren ().addAll (flightTableView, addFlight);

        Scene scene2 = new Scene(vb1,800,600);
        Stage chooseFlight = new Stage ();
        chooseFlight.initModality ( Modality.APPLICATION_MODAL );
        chooseFlight.setTitle ( "Choose Flight" );
        chooseFlight.setScene ( scene2 );


        search.setOnAction ( e-> chooseFlight.showAndWait () );

        return scene;
    }

    public static ObservableList<Flight> getFlight(){   //getFlight(Date filterType)

        ObservableList<Flight> flights = FXCollections.observableArrayList ();

        Flight f1 = new Flight();
        Flight f2 = new Flight();

        flights.add (f1);
        flights.add (f2);


        return flights;

        //use sql count function to get number of flights for the date, fromCity, and toCity

        /*PreparedStatement ps = Utilities.connection.prepareStatement
                ("SELECT * FROM flights WHERE + " + toCity + " = ?" + " + fromCity + " = ?" + " + date + " = ?"), ;
        ps.setString(1, filter);
        ResultSet count = ps.executeQuery();



        //populate data.



            /*PreparedStatement ps = Utilities.connection.prepareStatement
                ("SELECT * FROM flights WHERE + " + filterType + " = ?");
        ps.setString(1, filter);
        ResultSet rs = ps.executeQuery();
          */



    }

}

