package GUI;

import Final_Project.Admin;
import Final_Project.Customer;
import Final_Project.Driver;
import Final_Project.Flight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by nikhilpalli on 12/10/17.
 */
class ManageAllFlights {

    private static final Stage manageFlightStage = new Stage ();

    public static void display(){


        manageFlightStage.setTitle ( "AyrLyne" );

        Label title = new Label ( "Manage Flights - Admin Console" );
        Button add = new Button("Add Flight");
        Button delete = new Button("Delete Flight");
        Button update = new Button("Update Flight");

        Button homeButton = new Button ( "Home" );
        Button logout = new Button ( "Logout" );
        Label user = new Label ( "Hello, " + Customer.fullName);
        GridPane grid2 = new GridPane ();

        title.setFont ( new Font ( "Helvetica", 36 ) );

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

        GridPane.setConstraints ( add,0,5 );
        GridPane.setConstraints ( delete, 5,5);
        GridPane.setConstraints ( update,10,5 );

        grid.getChildren ().addAll ( title, homeButton,logout,user );
        grid2.getChildren ().addAll ( add,update,delete );

        VBox vb = new VBox ( 25);

        vb.getChildren ().addAll ( grid,grid2 );

        Scene scene = new Scene ( vb, 1250,700 );


       manageFlightStage.setScene (scene);
       manageFlightStage.show ();

        homeButton.setOnAction ( e-> manageFlightStage.close());

        logout.setOnAction ( e ->{

            manageFlightStage.close();
            MainMenu.close();
            Customer.logout ();
        } );

        delete.setOnAction ( e->{

           TextField flightInput = new TextField ( );
           flightInput.setPromptText ( "Enter Flight Id" );
           Button del = new Button("Delete Flight");

           VBox v = new VBox ( 25 );

           v.getChildren ().addAll ( flightInput,del );

           Scene s = new Scene ( v, 300,200 );
           Stage s1 = new Stage (  );
           s1.setScene ( s );
           s1.setTitle ( "Delete Flight" );
           s1.show();

            del.setOnAction ( e1 ->{

                if(flightInput.getText().matches("[A-Za-z]")){
                    Alert alert = new Alert ( Alert.AlertType.INFORMATION );
                    alert.setTitle ( "Information" );
                    alert.setHeaderText ( "Invalid Input" );
                    alert.setContentText ( "You must enter a number!" );

                    alert.showAndWait ();
                }else {
                    Admin.deleteFlight ( flightInput.getText () );
                    s1.close();
                }

            } );
        } );

        add.setOnAction ( e -> {

            Stage z = new Stage ();
            z.setTitle ( "Add Flight" );
            z.initModality ( Modality.APPLICATION_MODAL );

            Label t= new Label("Add Flight");
            Label fromL = new Label ( "From " );
            Label toL = new Label ( "To " );
            ComboBox<String> from = new ComboBox ();
            ComboBox<String> to = new ComboBox ();
            Label departtf = new Label ( "Date of Departure" );
            DatePicker departDate = new DatePicker ();
            Label flightTime = new Label("Flight Time");
            TextField ftInput = new TextField ();
            Label flightPrice = new Label("Flight Price");
            TextField fpInput = new TextField ();
            Button addNew = new Button("Add Flight");

            t.setFont ( new Font ( "Helvetica", 36 ) );

            from.getItems ().addAll (
                    "Atlanta (ATL)",
                    "San Fransisco (SFO)",
                    "St. Louis (STL)",
                    "New York (JFK)",
                    "Dallas (DFW)",
                    "Chicago (ORD)"

            );
            to.getItems ().addAll (
                    "Atlanta (ATL)",
                    "San Fransisco (SFO)",
                    "St. Louis (STL)",
                    "New York (JFK)",
                    "Dallas (DFW)",
                    "Chicago (ORD)"
            );

            GridPane grid4 = new GridPane ();

            GridPane.setConstraints (t,0,0  );
            GridPane.setConstraints (fromL,0,1  );
            GridPane.setConstraints ( from, 1,1 );
            GridPane.setConstraints ( toL, 0,2 );
            GridPane.setConstraints ( to,1,2 );
            GridPane.setConstraints ( departtf,0,3 );
            GridPane.setConstraints ( departDate,1,3 );
            GridPane.setConstraints ( flightTime,0,4 );
            GridPane.setConstraints ( ftInput,1,4 );
            GridPane.setConstraints ( flightPrice,0,5 );
            GridPane.setConstraints ( fpInput,1,5 );
            GridPane.setConstraints ( addNew,1,6 );

            grid4.getChildren ().addAll ( t,fromL,from,toL,to,departtf,departDate,flightTime,ftInput,flightPrice,fpInput,addNew );

            grid4.setPadding ( new Insets ( 25 ) );
            grid4.setHgap ( 10 );
            grid4.setVgap ( 25 );
            grid4.setAlignment ( Pos.CENTER );


            Scene s = new Scene(grid4, 600,500);
            z.setScene ( s );
            z.show ();

            DateTimeFormatter dt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                addNew.setOnAction(e2 -> {
                    if(from.getValue() == null || to.getValue() == null || departDate.getValue() == null || ftInput.getText() == null || fpInput.getText() == null) {

                        Alert alert = new Alert ( Alert.AlertType.INFORMATION );
                        alert.setTitle ( "Information" );
                        alert.setHeaderText ( "Flight Creation" );
                        alert.setContentText ( "You must enter the correct input for each field!" );

                        alert.showAndWait ();

                    }else {

                        Admin.addNewFlight(from.getValue(), to.getValue(), departDate.getValue().format(dt1), ftInput.getText(), fpInput.getText());
                        z.close();

                    }
                });

        } );

        update.setOnAction ( e -> {

            TextField flightInput = new TextField ( );
            flightInput.setPromptText ( "Enter Flight Id" );
            Button upd = new Button("Update Flight");

            VBox v = new VBox ( 25 );

            v.getChildren ().addAll ( flightInput,upd );

            Scene s = new Scene ( v, 300,200 );
            Stage s1 = new Stage (  );
            s1.setScene ( s );
            s1.setTitle ( "Update Flight" );
            s1.show();

            upd.setOnAction (e3->{

                if(flightInput.getText().matches("[A-Za-z]")){
                    Alert alert = new Alert ( Alert.AlertType.INFORMATION );
                    alert.setTitle ( "Information" );
                    alert.setHeaderText ( "Invalid Input" );
                    alert.setContentText ( "You must enter a number!" );

                    alert.showAndWait ();
                }else {

                    Flight f1 = Admin.searchUpdateFlights(Integer.parseInt(flightInput.getText()));

                    if (f1.getFlightId() == 0) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("Flight Search");
                        alert.setContentText("That flight does not exist!");

                        alert.showAndWait();

                    } else {

                        Label ti = new Label("Flight Id: \t\t\t\t\t\t\t" + f1.getFlightId());
                        Label fromL = new Label("From ");
                        Label toL = new Label("To ");
                        ComboBox<String> from = new ComboBox();
                        ComboBox<String> to = new ComboBox();
                        Label departtf = new Label("Date of Departure");
                        LocalDate flightDate = LocalDate.parse(f1.getFlightDate());
                        DatePicker departDate = new DatePicker(flightDate);
                        Label flightTime = new Label("Flight Time");
                        TextField ftInput = new TextField(f1.getFlightTime());
                        Label flightPrice = new Label("Flight Price");
                        TextField fpInput = new TextField("" + f1.getFlightPrice());
                        Button updateF = new Button("Update Flight");

                        from.getItems().addAll(
                                "Atlanta (ATL)",
                                "San Fransisco (SFO)",
                                "St. Louis (STL)",
                                "New York (JFK)",
                                "Dallas (DFW)",
                                "Chicago (ORD)"

                        );
                        to.getItems().addAll(
                                "Atlanta (ATL)",
                                "San Fransisco (SFO)",
                                "St. Louis (STL)",
                                "New York (JFK)",
                                "Dallas (DFW)",
                                "Chicago (ORD)"
                        );

                        from.setPromptText(f1.getFromCity());
                        to.setPromptText(f1.getToCity());

                        GridPane.setConstraints(ti, 0, 0);
                        GridPane.setConstraints(fromL, 0, 1);
                        GridPane.setConstraints(from, 1, 1);
                        GridPane.setConstraints(toL, 0, 2);
                        GridPane.setConstraints(to, 1, 2);
                        GridPane.setConstraints(departtf, 0, 3);
                        GridPane.setConstraints(departDate, 1, 3);
                        GridPane.setConstraints(flightTime, 0, 4);
                        GridPane.setConstraints(ftInput, 1, 4);
                        GridPane.setConstraints(flightPrice, 0, 5);
                        GridPane.setConstraints(fpInput, 1, 5);
                        GridPane.setConstraints(updateF, 1, 6);

                        GridPane grid3 = new GridPane();
                        grid3.setAlignment(Pos.CENTER);
                        grid3.setVgap(10);
                        grid3.setHgap(25);
                        grid3.getChildren().addAll(ti, fromL, from, toL, to, departtf, departDate, flightTime, ftInput, flightPrice, fpInput, updateF);

                        Scene scene2 = new Scene(grid3, 650, 500);
                        Stage s2 = new Stage();
                        s2.setTitle("Update Flight");
                        s2.setScene(scene2);

                        s2.show();
                        s1.close();

                        DateTimeFormatter dt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        updateF.setOnAction(e2 -> {
                            if (from.getValue() == null && to.getValue() == null) {
                                Admin.updateFlight(f1.getFromCity(), f1.getToCity(), departDate.getValue().format(dt1), ftInput.getText(), fpInput.getText(), f1.getFlightId());
                            } else if (from.getValue() == null) {
                                Admin.updateFlight(f1.getFromCity(), to.getValue(), departDate.getValue().format(dt1), ftInput.getText(), fpInput.getText(), f1.getFlightId());
                            } else if (to.getValue() == null) {
                                Admin.updateFlight(from.getValue(), f1.getToCity(), departDate.getValue().format(dt1), ftInput.getText(), fpInput.getText(), f1.getFlightId());
                            } else {
                                Admin.updateFlight(from.getValue(), to.getValue(), departDate.getValue().format(dt1), ftInput.getText(), fpInput.getText(), f1.getFlightId());
                            }

                            s2.close();

                        });


                    }

                }


            });



        } );

    }
}
