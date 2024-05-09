
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame {

    public MainMenu(){
        setTitle("Welcome to the Our Game!");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel introLabel = new JLabel("Welcome to out Game!", JLabel.CENTER);
        introLabel.setFont(new Font("Mono Space", Font.BOLD,30));
        panel.add(introLabel);

        JButton snakeButton = new JButton("Play Snake Game!!");
        snakeButton.addActionListener(new ActionListener(){

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
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                MainMenu menu = new MainMenu();
                menu.setVisible(true);
            }
        });
    }

}
