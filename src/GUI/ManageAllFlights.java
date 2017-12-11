package GUI;

import Final_Project.Admin;
import Final_Project.Customer;
import Final_Project.Driver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.awt.image.GifImageDecoder;

import java.util.Optional;

/**
 * Created by nikhilpalli on 12/10/17.
 */
public class ManageAllFlights {

    static Stage manageFlightStage = new Stage ();

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

        homeButton.setOnAction ( e-> {

            manageFlightStage.close();

        });

        logout.setOnAction ( e ->{

            manageFlightStage.close();
            MainMenu.close();
            Driver.logout ();
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
                Admin.deleteFlight ( flightInput.getText () );
                s1.close();
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
                    "New York (NYC)",
                    "Dallas (DFW)",
                    "Chicago (ORD)"

            );
            to.getItems ().addAll (
                    "Atlanta (ATL)",
                    "San Fransisco (SFO)",
                    "St. Louis (STL)",
                    "New York (NYC)",
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


        } );

    }
}
