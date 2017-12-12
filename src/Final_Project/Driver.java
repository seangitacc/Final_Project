package Final_Project;

import GUI.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Driver extends Application {



    public static void main(String[] args) {

        try{

            //Known bug workaround for Windows 10 computers with touchscreens
            System.setProperty("glass.accessible.force", "false");

            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            //Create a connection
            Utilities.createConnnection();
            Utilities.createStatement();

            //Launch the display
            launch ( args );

            // Close the connection
            Utilities.connection.close();

        }catch(Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }
    }

    @Override
    /**
     * Display the entry window
     */
    public void start(Stage primaryStage) throws Exception {



        try {
            primaryStage.initStyle( StageStyle.UNDECORATED);
            ImageView iv = new ImageView (new Image("http://robinson.gsu.edu/files/2015/02/20150216CPR_Arash_AkhlagiPSFC.jpg"));
            StackPane s = new StackPane ( );
            s.setAlignment ( Pos.CENTER );
            s.getChildren ().add(iv);

            Scene scene = new Scene ( s, 500,300 );

            //  scene.getStylesheets ().add("Splash.css");

            primaryStage.setScene ( scene );

            primaryStage.show();
        }
        catch (Exception e){
            System.out.println (e);
        }
        finally {
            try{
                Thread.sleep ( 10000 );
            }
            catch (Exception e){
                System.out.println(e);
            }
            finally {
                primaryStage.close ();
                EntryWindow.display ();
            }

        }


    }

}
