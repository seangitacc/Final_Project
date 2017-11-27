package Final_Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Customer extends User implements newUser, returningUser {

    private static int userID;


    public static void register(String username, String password, String firstName){

        try {

            Utilities.statement.executeUpdate("INSERT INTO users " + "(username, password, firstName) VALUES (" + "'" + username + "' , '" + password + "' , '" + firstName +"')");

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
                System.out.println("Your username or password is invalid!");
            }


        }catch (Exception ex){

            System.out.println(ex);

        }

    }

}
