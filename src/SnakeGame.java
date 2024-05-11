import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1100;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 100;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    JButton restartB;
    Image headImage;
    JButton menu;

    public SnakeGame() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.setLayout(null);

        // Load the snake head image
        headImage = new ImageIcon("res/images/face.jpeg").getImage();

        restartB = new JButton("Restart");
        restartB.setFont(new Font("Mono Space", Font.BOLD, 30));
        restartB.setBounds((SCREEN_WIDTH - 200) / 2, SCREEN_HEIGHT - 200, 200, 60);
        restartB.setBackground(new Color(255, 0, 0));
        restartB.setForeground(new Color(0, 0, 0));
        restartB.setOpaque(true);
        restartB.setVisible(false); // Initially hidden
        restartB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameFrame(); // Create and display a new game frame
            }
        });

        menu = new JButton("Menu");
        menu.setFont(new Font("Mono Space", Font.BOLD, 30));
        menu.setBounds((SCREEN_WIDTH - 200) / 2, SCREEN_HEIGHT - 100, 200, 60);
        menu.setBackground(new Color(255, 0, 0));
        menu.setForeground(new Color(0, 0, 0));
        menu.setOpaque(true);
        menu.setVisible(false); // Initially hidden
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu menu = new MainMenu();
                menu.setVisible(true); // Display the main menu
            }
        });

        this.add(restartB);
        this.add(menu);
        startGame(); // Start the game
    }

    /**
     * Initializes the game by generating the first apple, setting the game to the running state,
     * and starting the game timer which drives the game logic and rendering.
     */
    public void startGame() {
        newApple();          // Generate initial apple position
        running = true;      // Set game state to running
        timer = new Timer(DELAY, this);  // Create and start timer with game as ActionListener
        timer.start();
    }

    /**
     * Paint component method that is called by the Swing framework to render components.
     * It calls the superclass's paintComponent method to handle standard component painting,
     * followed by custom drawing through the draw method.
     *
     * @param g the Graphics object to be used for painting
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call superclass method for basic component painting
        draw(g);                  // Call draw method to render game-specific elements
    }


    /**
     * Renders the game's graphics including the grid, the apple, the snake, and the score.
     * If the game is running, it draws the grid lines, the apple, and each segment of the snake's body,
     * with a special rendering for the head using an image. It also displays the current score.
     * If the game is not running, it triggers the game over screen.
     *
     * @param g the Graphics object used for drawing game elements on the screen.
     */
    public void draw(Graphics g) {
        if (running) {
            // Draw grid lines
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            // Draw apple
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Draw each body part of the snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    // Draw the head image instead of a colored rectangle
                    g.drawImage(headImage, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);
                } else {
                    g.setColor(new Color(45, 180, 0));  // Dark green color for body
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            // Display score
            g.setColor(Color.RED);
            g.setFont(new Font("Mono Space", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g); // Trigger game over processing
        }
    }


    /**
     * Generates a new position for the apple on the game board.
     * The position is calculated using random coordinates within the bounds of the screen dimensions,
     * ensuring the apple appears at a location that aligns with the movement grid of the snake.
     */
    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;  // Random X coordinate within grid bounds
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE; // Random Y coordinate within grid bounds
    }


    /**
     * Updates the positions of the snake's body segments and head based on the current direction.
     * Each segment moves to the position of the segment in front of it, and the head moves in the current direction.
     * This method is essential for the snake's movement mechanics in the game.
     */
    public void move() {
        // Move each body part to the position of the previous segment
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Update the head position based on the current direction
        switch (direction) {
            case 'U':
                y[0] -= UNIT_SIZE;  // Move up
                break;
            case 'D':
                y[0] += UNIT_SIZE;  // Move down
                break;
            case 'L':
                x[0] -= UNIT_SIZE;  // Move left
                break;
            case 'R':
                x[0] += UNIT_SIZE;  // Move right
                break;
        }
    }


    /**
     * Checks if the head of the snake has collided with the apple. If so, it increases the snake's body length,
     * increments the count of apples eaten, and generates a new apple position. This method ensures new body segments
     * appear at the tail of the snake and not at the default position.
     */
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            applesEaten++;
            bodyParts += 3;
            newApple();
            // Initialize the new segments at the position of the last segment
            for (int i = bodyParts - 3; i < bodyParts; i++) {
                x[i] = x[bodyParts - 4];
                y[i] = y[bodyParts - 4];
            }
        }
    }


    public void checkCollisions() {
        // This checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        // Check if head touches left border
        if (x[0] < 0) {
            running = false;
        }
        // Check if head touches right border
        if (x[0] >= SCREEN_WIDTH) {
            running = false;
        }
        // Check if head touches top border
        if (y[0] < 0) {
            running = false;
        }
        // Check if head touches bottom border
        if (y[0] >= SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    /**
     * Displays the game over screen including the player's score and a "Game Over" message.
     * It also makes the restart and menu buttons visible, allowing the player to choose subsequent actions.
     * The method uses customized graphics settings to display the score and game over message centrally aligned.
     *
     * @param g the Graphics object used for drawing text and other graphical content on the screen
     */
    public void gameOver(Graphics g) {
        // Score
        g.setColor(Color.RED);
        g.setFont(new Font("Mono Space", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        // Game Over text
        g.setColor(Color.RED);
        g.setFont(new Font("Mono Space", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        restartB.setVisible(true);
        menu.setVisible(true);
    }


    /**
     * Handles the actions to be performed at each tick of the timer.
     * This method updates the game state by moving the snake, checking for apple consumption,
     * and checking for collisions if the game is currently running. It also triggers a repaint of the game panel.
     *
     * @param e the ActionEvent that triggered this method
     */
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
     * This class extends KeyAdapter and provides custom handling for key press events.
     * It is designed specifically for controlling the direction of the snake in the SnakeGame.
     */
    public class MyKeyAdapter extends KeyAdapter {

        /**
         * Handles key press events to change the direction of the snake based on arrow key inputs.
         * The snake cannot reverse direction; it can only turn left, right, or continue moving in the current direction.
         *
         * @param e the KeyEvent that triggered this method
         */
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

