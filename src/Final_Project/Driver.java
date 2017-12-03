package Final_Project;
import GUI.*;
import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javax.swing.*;
import java.net.URL;
import java.sql.*;

public class Driver {

    public static void main(String[] args) throws Exception, ClassNotFoundException {

        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginScreen.setBounds(5,5,300,200);
        loginScreen.setSize(500,250);
        loginScreen.setVisible(true);

        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        Utilities.createConnnection();;
        Utilities.createStatement();

        // Close the connection
        //Utilities.connection.close();

    }

}
