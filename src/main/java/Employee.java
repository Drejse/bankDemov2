public class Employee {
    private final static String EMuser = "admin";
    private final static String EMpass = "ebberød420";
    public static boolean EMsucces = false;

    public static boolean validateCredentialsEmployee(String username, String password) {

        if (password.equals(EMpass) && username.equals(EMuser)) {
            System.out.println("Success");
            return true;
        } else {
            System.out.println("Not success - try again");
            return false;
        }
    }

    public static void login(){

        System.out.println("Enter Username");
        String username = Utilities.promptForAnwser();
        System.out.println("Enter password");
        String password = Utilities.promptForAnwser();
        EMsucces = validateCredentialsEmployee(username,password);
    }
}
