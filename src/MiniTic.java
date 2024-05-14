import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniTic extends JDialog {
    private JButton[] buttons = new JButton[9];
    private boolean firstPlayerTurn = true; // Assuming X starts
    private int winner = 0; // 0 - no winner yet, 1 - X wins, 2 - O wins

    /**
     * Constructs a MiniTic dialog which is modal and centered relative to its owner.
     *
     * @param owner The frame from which the dialog is displayed and blocked.
     */
    public MiniTic(JFrame owner) {
        super(owner, "Mini Tic Tac Toe", true); // Make it modal
        setSize(500, 400);
        setLayout(new GridLayout(3, 3)); // Set layout to grid for the buttons.
        setLocationRelativeTo(owner); // Center the dialog relative to the owner.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Set default close operation.
        getContentPane().setBackground(new Color(25, 25, 25)); // Set the background color of the dialog.
        initializeButtons(); // Method call to initialize buttons (Assuming this method exists in your class).
    }

    /**
     * Initializes buttons for the Tic Tac Toe grid, setting up their font, adding action listeners,
     * and adding them to the dialog's content pane.
     */
    private void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Mono Space", Font.BOLD, 90));
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (button.getText().isEmpty()) {
                        if (firstPlayerTurn) {
                            button.setForeground(new Color(220, 20, 60));
                            button.setText("X");
                            firstPlayerTurn = false;
                        } else {
                            button.setForeground(new Color(0, 0, 255));
                            button.setText("O");
                            firstPlayerTurn = true;
                        }
                        checkGameStatus();
                    }
                }
            });
            add(buttons[i]);
        }
    }

    private void checkGameStatus() {
        int result = checkWin();
        if (result != 0) { // There is a winner or a tie
            winner = result;
            dispose(); // Close the window
        }
    }

    /**
     * Checks each row, column, and diagonal to determine if there is a winner or if the game is a draw.
     * @return 1 if player X wins, 2 if player O wins, 0 if the game is still ongoing, -1 if it's a draw.
     */
    private int checkWin() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = buttons[0].getText() + buttons[1].getText() + buttons[2].getText();
                    break;
                case 1:
                    line = buttons[3].getText() + buttons[4].getText() + buttons[5].getText();
                    break;
                case 2:
                    line = buttons[6].getText() + buttons[7].getText() + buttons[8].getText();
                    break;
                case 3:
                    line = buttons[0].getText() + buttons[3].getText() + buttons[6].getText();
                    break;
                case 4:
                    line = buttons[1].getText() + buttons[4].getText() + buttons[7].getText();
                    break;
                case 5:
                    line = buttons[2].getText() + buttons[5].getText() + buttons[8].getText();
                    break;
                case 6:
                    line = buttons[0].getText() + buttons[4].getText() + buttons[8].getText();
                    break;
                case 7:
                    line = buttons[2].getText() + buttons[4].getText() + buttons[6].getText();
                    break;
            }
            if (line.equals("XXX")) {
                return 1; // X wins
            } else if (line.equals("OOO")) {
                return 2; // O wins
            }
        }

        // Check for tie
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                return 0; // Game not over yet
            }
        }
        return -1; // Tie
    }

    /**
     * Returns the winner of the game.
     * @return the winner status code: 0 (no winner), 1 (X wins), 2 (O wins), or -1 (tie).
     */
    public int getWinner() {
        return winner;
    }
}