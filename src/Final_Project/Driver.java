package Final_Project;

import java.sql.*;

public class Driver {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        Utilities.createConnnection();;
        Utilities.createStatement();

        Customer c1 = new Customer();
        //c1.register();

        c1.login();

        // Close the connection
        Utilities.connection.close();


    }

}
