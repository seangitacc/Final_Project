package Final_Project;
import GUI.*;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.sql.*;

public class Driver {

    public static void main(String[] args) throws Exception, ClassNotFoundException {


//a

        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        Utilities.createConnnection();;
        Utilities.createStatement();

        // Close the connection
        //Utilities.connection.close();

    }

}
