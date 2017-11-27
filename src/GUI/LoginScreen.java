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
        setLayout(new FlowLayout());


        welcomeMessage = new JLabel("Welcome to Airline Application");
        add(welcomeMessage);

        enterButton = new JButton("Click to Login!");
        add(enterButton);

        registerButton = new JButton("Click to Register!");
        add(registerButton);


        LoginHandler enterToApp = new LoginHandler();
        enterButton.addActionListener(enterToApp);

        RegisterHandler registerUser = new RegisterHandler();
        registerButton.addActionListener(registerUser);
    }
}

class LoginHandler implements ActionListener {

    public void actionPerformed(ActionEvent event) {


        JOptionPane.showInputDialog ( "Enter Username:", String.format ( "" ) );

        JOptionPane.showInputDialog ( "Enter Password", String.format ( "" ) );

    }
}
class RegisterHandler implements ActionListener{

    public void actionPerformed(ActionEvent event){


        JFrame frame = new JFrame();
        String name;
        String username;
        String password;
        String confirmPassword;




        name = JOptionPane.showInputDialog ( "Enter name:", String.format ( "" ) );
        username = JOptionPane.showInputDialog ( "Enter Username:", String.format ( "" ) );


        do {
            password = JOptionPane.showInputDialog ( "Enter Password", String.format ( "" ) );

            confirmPassword = JOptionPane.showInputDialog ( "Confirm Password", String.format ( "" ) );

            if (!(password.equals ( confirmPassword ))) {
                JOptionPane.showMessageDialog ( frame, "Your Passwords do not match." );
            }

        }while(!password.equals(confirmPassword));



    }

}



