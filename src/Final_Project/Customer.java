package Final_Project;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customer extends User implements newUser, returningUser {

    public static int userID;
    public static int flightID;
    public static final String adminPassword = "iluvaria";
    public static boolean adminBool = false;


    public static void register(String firstName, String lastName, String addressLine, String zipcode, String state, String username, String password, String email, String ssn, String secQuestion, String secAnswer){

        try {

            String query = "INSERT INTO users " + "(username, password, firstName, lastName, addressLine, zipcode, state, email, ssn, secQuestion, secAnswer) VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, addressLine);
            ps.setString(6, zipcode);
            ps.setString(7, state);
            ps.setString(8, email);
            ps.setString(9, ssn);
            ps.setString(10, secQuestion);
            ps.setString(11, secAnswer);

            ps.execute();

        }catch (Exception ex){

            System.out.println(ex);

        }

    }

    public static void login(String username, String password){

        try{

            PreparedStatement ps = Utilities.connection.prepareStatement
                            ("SELECT user_id FROM users WHERE username = ? AND password = ?");
            ps.setString (1, username);
            ps.setString (2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Nice!");
                userID = rs.getInt(1);
            } else {
                Alert alert = new Alert( Alert.AlertType.ERROR);
                alert.setTitle ( "Warning" );
                alert.setHeaderText ( "Invalid Input!" );
                alert.setContentText ( "Please Enter Correct Username and Password" );

                alert.showAndWait ();
            }


        }catch (Exception ex){

            System.out.println(ex);

        }

    }

    public static boolean checkUser(String username, String ssn){

        try{

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT user_id FROM users WHERE username = ? AND ssn = ?");
            ps.setString (1, username);
            ps.setString (2, ssn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }


        }catch (Exception ex){

            System.out.println(ex);

        }

        return false;

    }

    public static String retriveSecquestion(String username, String ssn){

        String secQuestion = "";

        try{

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT secQuestion FROM users WHERE username = ? AND ssn = ?");
            ps.setString (1, username);
            ps.setString (2, ssn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                secQuestion =  rs.getString(1);
            }


        }catch (Exception ex){

            System.out.println(ex);

        }

        return secQuestion;

    }

    public static String checkSecAnswer(String username, String secAnswer) {

        String answer = "";

        try {

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT secAnswer, password FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {


                if(secAnswer.equals(rs.getString(1))) {

                    answer = rs.getString(2);


                }else{

                    answer = "invalid";

                }

            }


        } catch (Exception ex) {

            System.out.println(ex);

        }

        return answer;

    }

    public static void checkFlights(String filterType, String filter){
        try{

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT * FROM flights WHERE + " + filterType + " = ?");
            ps.setString(1, filter);
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {

                do{
                    System.out.print(rs.getString("flight_id") + " \t");
                    System.out.print(rs.getString("from_city") + " \t");
                    System.out.print(rs.getString("to_city") + " \t");
                    System.out.print(rs.getString("flight_date") + " \t");
                    System.out.print(rs.getString("flight_time") + " \t");
                    System.out.print(rs.getString("flight_price") + " \t");
                    System.out.println();
                }while(rs.next());

            }

        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void addFlight(int flightID, int userID){
        try{

            String query = "Insert into flights_users (flight_id, user_id) VALUES (?,?)";

            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setInt(1, flightID);
            ps.setInt(2, userID);

            ps.execute();


        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void searchMyFlights(int userID){

        try {

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT *\n" +
                            "from flights, users, flights_users\n" +
                            "where flights.flight_id = flights_users.flight_id AND\n" +
                            "users.user_id = flights_users.user_id AND\n" +
                            "users.user_id = ?;");
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {

                do {
                    System.out.print(rs.getString("flight_id") + " \t");
                    System.out.print(rs.getString("from_city") + " \t");
                    System.out.print(rs.getString("to_city") + " \t");
                    System.out.print(rs.getString("flight_date") + " \t");
                    System.out.print(rs.getString("flight_time") + " \t");
                    System.out.print(rs.getString("flight_price") + " \t");
                    System.out.println();
                } while (rs.next());

            }

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

}
