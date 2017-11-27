package Final_Project;

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

}
