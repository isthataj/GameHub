import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.Random;

/**
 * The {@code TicTacToe} class represents the game logic and user interface for a Tic-Tac-Toe game.
 * It manages the game state, player turns, and interaction through the graphical user interface.
 */
public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title = new JPanel();
    JPanel button = new JPanel();
    JLabel text = new JLabel();
    JButton restartB = new JButton("Restart");
    JPanel resPanel = new JPanel();
    JButton MenuB = new JButton("Menu");
    JPanel resPanel2 = new JPanel();
    JButton[] buttons = new JButton[9];
    boolean firstPlayerTurn;

    /**
     * Constructor for {@code TicTacToe} initializes the components and game state.
     * Sets up the main frame, panels for title, buttons, and response controls with their respective functionalities.
     */

    /**
     * Constructor for {@code TicTacToe} initializes the components and game state.
     * Sets up the main frame, panels for title, buttons, and response controls with their respective functionalities.
     * It configures the frame's default close operation, size, background color, layout, and visibility.
     * Also initializes the game grid and text label with specific styles and fonts.
     */
    public TicTacToe() {
        // Set up the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.getContentPane().setBackground(new Color(25, 15, 15));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Set up the text label
        text.setBackground(new Color(173, 216, 230));
        text.setForeground(new Color(15, 25, 25));
        text.setFont(new Font("Mono Space", Font.BOLD, 75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("TicTacToe");
        text.setOpaque(true);

        // Configure the title panel
        title.setLayout(new BorderLayout());
        title.setBounds(0, 0, 800, 100);

        // Set up the button panel for TicTacToe grid
        button.setLayout(new GridLayout(3, 3));
        button.setBackground(new Color(0, 0, 0));

        // Initialize buttons for the TicTacToe grid
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button.add(buttons[i]);
            buttons[i].setFont(new Font("Mono Space", Font.BOLD, 100));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        // Add components to the frame
        title.add(text);
        frame.add(title, BorderLayout.NORTH);
        frame.add(button);

        // Initialize the game logic (methods like first(), setRestartB(), setupResPanel(), and setMenuB() should be defined elsewhere)
        first();
        setRestartB();
        setupResPanel();
        setMenuB();
    }


    /**
     * Initializes and configures the "Restart" button and its panel.
     * Sets the layout, background color, bounds, and text of the restart button.
     * Adds action listener to handle the game restart functionality when clicked.
     */
    public void setRestartB() {
        resPanel.setLayout(new GridLayout(1, 1));
        resPanel.setBackground(new Color(13, 216, 230));
        resPanel.setBounds(0, 0, 800, 100);
        restartB.setText("Restart");
        restartB.setFont(new Font("Mono Space", Font.BOLD, 75));
        restartB.setHorizontalAlignment(SwingConstants.CENTER);
        restartB.setOpaque(true);
        restartB.setForeground(new Color(15, 25, 25));
        restartB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();  // This method must be defined to specify what restarting the game entails
            }
        });
    }

    /**
     * Initializes and configures the "Menu" button and its panel.
     * Sets the layout, background color, bounds, and text of the menu button.
     * Adds action listener to handle the transition back to the main menu when clicked.
     */
    public void setMenuB() {
        resPanel2.setLayout(new GridLayout(1, 1));
        resPanel2.setBackground(new Color(173, 216, 230));
        resPanel2.setBounds(0, 0, 800, 100);
        MenuB.setText("Menu");
        MenuB.setFont(new Font("Mono Space", Font.BOLD, 75));
        MenuB.setHorizontalAlignment(SwingConstants.CENTER);
        MenuB.setOpaque(true);
        MenuB.setForeground(new Color(15, 25, 25));
        MenuB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu();  // This method must be defined to specify what returning to the main menu entails
            }
        });
    }


    /**
     * Makes the control panels visible and refreshes the game frame to update the user interface.
     * This includes making the restart and menu button panels visible, revalidating, and repainting the frame.
     */
    private void displayButtons() {
        resPanel2.setVisible(true); // Show the menu panel
        resPanel.setVisible(true);  // Show the restart panel
        frame.revalidate();         // Refresh the frame to ensure updates are visible
        frame.repaint();            // Repaint the frame to reflect changes
    }

    /**
     * Restarts the game by disposing of the current frame and creating a new instance of {@code TicTacToe}.
     * This method effectively resets the game to its initial state.
     */
    private void restartGame() {
        frame.dispose();           // Close the current frame
        new TicTacToe();           // Create a new game instance
    }

    /**
     * Closes the current game and displays the main menu.
     * This method disposes of the current game frame and initializes the main menu.
     */
    private void MainMenu() {
        frame.dispose();           // Close the current frame
        MainMenu menu = new MainMenu(); // Create a new main menu
        menu.setVisible(true);     // Make the main menu visible
    }

    /**
     * Sets up the response panel which contains the restart and menu buttons.
     * Configures the layout, background, and visibility of the panel, and adds it to the south region of the frame.
     */
    private void setupResPanel() {
        resPanel.setLayout(new GridLayout(2, 1)); // Set layout of the response panel
        resPanel.setBackground(new Color(173, 216, 230)); // Set background color
        resPanel.add(restartB); // Add restart button to panel
        resPanel.add(MenuB);    // Add menu button to panel
        resPanel.setVisible(false); // Initially hide the panel
        frame.add(resPanel, BorderLayout.SOUTH); // Add panel to the frame
    }



    /**
     * Responds to button clicks in the TicTacToe grid.
     * When a grid button is clicked and is empty, it triggers a mini-game.
     * After the mini-game, the button is updated based on the result. If the result is 1, an "X" is placed,
     * and if the result is 2, an "O" is placed. It then checks if this move ends the game either by winning
     * or filling up all buttons. If all buttons are filled and there's no winner, it declares a tie.
     *
     * @param e the {@link ActionEvent} that triggered this method, representing a button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i] && buttons[i].getText().isEmpty()) {
                // Launch the mini-game
                MiniTic miniGame = new MiniTic(frame);
                miniGame.setVisible(true);  // Show the mini-game and wait for it to close
                int result = miniGame.getWinner();

                // Update the main game button based on the mini-game result
                if (result == 1) {
                    buttons[i].setText("X");
                    buttons[i].setForeground(new Color(220, 20, 60)); // Color for X
                } else if (result == 2) {
                    buttons[i].setText("O");
                    buttons[i].setForeground(new Color(0, 0, 255)); // Color for O
                }

                // Check if this move ends the game
                if (checkWin() || allButtonsFilled()) {
                    displayGameOver(); // Handle game over logic
                }
                break;
            }
        }

        // Check if all buttons are filled, indicating a draw
        boolean allButtonsFilled = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                allButtonsFilled = false;
                break;
            }
        }

        // If all buttons are filled and no winner, declare a tie and display buttons
        if (allButtonsFilled && !checkWin()) {
            text.setBackground(new Color(255, 0, 0));
            text.setText("You tie");
            setRestartB();
            setMenuB();
            displayButtons();
        }
    }

    /**
     * Handles the game over logic, such as disabling all buttons to prevent further interaction
     * and invoking methods to determine if there was a win for X or O.
     * This method ensures no more moves can be made once the game ends.
     */
    private void displayGameOver() {
        for (JButton button : buttons) {
            button.setEnabled(false); // Disable all buttons after game over
        }
        winO();  // Check if O has won and handle accordingly
        winX();  // Check if X has won and handle accordingly
    }

    /**
     * Checks if all buttons in the TicTacToe grid are filled.
     * This method is used to check for a draw condition when no more moves can be made.
     *
     * @return true if all buttons are filled, false otherwise
     */
    private boolean allButtonsFilled() {
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines which player starts the game by randomly setting the first player's turn.
     * Updates the text label to indicate that it is "Ultimate TicTacToe" and whose turn it is to play.
     */
    public void first() {
        if (random.nextInt(2) == 0) {
            firstPlayerTurn = true;
            text.setText("Ultimate TicTacToe");
        } else {
            firstPlayerTurn = false;
            text.setText("Ultimate TicTacToe");
        }
    }


    /**
     * Checks if either player has won the game by forming a straight line of their marks ('X' or 'O').
     * The method checks all possible win conditions (horizontal, vertical, and diagonal lines).
     * If a win is detected, it calls either {@code winX()} or {@code winO()} depending on the winner,
     * and returns true to indicate a player has won.
     *
     * @return true if a player wins, false otherwise
     */
    public boolean checkWin() {
        // Check rows, columns, and diagonals for 'X'
        if ((buttons[0].getText().equals("X")) && (buttons[1].getText().equals("X")) && (buttons[2].getText().equals("X")) ||
                (buttons[3].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[5].getText().equals("X")) ||
                (buttons[6].getText().equals("X")) && (buttons[7].getText().equals("X")) && (buttons[8].getText().equals("X")) ||
                (buttons[0].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[8].getText().equals("X")) ||
                (buttons[2].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[6].getText().equals("X")) ||
                (buttons[0].getText().equals("X")) && (buttons[3].getText().equals("X")) && (buttons[6].getText().equals("X")) ||
                (buttons[1].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[7].getText().equals("X")) ||
                (buttons[2].getText().equals("X")) && (buttons[5].getText().equals("X")) && (buttons[8].getText().equals("X"))) {
            winX();
            return true;
        }
        // Check rows, columns, and diagonals for 'O'
        if ((buttons[0].getText().equals("O")) && (buttons[1].getText().equals("O")) && (buttons[2].getText().equals("O")) ||
                (buttons[3].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[5].getText().equals("O")) ||
                (buttons[6].getText().equals("O")) && (buttons[7].getText().equals("O")) && (buttons[8].getText().equals("O")) ||
                (buttons[0].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[8].getText().equals("O")) ||
                (buttons[2].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[6].getText().equals("O")) ||
                (buttons[0].getText().equals("O")) && (buttons[3].getText().equals("O")) && (buttons[6].getText().equals("O")) ||
                (buttons[1].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[7].getText().equals("O")) ||
                (buttons[2].getText().equals("O")) && (buttons[5].getText().equals("O")) && (buttons[8].getText().equals("O"))) {
            winO();
            return true;
        }
        // No win found
        return false;
    }


    /**
     * Handles the game logic for when player X wins.
     * This method updates the text color and text message to indicate that X has won,
     * disables all the buttons to prevent further moves, and displays restart and menu options.
     */
    public void winX() {
        text.setBackground(new Color(31, 255, 0));  // Set background color to green
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);  // Disable all buttons
        }
        text.setText("X wins");  // Set text to "X wins"
        setRestartB();  // Configure restart button
        setMenuB();     // Configure menu button
        displayButtons();  // Make buttons visible
    }

    /**
     * Handles the game logic for when player O wins.
     * This method updates the text color and text message to indicate that O has won,
     * disables all the buttons to prevent further moves, and displays restart and menu options.
     */
    public void winO() {
        text.setBackground(new Color(13, 255, 0));  // Set background color to green
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);  // Disable all buttons
        }
        text.setText("O wins");  // Set text to "O wins"
        setRestartB();  // Configure restart button
        setMenuB();     // Configure menu button
        displayButtons();  // Make buttons visible
    }

}
