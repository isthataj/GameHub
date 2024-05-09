import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.Random;

/**
 * The {@code GamePanel} class extends {@link JPanel} and implements {@link ActionListener} to provide a panel where the Snake game logic is handled.
 * This includes game initialization, movement mechanics, game rendering, and game over conditions.
 */
public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 1240;
    static final int SCREEN_HEIGHT = 830;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS= (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    final int[] x = new int[GAME_UNITS];
    final int[] y= new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    JButton restartB = new JButton("Restart");
    JPanel resPanel = new JPanel();

    SnakeGame(){

    /**
     * Constructor initializes the GamePanel, sets up game properties, and starts the game.
     */

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    /**
     * Starts the game by placing the first apple, setting the game to running, and starting the timer.
     */
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    /**
     * Paints components on the panel. This method is called automatically by Swing when the panel needs to be redrawn.
     * @param g the Graphics object used for drawing.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running) {

            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }



    /**
     * Draws the current state of the game including the grid, the snake, the apple, and the score.
     * @param g the Graphics object used for drawing.
     */
    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.blue);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    //If you want random colored snake
                    //g.setColor(new Color(random.nextInt(255),random.nextInt(255 ), random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Mono Space", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    /**
     * Places a new apple randomly on the board.
     */
    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    /**
     * Updates the position of the snake based on the current direction.
     */
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] -= UNIT_SIZE;
                break;
            case 'D':
                y[0] += UNIT_SIZE;
                break;
            case 'L':
                x[0] -= UNIT_SIZE;
                break;
            case 'R':
                x[0] += UNIT_SIZE;
                break;
        }
    }

    /**
     * Checks if the head of the snake has collided with an apple. Increases the snake size and score if true.
     */
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    /**
     * Checks for collisions with the snake's body or the borders, stopping the game if a collision is detected.
     */
    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        if (x[0] < 0 || x[0] > SCREEN_WIDTH || y[0] < 0 || y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        //check if touches right border
        if(x[0] > SCREEN_WIDTH ){
            running =false;
        }
        //check if touches top border
        if(y[0] < 0 ){
            running =false;
        }
        //check if touches bottom border
        if(y[0] > SCREEN_HEIGHT){
            running =false;
        }

        if(!running){
            timer.stop();
        }
    }

    /**
     * Displays the game over screen, including the final score.
     * @param g the Graphics object used for drawing.
     */
    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Mono Space", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        g.setFont(new Font("Mono Space", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
       setRestartB();

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
        resPanel.setVisible(true);
    }


    private void restartGame() {
        new GameFrame();
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    /**
     * Custom key adapter to handle directional inputs from the keyboard.
     */
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }

        }
    }
}
