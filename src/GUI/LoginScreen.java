package GUI;

import Final_Project.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nikhilpalli on 11/26/17.
 */

public class LoginScreen extends JFrame {

    private JButton enterButton;
    private JButton registerButton;
    private JLabel welcomeMessage;
    public JFrame frame;

    public LoginScreen(){
        super("Airplane Application");
        setLayout(new FlowLayout ());


        welcomeMessage = new JLabel("Welcome to Airline Application");
        add(welcomeMessage);

        enterButton = new JButton("Click to Login!");
        add(enterButton);

        registerButton = new JButton("Click to Register!");
        add(registerButton);


        HandlerClass enterToApp = new HandlerClass();
        enterButton.addActionListener(enterToApp);

        HandlerClassTwo registerUser = new HandlerClassTwo();
        registerButton.addActionListener(registerUser);
    }
}

class HandlerClass implements ActionListener {

    public void actionPerformed(ActionEvent event) {


        String username = JOptionPane.showInputDialog ( "Enter Username:", String.format ( "" ) );

        String password = JOptionPane.showInputDialog ( "Enter Password", String.format ( "" ) );

        Customer c1 = new Customer();
        c1.login(username, password);
        c1.setIsReturning(true);

    }
}
class HandlerClassTwo implements ActionListener{

    public void actionPerformed(ActionEvent event){


        JFrame frame = new JFrame();
        String firstName;
        String username;
        String password;
        String confirmPassword;




        firstName = JOptionPane.showInputDialog ( "Enter first name:", String.format ( "" ) );
        username = JOptionPane.showInputDialog ( "Enter Username:", String.format ( "" ) );


        do {
            password = JOptionPane.showInputDialog ( "Enter Password", String.format ( "" ) );

            confirmPassword = JOptionPane.showInputDialog ( "Confirm Password", String.format ( "" ) );

            if (!(password.equals ( confirmPassword ))) {
                JOptionPane.showMessageDialog ( frame, "Your Passwords do not match." );
            }

        }while(!password.equals(confirmPassword));

        Customer c1 = new Customer();
        c1.register(username, password, firstName);


    }

}



