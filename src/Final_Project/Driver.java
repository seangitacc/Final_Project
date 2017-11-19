package Final_Project;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        String user = "root";
        String passwd = "gr8isc00l";

        // Establish a connection
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://35.196.66.63" + "/final-project?"
                        + "user=" + user + "&password=" + passwd + "&autoReconnect=true&useSSL=false");
        System.out.println("Database connected");

        // Create a statement
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery
                ("select * from flights");

        // Iterate through the result and print the student names
        while (resultSet.next())
            System.out.println(resultSet.getString(1));

        Customer c1 = new Customer();

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a username: ");

        c1.setUsername(input.next());

        System.out.print("Please enter a password: ");

        c1.setPassword(input.next());

        statement.executeUpdate("INSERT INTO users " + "(username, password) VALUES (" + "'" + c1.getUsername() + "' , '" + c1.getPassword() + "')");

        // Close the connection
        connection.close();


    }

}
