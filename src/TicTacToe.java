import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.Random;


public class TicTacToe implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title = new JPanel();
    JPanel button = new JPanel();
    JLabel text = new JLabel();
    JButton restartB = new JButton("Restart");
    JPanel resPanel = new JPanel();
    JButton[] buttons = new JButton[9];
    boolean firstPlayerTurn;

    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(25, 15,15));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        text.setBackground(new Color(173, 216, 230));
        text.setForeground(new Color(15, 25,25));
        text.setFont(new Font("Mono Space", Font.BOLD, 75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("TicTacToe");
        text.setOpaque(true);

        title.setLayout(new BorderLayout());
        title.setBounds(0, 0, 800, 100);

        button.setLayout(new GridLayout(3, 3));
        button.setBackground(new Color(0,0,0));

        for(int i = 0; i < 9; i++){
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
    }
    public void setRestartB(){
        resPanel.setLayout(new GridLayout(1, 1));
        resPanel.setBackground(new Color(173, 216, 230));
        resPanel.setBounds(0,0,800,100);
        restartB.setText("Restart");
        restartB.setFont(new Font("Mono Space", Font.BOLD, 75));
        restartB.setHorizontalAlignment(SwingConstants.CENTER);
        restartB.setOpaque(true);
        restartB.setForeground(new Color(15, 25,25));
        restartB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        resPanel.add(restartB);
        resPanel.setVisible(false);
        frame.add(resPanel, BorderLayout.SOUTH);
    }
    private void restartGame() {
        new TicTacToe();
    }
    private void displayRestartButton() {
        resPanel.setVisible(true); // Show the restart panel
        frame.revalidate(); // Refresh the frame to ensure visibility
        frame.repaint(); // Repaint the frame
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 9; i++){
            if(e.getSource() == buttons[i]){
                if(firstPlayerTurn){
                    if(buttons[i].getText().isEmpty()){
                        buttons[i].setForeground(new Color(220,20,60));
                        buttons[i].setText("X");
                        firstPlayerTurn = false;
                        text.setText("O's turn");
                        checkWin();
                    }
                }
                else{
                    if(buttons[i].getText().isEmpty()){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        firstPlayerTurn = true;
                        text.setText("X's turn");
                        checkWin();
                    }
                }

                }
        }
        boolean allButtonsFilled = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                allButtonsFilled = false;
                break;
            }
        }

        if (allButtonsFilled && !checkWin() ) {
            text.setBackground(new Color(255, 0, 0));
            text.setText("You tie");
            setRestartB();
            displayRestartButton();
        }
    }

    public void first(){

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(random.nextInt(2)==0){
            firstPlayerTurn = true;
            text.setText("X's turn");
        }
        else{
            firstPlayerTurn = false;
            text.setText("O's turn");
        }
    }

    public boolean checkWin(){
        if((buttons[0].getText().equals("X")) && (buttons[1].getText().equals("X")) && (buttons[2].getText().equals("X"))){
            winx(0, 1, 2);
            return true;
        }
        if((buttons[3].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[5].getText().equals("X"))){
            winx(3, 4, 5);
            return true;
        }
        if((buttons[6].getText().equals("X")) && (buttons[7].getText().equals("X")) && (buttons[8].getText().equals("X"))){
            winx(6, 7, 8);
            return true;
        }
        if((buttons[0].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[8].getText().equals("X"))){
            winx(0, 4, 8);
            return true;
        }
        if((buttons[2].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[6].getText().equals("X"))){
            winx(2, 4, 6);
            return true;
        }
        if((buttons[0].getText().equals("X")) && (buttons[3].getText().equals("X")) && (buttons[6].getText().equals("X"))){
            winx(0, 3, 6);
            return true;
        }
        if((buttons[1].getText().equals("X")) && (buttons[4].getText().equals("X")) && (buttons[7].getText().equals("X"))){
            winx(1, 4, 7);
            return true;
        }
        if((buttons[2].getText().equals("X")) && (buttons[5].getText().equals("X")) && (buttons[8].getText().equals("X"))){
            winx(2, 5, 8);
            return true;
        }

        if((buttons[0].getText().equals("O")) && (buttons[1].getText().equals("O")) && (buttons[2].getText().equals("O"))){
            wino(0, 1, 2);
            return true;
        }
        if((buttons[3].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[5].getText().equals("O"))){
            wino(3, 4, 5);
            return true;
        }
        if((buttons[6].getText().equals("O")) && (buttons[7].getText().equals("O")) && (buttons[8].getText().equals("O"))){
            wino(6, 7, 8);
            return true;
        }
        if((buttons[0].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[8].getText().equals("O"))){
            wino(0, 4, 8);
            return true;
        }
        if((buttons[2].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[6].getText().equals("O"))){
            wino(2, 4, 6);
            return true;
        }
        if((buttons[0].getText().equals("O")) && (buttons[3].getText().equals("O")) && (buttons[6].getText().equals("O"))){
            wino(0, 3, 6);
            return true;
        }
        if((buttons[1].getText().equals("O")) && (buttons[4].getText().equals("O")) && (buttons[7].getText().equals("O"))){
            wino(1, 4, 7);
            return true;
        }
        if((buttons[2].getText().equals("O")) && (buttons[5].getText().equals("O")) && (buttons[8].getText().equals("O"))){
            wino(2, 5, 8);
            return true;
        }
        return false;

    }

    public void winx(int x, int y, int z){
        text.setBackground(new Color(31, 255, 0));

        for(int i=0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        text.setText("X wins");
        setRestartB();
        displayRestartButton();

    }

    public void wino(int x, int y, int z){
        text.setBackground(new Color(13, 255, 0));

        for(int i=0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        text.setText("O wins");
        setRestartB();
        displayRestartButton();
    }

}
