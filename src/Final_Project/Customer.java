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
    private static int seatCount = 0;
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

    public static void addFlight(int flightID, int userID){
        try{

            String query = "Insert ignore into flights_users (flight_id, user_id) \n" +
                            "values (?, ?)";
            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setInt(1, flightID);
            ps.setInt(2, userID);

            ObservableList<Flight> flights = searchMyFlights(userID);

            PreparedStatement ps2 = Utilities.connection.prepareStatement
                    ("SELECT flight_date, flight_time, flights_seats FROM flights where flight_id = ?");
            ps2.setInt(1, flightID);

            ResultSet rs = ps2.executeQuery();

            while(rs.next()) {
                String flightDate = rs.getString(1);
                String flightTime = rs.getString(2);
                int flightsCount = rs.getInt(3);
                boolean ignore = false;

                for (int i = 0; i < flights.size(); i++) {
                    if (flights.get(i).getFlightDate().equals(flightDate) || flights.get(i).getFlightTime().equals(flightTime)) {
                        ignore = true;
                    }
                }

                if (ignore){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Flight Booking");
                    alert.setContentText("You've cannot book a conflicting flight!");

                    alert.showAndWait();
                }else if(flightsCount < seatCount){
                        Alert alert = new Alert( Alert.AlertType.ERROR);
                        alert.setTitle ( "Warning" );
                        alert.setHeaderText ( "Flight Booking" );
                        alert.setContentText ( "This flight is full, Professor Aria!" );

                        alert.showAndWait();

                }else{
                            String decrementQuery = "UPDATE flights\n" +
                                    "SET flights_seats = flights_seats - 1\n" +
                                    "Where flight_id = ?";
                            PreparedStatement decrementCount = Utilities.connection.prepareStatement(decrementQuery);
                            decrementCount.setInt(1, flightID);
                            decrementCount.execute();

                        ps.execute();

                        SQLWarning warning = ps.getWarnings();

                        if(warning != null){
                            Alert alert = new Alert( Alert.AlertType.ERROR);
                            alert.setTitle ( "Warning" );
                            alert.setHeaderText ( "Flight Booking" );
                            alert.setContentText ( "You've already booked this flight!" );

                            alert.showAndWait();
                        }else{
                            Alert alert = new Alert ( Alert.AlertType.INFORMATION );
                            alert.setTitle ( "Information" );
                            alert.setHeaderText ( "Flight Booking" );
                            alert.setContentText ( "You've successfully booked this flight!" );

                            alert.showAndWait ();
                        }
                        }
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

            String incrementQuery = "UPDATE flights\n" +
                    "SET flights_seats = flights_seats + 1\n" +
                    "Where flight_id = ?";
            PreparedStatement incrementCount = Utilities.connection.prepareStatement(incrementQuery);
            incrementCount.setInt(1, flightID);
            incrementCount.execute();


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

    public static int getFlightID() {
        return flightID;
    }

    public static void setFlightID(int flightID) {
        Customer.flightID = flightID;
    }

}
