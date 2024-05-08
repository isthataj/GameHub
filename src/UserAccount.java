import java.util.Scanner;
import java.util.HashMap;
public class UserAccount {
    private Scanner scanner = new Scanner(System.in);
    static HashMap<String, String> user = new HashMap<String, String>();

    public static void selection(){
        System.out.print("""
                1. Register
                2. Login
                """);
    }
    public void createAccount(){
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        if(user.containsKey(username)){
            System.out.println("This username already exists.");
        }
        else{
            System.out.println("Please enter your password: ");
            String password = scanner.nextLine();
            user.put(username, password);
            System.out.println("Your account has been created.");
        }

    }

    public void login(){
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        if(!user.containsKey(username)){
            System.out.println("Invalid username");
        }
        else{
            if(user.get(username).equals(password)){
                System.out.println("Welcome to the GameHub " + username);
            }
            else{
                System.out.println("Invalid password.");
            }
        }
    }

}
