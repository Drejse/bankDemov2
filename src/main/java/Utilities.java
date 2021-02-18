import java.sql.*;
import java.util.Scanner;

public class Utilities {
    public static Connection conn;
    private final static String JdbcUrl = "jdbc:mysql://localhost/BankDemo?" + "autoReconnect=true&useSSL=false";
    private final static String username = "root";
    private final static String password = "Cervelo2011";

    public static String getJdbcUrl() {
        return JdbcUrl;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static void createConnection() {
        try {
            conn = DriverManager.getConnection(JdbcUrl, username, password);
            System.out.println("connected");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String promptForAnwser(){
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        return answer;
    }

    public static void createUser() {

        System.out.println("Input email: ");
        String email = Utilities.promptForAnwser();
        System.out.println("Input Username: ");
        String username = Utilities.promptForAnwser();
        System.out.println("Input Password: ");
        String password = Utilities.promptForAnwser();
        String sql = "INSERT INTO Customer (Customer_email, Customer_username, Customer_password, Customer_balance) values ('" + email + "','" + username + "','" + password + "','" + 0 + "')";
        Statement st = null;
        try {
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateBalance(int depositAmount, String email){
        int currentBalance = Account.getBalance();
        int newBalance = currentBalance + depositAmount;
        String SQL_UPDATE_BALANCE_QUERY = "UPDATE Customer SET Customer_balance = " + newBalance + " WHERE Customer_email = '" + email + "';";
        try (Connection conn = DriverManager.getConnection(getJdbcUrl(),getUsername(),getPassword())){
            Statement st = conn.createStatement();
            st.executeUpdate(SQL_UPDATE_BALANCE_QUERY);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void transferBalance(int withdrawAmount, String email1, String email2){
        int currentBalance1 = Account.getBalanceForTransfer(email1);
        int currentBalance2 = Account.getBalanceForTransfer(email2);
        int newBalance1 = currentBalance1 - withdrawAmount;
        int newBalance2 = currentBalance2 + withdrawAmount;
        String SQL_UPDATE_BALANCE_QUERY1 = "UPDATE Customer SET Customer_balance = " +
                newBalance1 + " WHERE Customer_email = '" + email1 + "';";
        String SQL_UPDATE_BALANCE_QUERY2 = "UPDATE Customer SET Customer_balance = " +
                newBalance2 + " WHERE Customer_email = '" + email2 + "';";
        int customerID1 = Utilities.getCustomerID(email1);
        int customerID2 = Utilities.getCustomerID(email2);
        String sql_addNegativeTransfer = "INSERT INTO Transactions VALUES (" +
                customerID1 + ",-" + withdrawAmount + ");";
        String sql_addPositiveTransfer = "INSERT INTO Transactions VALUES (" +
                customerID2 + "," + withdrawAmount + ");";
        try (Connection conn = DriverManager.getConnection(getJdbcUrl(),getUsername(),getPassword())){
            Statement st = conn.createStatement();
            st.executeUpdate(SQL_UPDATE_BALANCE_QUERY1);
            st.executeUpdate(SQL_UPDATE_BALANCE_QUERY2);
            st.executeUpdate(sql_addNegativeTransfer);
            st.executeUpdate(sql_addPositiveTransfer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static int getCustomerID(String email){
        int customerID = 0;
        String sql = "SELECT Customer_id FROM Customer where Customer_email = '" + email +"';";
        try(Statement stm = Utilities.conn.createStatement()) {
            ResultSet rs =stm.executeQuery(sql);
            while (rs.next())
            {
                customerID = rs.getInt("Customer_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerID;
    }
}