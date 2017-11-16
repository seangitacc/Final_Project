package Final_Project;

import java.sql.*;

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
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));

        // Close the connection
        connection.close();


    }

}
