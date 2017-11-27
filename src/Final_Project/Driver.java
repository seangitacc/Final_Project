package Final_Project;
import GUI.*;

import javax.swing.*;
import java.net.URL;
import java.sql.*;

public class Driver {

    public static void main(String[] args) throws Exception, ClassNotFoundException {

        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon(new URL ("https://s.hswstatic.com/gif/airplanes-work-1.jpg")), SwingConstants.CENTER));
        window.setBounds(500, 150, 300, 200);
        window.setVisible(true);
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.dispose();

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

        Customer c1 = new Customer();
        //c1.register();

        c1.login();

        // Close the connection
        Utilities.connection.close();


    }

}
