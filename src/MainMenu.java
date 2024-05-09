
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@code MainMenu} class extends {@link JFrame} and serves as the main menu for launching games.
 * It provides buttons to start different games such as Snake and Tic Tac Toe.
 */
public class MainMenu extends JFrame {

    /**
     * Constructs a new {@code MainMenu} instance, setting up the GUI components for the main menu.
     */
    public MainMenu() {
        setTitle("Welcome to Our Game!");
        setSize(1100, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel introLabel = new JLabel("Welcome to out Game!", JLabel.CENTER);
        introLabel.setFont(new Font("Mono Space", Font.BOLD,30));
        panel.add(introLabel);

        JButton snakeButton = new JButton("Play Snake Game!!");
        snakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameFrame();
            }});
        panel.add(snakeButton);

        JButton tictactoeButton = new JButton("Play Tic Tac Toe Game");
                tictactoeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new TicTacToe();
                    }
                });
                panel.add(tictactoeButton);


        add(panel, BorderLayout.CENTER);
    }

    /**
     * The main method that serves as the entry point for the MainMenu application.
     * It creates an instance of {@code MainMenu} and makes it visible.
     *
     * @param args command line arguments, not used in this application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenu menu = new MainMenu();
                menu.setVisible(true);
            }
        });
    }

}
