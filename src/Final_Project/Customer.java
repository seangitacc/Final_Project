package Final_Project;

import GUI.EntryWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLWarning;

public class Customer extends User implements newUser, returningUser {

    public static int userID; //Keep track of a user session
    static int flightID; //Keep track of a flight
    public static final String adminPassword = "iluvaria"; //Used to let a user register as an admin
    public static boolean adminBool = false; //Allows the user to register as an admin
    public static String fullName = ""; //Keeps track of a user's name
    public static int adminCheck;  //Allows the user to login as an admin

    /**
     * Allow a user to register; whether they be just a customer or an admin.
     * @param firstName
     * @param lastName
     * @param addressLine
     * @param zipcode
     * @param state
     * @param username
     * @param password
     * @param email
     * @param ssn
     * @param secQuestion
     * @param secAnswer
     */
    public static void register(String firstName, String lastName, String addressLine, String zipcode, String state, String username, String password, String email, String ssn, String secQuestion, String secAnswer){

        try {

            //If not an admin, then register as customer
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

            }else{ //Otherwise, register the user as an admin

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

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

    }

    /**
     * Allows a user to log in into the susyem.
     * @param username
     * @param password
     */
    public static void login(String username, String password){

        try{

            PreparedStatement ps = Utilities.connection.prepareStatement
                            ("SELECT user_id, isAdmin FROM users WHERE username = ? AND password = ?");
            ps.setString (1, username);
            ps.setString (2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                userID = rs.getInt(1); //Keep track of a user's ID for the entire session
                adminCheck = rs.getInt(2); //Check to see if the user registered as an admin in the DB
            } else {
                //If the username and password do not return something in the database, let the user know the input is invalid
                Alert alert = new Alert( Alert.AlertType.ERROR);
                alert.setTitle ( "Warning" );
                alert.setHeaderText ( "Invalid Input!" );
                alert.setContentText ( "Please Enter Correct Username and Password" );
                alert.showAndWait ();
            }

            fullName =  Customer.getName(Customer.userID); //Keep track of a customer's name for the entire session


        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

    }

    /**
     * Used to check to see if a user inputs a correct username and SSN that exists. Part of the password recovery process.
     * @param username
     * @param ssn
     * @return
     */
    public static boolean checkUser(String username, String ssn){

        try{

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT user_id FROM users WHERE username = ? AND ssn = ?");
            ps.setString (1, username);
            ps.setString (2, ssn);
            ResultSet rs = ps.executeQuery();

            //If the user exists in the DB, return true
            if (rs.next()) {
                return true;
            }


        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

        return false;

    }

    /**
     * Collect a user's security answer by using the username and SSN verified in the checkUser() function.
     * @param username
     * @param ssn
     * @return
     */
    public static String retriveSecquestion(String username, String ssn){

        String secQuestion = "";

        try{

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT secQuestion FROM users WHERE username = ? AND ssn = ?");
            ps.setString (1, username);
            ps.setString (2, ssn);
            ResultSet rs = ps.executeQuery();

            //Collect the security question
            if (rs.next()) {
                secQuestion =  rs.getString(1);
            }


        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

        return secQuestion;

    }

    /**
     * Check to make sure a user inputs the correct security answer to their security question.
     * @param username
     * @param secAnswer
     * @return
     */
    public static String checkSecAnswer(String username, String secAnswer) {

        String answer = "";

        try {

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT secAnswer, password FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            //Collect the user's answer and check to see if it is a valid answer.
            if (rs.next()) {


                if(secAnswer.equals(rs.getString(1))) {

                    answer = rs.getString(2);


                }else{

                    answer = "invalid";

                }

            }


        } catch (Exception ex) {

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

        return answer;

    }

    /**
     * Add a flight to the flights_users table to keep track of a user's booked flights.
     * @param flightID
     * @param userID
     */
    public static void addFlight(int flightID, int userID){
        try{

            //Insert ignore allows the user to only book flights they haven't booked before
            String query = "Insert ignore into flights_users (flight_id, user_id) \n" +
                            "values (?, ?)";
            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setInt(1, flightID);
            ps.setInt(2, userID);

            //Collect the user's flights in an observable list to be displayed in a table view
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

                //If there is a conflicting date and time, the user is not allowed to book the flight
                for (Flight flight : flights) {
                    if (flight.getFlightDate().equals(flightDate) || flight.getFlightTime().equals(flightTime)) {
                        ignore = true;
                    }
                }

                //Cannot book a conflicting flight
                int seatCount = 0;
                if (ignore){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Flight Booking");
                    alert.setContentText("You've cannot book a conflicting flight!");

                    alert.showAndWait();
                }else if(flightsCount < seatCount){//Cannot book a full flight
                        Alert alert = new Alert( Alert.AlertType.ERROR);
                        alert.setTitle ( "Warning" );
                        alert.setHeaderText ( "Flight Booking" );
                        alert.setContentText ( "This flight is full, Professor Aria!" );

                        alert.showAndWait();

                }else{//If a flight is booked, lower the seat count to keep track of a full flight
                            String decrementQuery = "UPDATE flights\n" +
                                    "SET flights_seats = flights_seats - 1\n" +
                                    "Where flight_id = ?";
                            PreparedStatement decrementCount = Utilities.connection.prepareStatement(decrementQuery);
                            decrementCount.setInt(1, flightID);
                            decrementCount.execute();

                        ps.execute();

                        //Collect the warning given by the insert ignore statement. A warning is thrown if the data already exists in the DB.
                        SQLWarning warning = ps.getWarnings();

                        //Cannot book a flight that's already been booked
                        if(warning != null){
                            Alert alert = new Alert( Alert.AlertType.ERROR);
                            alert.setTitle ( "Warning" );
                            alert.setHeaderText ( "Flight Booking" );
                            alert.setContentText ( "You've already booked this flight!" );

                            alert.showAndWait();
                        }else{//Let the user know they have successfully booked a flight
                            Alert alert = new Alert ( Alert.AlertType.INFORMATION );
                            alert.setTitle ( "Information" );
                            alert.setHeaderText ( "Flight Booking" );
                            alert.setContentText ( "You've successfully booked this flight!" );

                            alert.showAndWait ();
                        }
                        }
                }

        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }
    }

    /**
     * Deletes a flight from the flights_user table so that it is no longer considered booked by the user
     * @param flightID
     * @param userID
     */
    public static void deleteFlight(int flightID, int userID){
        try{

            String query = "Delete from flights_users where flight_id = ? AND user_id = ?";

            PreparedStatement ps = Utilities.connection.prepareStatement(query);

            ps.setInt(1, flightID);
            ps.setInt(2, userID);

            ps.execute();

            //If a flight is deleted from this table, increment the seats in the DB so that the seat is no longer filled
            String incrementQuery = "UPDATE flights\n" +
                    "SET flights_seats = flights_seats + 1\n" +
                    "Where flight_id = ?";
            PreparedStatement incrementCount = Utilities.connection.prepareStatement(incrementQuery);
            incrementCount.setInt(1, flightID);
            incrementCount.execute();

            //Let the user know they have deleted the flight
            Alert alert = new Alert ( Alert.AlertType.INFORMATION );
            alert.setTitle ( "Information" );
            alert.setHeaderText ( "Flight Deletion" );
            alert.setContentText ( "You've successfully deleted this flight!" );

            alert.showAndWait ();


        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }
    }

    /**
     * A user can view the flights they have booked.
     * @param userID
     * @return flights
     */
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


            //Add each flight to an observable list so it can be displayed in a table view
            while(count.next()){

                flights.add(new Flight(Integer.parseInt(count.getString(1)), count.getString(2), count.getString(3),
                        count.getString(4), count.getString(5), Double.parseDouble(count.getString(6))));
            }

        }catch (Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }


        return flights;
    }

    /**
     * Collect the user's name to be displayed throughout the session.
     * @param userID
     * @return fullName
     */
    private static String getName(int userID){

        try {

            PreparedStatement ps = Utilities.connection.prepareStatement
                    ("SELECT firstName, lastName from users where user_id = ?" );
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                //Add the user's name from the DB
                fullName += rs.getString(1);
                fullName += " " + rs.getString(2);

            }


        }catch(Exception ex){

            ex.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Application Error");
            alert.setContentText("There has been an error. Please contact the system administrator.");

            alert.showAndWait();

        }

        return fullName;

    }

    /**
     * Certain functions to be completed when a user logs out
     */
    public static void logout(){
        Customer.userID = 0;
        Customer.fullName = "";
        Customer.adminBool = false;
        Customer.adminCheck = 0;

        EntryWindow.display ();
    }

    //Normal getters and setters for flightID
    public static int getFlightID() {
        return flightID;
    }

    public static void setFlightID(int flightID) {
        Customer.flightID = flightID;
    }

}
