import java.util.Objects;
import java.sql.*;

public class Customer {
    public static String email = "";
    private String name;
    public static boolean succes = false;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getName(), customer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }


    public static boolean validateCredentials(String email, String password) {

        String pass = null;
        String SQL_SELECT = "SELECT Customer_password FROM Customer WHERE Customer_email = '" + email + "';";

        try (Connection conn = DriverManager.getConnection(Utilities.getJdbcUrl(), Utilities.getUsername(), Utilities.getPassword())) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pass = resultSet.getString("Customer_password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (password.equals(pass)) {
            System.out.println("Success");
            return true;
        } else {
            System.out.println("Not success - try again");
            return false;
        }
    }

    public static void login(){

        System.out.println("Enter email");
        email = Utilities.promptForAnwser();
        System.out.println("Enter password");
        String password = Utilities.promptForAnwser();
        succes = validateCredentials(email,password);
    }
}
