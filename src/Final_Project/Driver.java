package Final_Project;
import GUI.*;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.sql.*;

public class Driver {

    public static void main(String[] args) throws Exception, ClassNotFoundException {



        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon(new URL ("http://bestanimations.com/Transport/Aircraft/Jumbo-08-june.gif")), SwingConstants.CENTER));
        window.setBounds(450, 150, 400, 250);
        window.setVisible(true);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.dispose();

        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginScreen.setBounds(400, 150, 400, 200);
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
