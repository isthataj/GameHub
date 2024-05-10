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
        setTitle("Game Hub");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(32, 32, 32));  // Dark background for the frame

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(45, 45, 45));  // Slightly lighter than the frame background

        JLabel introLabel = new JLabel("Welcome to our Game!", JLabel.CENTER);
        introLabel.setFont(new Font("Mono Space", Font.BOLD, 80));
        introLabel.setForeground(new Color(250, 250, 250));  // White text for visibility
        panel.add(introLabel);

        JButton snakeButton = new JButton("Play Snake Game!");
        styleButton(snakeButton);
        snakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameFrame();
            }
        });
        panel.add(snakeButton);

        JButton tictactoeButton = new JButton("Play Tic Tac Toe Game");
        styleButton(tictactoeButton);
        tictactoeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicTacToe();
            }
        });
        panel.add(tictactoeButton);

        add(panel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Mono Space", Font.BOLD, 20));
        button.setForeground(new Color(255, 255, 255));  // White text
        button.setBackground(new Color(70, 130, 180));  // Soft blue background
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));  // Lighter shade when hovered
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));  // Original color when not hovered
            }
        });
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
