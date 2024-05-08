import java.util.Scanner;
/**
 * The {@code SnakeGame} class contains the main method to start the Snake game.
 * It creates an instance of {@link GameFrame}, which sets up the game window and initiates the game.
 */
public class SnakeGame {

    /**
     * The main method is the entry point of the application.
     * It creates a new instance of {@link GameFrame} to start the game.
     * @param args command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserAccount account = new UserAccount();
        UserAccount.selection();
        System.out.print("Please select an option: ");
        String select = scanner.next();
        if(select.equals("1")){
            account.createAccount();
            UserAccount.selection();
        }
        if (select.equals("2")) {
            account.login();
            new GameFrame(); //starts game
        }

        TicTacToe TicTac = new TicTacToe();
        //hey

    }
}
