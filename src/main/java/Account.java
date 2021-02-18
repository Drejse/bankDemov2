import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    private List<Transaction> transactions;
    private Customer customer;

    public Account(Customer customer) {
        this.transactions = new ArrayList<>();
        this.customer = customer;
    }

    public static int getBalance(){
        int sum = 0;
        String getST = "SELECT Customer_balance FROM Customer where Customer_email = '" + Customer.email +"';";
        try(Statement stmt = Utilities.conn.createStatement()) {
            ResultSet rs =stmt.executeQuery(getST);
            while (rs.next())
            {
                sum = rs.getInt("Customer_balance");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sum;
    }

    public static int printBalance(){
        int sum = 0;
        String getST = "SELECT Customer_balance FROM Customer where Customer_email = '" + Customer.email +"';";
        try(Statement stmt = Utilities.conn.createStatement()) {
            ResultSet rs =stmt.executeQuery(getST);
            while (rs.next())
            {
                sum = rs.getInt("Customer_balance");
                System.out.println("Balance: " + sum + " $");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sum;
    }

    public int withDrawAmount(int amount){
            int sum = getBalance();
       if(amount > sum){
           System.out.println("not enough money brobeans");
       } else {
           transactions.add(new Transaction(-amount, new Date()));
       }
           return amount;
    }

    public static int getBalanceForTransfer(String email){
        int sum = 0;
        String sql = "SELECT Customer_balance FROM Customer where Customer_email = '" + email +"';";
        try(Statement stm = Utilities.conn.createStatement()) {
            ResultSet rs =stm.executeQuery(sql);
            while (rs.next())
            {
                sum = rs.getInt("Customer_balance");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sum;
    }

    public static int printTransactions(int id){
        int transactions = 0;
        String sql = "SELECT Transactions FROM Transactions where Customer_id = '" + id +"';";
        try(Statement stm = Utilities.conn.createStatement()) {
            ResultSet rs =stm.executeQuery(sql);
            System.out.println("Transactions History:");
            while (rs.next())
            {
                transactions = rs.getInt("Transactions");
                System.out.println(transactions + " $");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return transactions;
    }
}