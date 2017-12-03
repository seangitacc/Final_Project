package Final_Project;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customer extends User implements newUser, returningUser {

    private static int userID;


    public static void register(String firstName, String lastName, String addressLine, String zipcode, String state, String username, String password, String email, String ssn, String secQuestion, String secAnswer){

        try {

            String query = "INSERT INTO users " + "(username, password, firstName, lastName, addressLine, zipcode, state, email, ssn, secQuestion, secAnswer) VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, addressLine);
            ps.setString(6, zipcode);
            ps.setString(7, state);
            ps.setString(8, email);
            ps.setString(9, ssn);
            ps.setString(10, secQuestion);
            ps.setString(11, secAnswer);

            ps.execute();

        }catch (Exception ex){

            System.out.println(ex);

        }

    }

    public static void login(String username, String password){

        try{

            PreparedStatement ps = Utilities.connection.prepareStatement
                            ("SELECT user_id FROM users WHERE username = ? AND password = ?");
            ps.setString (1, username);
            ps.setString (2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Nice!");
                userID = rs.getInt(1);
            } else {
                Alert alert = new Alert( Alert.AlertType.ERROR);
                alert.setTitle ( "Warning" );
                alert.setHeaderText ( "Invalid Input!" );
                alert.setContentText ( "Please Enter Correct Username and Password" );

                alert.showAndWait ();
            }


        }catch (Exception ex){

            System.out.println(ex);

        }

    }

}
