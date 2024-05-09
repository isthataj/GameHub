import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.Random;

/**
 * The {@code TicTacToe} class implements a GUI-based Tic Tac Toe game.
 * It manages the game's UI, state, and player interactions.
 */
public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame("Tic Tac Toe Game");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textLabel = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean firstPlayerTurn;

    /**
     * Constructor that sets up the Tic Tac Toe game board.
     */
    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(25, 15, 15));
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(new Color(173, 216, 230));
        textLabel.setForeground(new Color(15, 25, 25));
        textLabel.setFont(new Font("Monospaced", Font.BOLD, 75));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(0, 0, 0));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Monospaced", Font.BOLD, 100));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(textLabel);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        first();
    }

    /**
     * Determines randomly which player's turn is first.
     */
    public void first() {
        if (random.nextInt(2) == 0) {
            firstPlayerTurn = true;
            textLabel.setText("X's turn");
        } else {
            firstPlayerTurn = false;
            textLabel.setText("O's turn");
        }
    }

    /**
     * Responds to button clicks and handles the turn of each player.
     *
     * @param e ActionEvent object that provides information about the event and its source.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (firstPlayerTurn) {
                    if (buttons[i].getText().isEmpty()) {
                        buttons[i].setForeground(new Color(220, 20, 60));
                        buttons[i].setText("X");
                        firstPlayerTurn = false;
                        textLabel.setText("O's turn");
                        checkWin();
                    }
                } else {
                    if (buttons[i].getText().isEmpty()) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        firstPlayerTurn = true;
                        textLabel.setText("X's turn");
                        checkWin();
                    }
                }
            }
        }
    }

    /**
     * Checks all possible combinations to see if either player has won.
     */
    public void checkWin() {
        // Check win conditions for X and O.
        if((buttons[0].getText().equals("X")) && (buttons[1].getText().equals("X")) && (buttons[2].getText().equals("X"))){
            winx(0, 1, 2);
        }
        if((buttons[3].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[5].getText().equals("X"))){
            winx(3, 4, 5);
        }
        if((buttons[6].getText().equals("X")) && (buttons[7].getText().equals("X")) && (buttons[8].getText().equals("X"))){
            winx(6, 7, 8);
        }
        if((buttons[0].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[8].getText().equals("X"))){
            winx(0, 4, 8);
        }
        if((buttons[2].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[6].getText().equals("X"))){
            winx(2, 4, 6);
        }
        if((buttons[0].getText().equals("X")) && (buttons[3].getText().equals("X")) && (buttons[6].getText().equals("X"))){
            winx(0, 3, 6);
        }
        if((buttons[1].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[7].getText().equals("X"))){
            winx(1, 4, 7);
        }
        if((buttons[2].getText().equals("X")) && (buttons[5].getText().equals("X")) && (buttons[8].getText().equals("X"))){
            winx(2, 5, 8);
        }

        if((buttons[0].getText().equals("O")) && (buttons[1].getText().equals("O")) && (buttons[2].getText().equals("O"))){
            wino(0, 1, 2);
        }
        if((buttons[3].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[5].getText().equals("O"))){
            wino(3, 4, 5);
        }
        if((buttons[6].getText().equals("O")) && (buttons[7].getText().equals("O")) && (buttons[8].getText().equals("O"))){
            wino(6, 7, 8);
        }
        if((buttons[0].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[8].getText().equals("O"))){
            wino(0, 4, 8);
        }
        if((buttons[2].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[6].getText().equals("O"))){
            wino(2, 4, 6);
        }
        if((buttons[0].getText().equals("O")) && (buttons[3].getText().equals("O")) && (buttons[6].getText().equals("O"))){
            wino(0, 3, 6);
        }
        if((buttons[1].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[7].getText().equals("O"))){
            wino(1, 4, 7);
        }
        if((buttons[2].getText().equals("O")) && (buttons[5].getText().equals("O")) && (buttons[8].getText().equals("O"))){
            wino(2, 5, 8);
        }
    }

    /**
     * Indicates a win for player X and updates the UI accordingly.
     *
     * @param x index of the first button in the winning combination
     * @param y index of the second button in the winning combination
     * @param z index of the third button in the winning combination
     */
    public void winx(int x, int y, int z) {
        buttons[x].setBackground(Color.GREEN);
        buttons[y].setBackground(Color.GREEN);
        buttons[z].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textLabel.setText("X wins");
    }

    /**
     * Indicates a win for player O and updates the UI accordingly.
     *
     * @param x index of the first button in the winning combination
     * @param y index of the second button in the winning combination
     * @param z index of the third button in the winning combination
     */
    public void wino(int x, int y, int z) {
        buttons[x].setBackground(Color.GREEN);
        buttons[y].setBackground(Color.GREEN);
        buttons[z].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textLabel.setText("O wins");
    }
}
