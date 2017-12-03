package GUI;

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
 * Created by nikhilpalli on 12/1/17.
 */
public class RegisterWindow {

    public static void dispaly() {

        Stage RegisterWindow = new Stage ();
        RegisterWindow.setTitle ( "Register" );
        RegisterWindow.initModality ( Modality.APPLICATION_MODAL );


        Label fnl = new Label ( "First Name: " );
        Label lnl = new Label ( "Last Name: " );
        Label adrsl = new Label ( "Address:" );
        Label zipl = new Label ( "Zip Code:" );
        Label stl = new Label ( "State: " );
        Label unl = new Label ( "Username: " );
        Label pwl = new Label ( "Password: " );
        Label cpwl = new Label ("Confirm Password:");
        Label emaill = new Label ( "Email: " );
        Label ssnl = new Label ( "Social Security: " );
        Label sql = new Label ( "Security Quesiton: " );
        Label ans = new Label ("Security Answer: ");
        TextField fntf = new TextField ();
        TextField lntf = new TextField ();
        TextField adrstf = new TextField ();
        TextField ziptf = new TextField ();
        TextField sttf = new TextField ();
        TextField untf = new TextField ();
        TextField pwtf = new TextField ();
        TextField cpwtf = new TextField ();
        TextField emailtf = new TextField ();
        TextField ssntf = new TextField ();
        TextField sqtf = new TextField ();
        TextField anstf = new TextField (  );
        Button backButton = new Button("Back");
        Button submitButton = new Button("Submit");

        backButton.setOnAction ( e -> RegisterWindow.close() );



        GridPane grid = new GridPane ();
        grid.setPadding(new Insets ( 25 ));
        grid.setVgap ( 8 );
        grid.setHgap ( 10 );


        GridPane.setConstraints ( fnl,0,0 );
        GridPane.setConstraints ( lnl,0,1 );
        GridPane.setConstraints ( adrsl,0,2 );
        GridPane.setConstraints ( zipl,0,3 );
        GridPane.setConstraints ( stl,0,4 );
        GridPane.setConstraints ( unl,0,5 );
        GridPane.setConstraints ( pwl,0,6 );
        GridPane.setConstraints ( cpwl,0,7);
        GridPane.setConstraints ( emaill,0,8 );
        GridPane.setConstraints ( ssnl,0,9);
        GridPane.setConstraints ( sql,0,10 );
        GridPane.setConstraints ( ans,0,11 );
        GridPane.setConstraints ( fntf,1,0);
        GridPane.setConstraints ( lntf,1,1 );
        GridPane.setConstraints ( adrstf,1,2 );
        GridPane.setConstraints ( ziptf,1,3 );
        GridPane.setConstraints ( sttf,1,4 );
        GridPane.setConstraints ( untf,1,5 );
        GridPane.setConstraints ( pwtf,1,6 );
        GridPane.setConstraints ( cpwtf,1,7 );
        GridPane.setConstraints ( emailtf,1,8 );
        GridPane.setConstraints ( ssntf,1,9 );
        GridPane.setConstraints ( sqtf,1,10 );
        GridPane.setConstraints ( anstf,1,11 );
        GridPane.setConstraints ( backButton,0,13 );
        GridPane.setConstraints ( submitButton,3,13 );

        grid.getChildren ().addAll ( fnl,lnl,adrsl,zipl,stl,unl,pwl, cpwl,emaill,ssnl,sql,fntf,lntf,adrstf,ziptf,sttf,untf,pwtf, cpwtf, emailtf,ssntf,sqtf,ans, anstf, backButton,submitButton );

        grid.setAlignment ( Pos.CENTER );
        Scene registerScene = new Scene ( grid, 450,550 );

        RegisterWindow.setScene ( registerScene);
        RegisterWindow.showAndWait ();



    }


}
