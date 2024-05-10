import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniTic extends JDialog {
    private JButton[] buttons = new JButton[9];
    private boolean firstPlayerTurn = true; // Assuming X starts
    private int winner = 0; // 0 - no winner yet, 1 - X wins, 2 - O wins

    public MiniTic(JFrame owner) {
        super(owner, "Mini Tic Tac Toe", true); // Make it modal
        setSize(500, 400);
        setLayout(new GridLayout(3, 3));
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(25, 25, 25));
        initializeButtons();
    }

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

    private int checkWin() {
        // Check rows, columns, and diagonals for a win
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

    public int getWinner() {
        return winner; // Getter for the winner to be used by the main game
    }
}
