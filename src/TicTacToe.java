import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.Random;

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


    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.getContentPane().setBackground(new Color(25, 15, 15));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        text.setBackground(new Color(173, 216, 230));
        text.setForeground(new Color(15, 25, 25));
        text.setFont(new Font("Mono Space", Font.BOLD, 75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("TicTacToe");
        text.setOpaque(true);

        title.setLayout(new BorderLayout());
        title.setBounds(0, 0, 800, 100);

        button.setLayout(new GridLayout(3, 3));
        button.setBackground(new Color(0, 0, 0));


        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button.add(buttons[i]);
            buttons[i].setFont(new Font("Mono Space", Font.BOLD, 100));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }
        title.add(text);
        frame.add(title, BorderLayout.NORTH);
        frame.add(button);
        first();
        setRestartB();
        setupResPanel();
        setMenuB();

    }

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
                restartGame();
            }
        });

    }

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

                MainMenu();
            }
        });

    }

    private void displayButtons() {
        resPanel2.setVisible(true);// Show the restart panel
        resPanel.setVisible(true);
        frame.revalidate(); // Refresh the frame to ensure visibility
        frame.repaint(); // Repaint the frame
    }


    private void restartGame() {
        frame.dispose();
        new TicTacToe();
    }
    private void MainMenu(){
        frame.dispose();
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
    }

    private void setupResPanel() {
        resPanel.setLayout(new GridLayout(2, 1));
        resPanel.setBackground(new Color(173, 216, 230));
        resPanel.add(restartB);
        resPanel.add(MenuB);
        resPanel.setVisible(false);
        frame.add(resPanel, BorderLayout.SOUTH);
    }


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


        boolean allButtonsFilled = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                allButtonsFilled = false;
                break;
            }
        }


        if (allButtonsFilled && !checkWin()) {
            text.setBackground(new Color(255, 0, 0));
            text.setText("You tie");
            setRestartB();
            setMenuB();
            displayButtons();
        }
    }
    private void displayGameOver() {
        // Logic to display game over, such as disabling all buttons or showing a message
        for (JButton button : buttons) {
            button.setEnabled(false); // Disable all buttons after game over
        }
        winO();
        winX();
    }
    private boolean allButtonsFilled() {
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }


    public void first() {

        if (random.nextInt(2) == 0) {
            firstPlayerTurn = true;
            text.setText("X's turn");
        } else {
            firstPlayerTurn = false;
            text.setText("O's turn");
        }
    }

    public boolean checkWin() {
        if ((buttons[0].getText().equals("X")) && (buttons[1].getText().equals("X")) && (buttons[2].getText().equals("X"))) {
            winX();
            return true;
        }
        if ((buttons[3].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[5].getText().equals("X"))) {
            winX();
            return true;
        }
        if ((buttons[6].getText().equals("X")) && (buttons[7].getText().equals("X")) && (buttons[8].getText().equals("X"))) {
            winX();
            return true;
        }
        if ((buttons[0].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[8].getText().equals("X"))) {
            winX();
            return true;
        }
        if ((buttons[2].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[6].getText().equals("X"))) {
            winX();
            return true;
        }
        if ((buttons[0].getText().equals("X")) && (buttons[3].getText().equals("X")) && (buttons[6].getText().equals("X"))) {
            winX();
            return true;
        }
        if ((buttons[1].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[7].getText().equals("X"))) {
            winX();
            return true;
        }
        if ((buttons[2].getText().equals("X")) && (buttons[5].getText().equals("X")) && (buttons[8].getText().equals("X"))) {
            winX();
            return true;
        }

        if ((buttons[0].getText().equals("O")) && (buttons[1].getText().equals("O")) && (buttons[2].getText().equals("O"))) {
            winO();
            return true;
        }
        if ((buttons[3].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[5].getText().equals("O"))) {
            winO();
            return true;
        }
        if ((buttons[6].getText().equals("O")) && (buttons[7].getText().equals("O")) && (buttons[8].getText().equals("O"))) {
            winO();
            return true;
        }
        if ((buttons[0].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[8].getText().equals("O"))) {
            winO();
            return true;
        }
        if ((buttons[2].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[6].getText().equals("O"))) {
            winO();
            return true;
        }
        if ((buttons[0].getText().equals("O")) && (buttons[3].getText().equals("O")) && (buttons[6].getText().equals("O"))) {
            winO();
            return true;
        }
        if ((buttons[1].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[7].getText().equals("O"))) {
            winO();
            return true;
        }
        if ((buttons[2].getText().equals("O")) && (buttons[5].getText().equals("O")) && (buttons[8].getText().equals("O"))) {
            winO();
            return true;
        }
        return false;

    }

    public void winX() {
        text.setBackground(new Color(31, 255, 0));


        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        text.setText("X wins");
        setRestartB();
        setMenuB();
        displayButtons();
    }

    public void winO() {
        text.setBackground(new Color(13, 255, 0));

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        text.setText("O wins");
        setRestartB();
        setMenuB();
        displayButtons();
    }

}
