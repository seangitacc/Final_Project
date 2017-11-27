package Final_Project;

import javax.rmi.CORBA.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Customer extends User implements newUser, returningUser {


    static void register(){

        try {

            Scanner input = new Scanner(System.in);

            Customer c1 = new Customer();

            System.out.print("Please enter a username: ");

            c1.setUsername(input.next());

            System.out.print("Please enter a password: ");

            c1.setPassword(input.next());

            Utilities.statement.executeUpdate("INSERT INTO users " + "(username, password) VALUES (" + "'" + c1.getUsername() + "' , '" + c1.getPassword() + "')");

        }catch (Exception ex){

            System.out.println(ex);

        }

    }

    static void login(){

        Scanner input = new Scanner(System.in);


        try{

        System.out.print("Please enter your username: ");

        String username = input.next();

        System.out.print("Please enter your password: ");

        String password = input.next();

            PreparedStatement ps = Utilities.connection.prepareStatement
                            ("SELECT user_id FROM users WHERE username = ? AND password = ?");
            ps.setString (1, username);
            ps.setString (2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Nice!");
            } else {
                // Quest not completed yet
            }


        }catch (Exception ex){

            System.out.println(ex);

        }

    }

}
