package Final_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Utilities {

    static String user = "root";
    static String passwd = "gr8isc00l";
    public static Connection connection;
    static Statement statement;

    public static void createConnnection(){

        try{

            connection = DriverManager.getConnection
                    ("jdbc:mysql://35.196.66.63" + "/final-project?"
                            + "user=" + user + "&password=" + passwd + "&autoReconnect=true&useSSL=false");

        }catch(Exception ex){

            System.out.println(ex);
        }

    }

    public static void createStatement(){

        try{

            statement = connection.createStatement();

        }catch(Exception ex){

            System.out.println(ex);

        }

    }

}
