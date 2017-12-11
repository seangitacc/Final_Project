package Final_Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLWarning;

public class Customer extends User implements newUser, returningUser {

    public static int userID;
    public static int flightID;

    public static int getFlightID() {
        return flightID;
    }

    public static void setFlightID(int flightID) {
        Customer.flightID = flightID;
    }

    public static final String adminPassword = "iluvaria";
    public static boolean adminBool = false;
    public static String fullName = "";
    public static int adminCheck;

    public static void register(String firstName, String lastName, String addressLine, String zipcode, String state, String username, String password, String email, String ssn, String secQuestion, String secAnswer){

        try {

            if(!(Customer.adminBool)){

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

            }else{

                String query = "INSERT INTO users " + "(username, password, firstName, lastName, addressLine, zipcode, state, email, ssn, secQuestion, secAnswer, isAdmin) VALUES " +
                        "(?,?,?,?,?,?,?,?,?,?,?,?)";

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
                ps.setInt(12, 1);

                ps.execute();

            }

        }catch (Exception ex){

            System.out.println(ex);

        }

    }

    public static void login(String username, String password){

        try{

            PreparedStatement ps = Utilities.connection.prepareStatement
                            ("SELECT user_id, isAdmin FROM users WHERE username = ? AND password = ?");
            ps.setString (1, username);
            ps.setString (2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Nice!");
                userID = rs.getInt(1);
                adminCheck = rs.getInt(2);
            } else {
                Alert alert = new Alert( Alert.AlertType.ERROR);
                alert.setTitle ( "Warning" );
                alert.setHeaderText ( "Invalid Input!" );
                alert.setContentText ( "Please Enter Correct Username and Password" );

                alert.showAndWait ();
            }

            fullName =  Customer.getName(Customer.userID);


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

            String query = "Insert ignore into flights_users (flight_id, user_id) \n" +
                            "values (?, ?)";
            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setInt(1, flightID);
            ps.setInt(2, userID);

            ps.execute();

            SQLWarning warning = ps.getWarnings();

            if(warning != null){
                Alert alert = new Alert( Alert.AlertType.ERROR);
                alert.setTitle ( "Warning" );
                alert.setHeaderText ( "Flight Booking" );
                alert.setContentText ( "You've already booked this flight!" );

                alert.showAndWait();
            }

            else{
                Alert alert = new Alert ( Alert.AlertType.INFORMATION );
                alert.setTitle ( "Information" );
                alert.setHeaderText ( "Flight Booking" );
                alert.setContentText ( "You've successfully booked this flight!" );

                alert.showAndWait ();
            }



        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void deleteFlight(int flightID, int userID){
        try{

            String query = "Delete from flights_users where flight_id = ? AND user_id = ?";

            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setInt(1, flightID);
            ps.setInt(2, userID);

            ps.execute();


        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static ObservableList<Flight> searchMyFlights(int userID){

        ObservableList<Flight> flights = FXCollections.observableArrayList ();

        try {

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT *\n" +
                            "from flights, users, flights_users\n" +
                            "where flights.flight_id = flights_users.flight_id AND\n" +
                            "users.user_id = flights_users.user_id AND\n" +
                            "users.user_id = ?;");
            ps.setInt(1, userID);
            ResultSet count = ps.executeQuery();


            while(count.next()){

                flights.add(new Flight(Integer.parseInt(count.getString(1)), count.getString(2), count.getString(3),
                        count.getString(4), count.getString(5), Double.parseDouble(count.getString(6))));
            }

        }catch (Exception ex){
            System.out.println(ex);
        }


        return flights;
    }

    public static String getName(int userID){

        try {

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT firstName, lastName from users where user_id = ?" );
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                fullName += rs.getString(1);
                fullName += " " + rs.getString(2);

            }


        }catch(Exception ex){
            System.out.println(ex);
        }

        return fullName;

    }

}
