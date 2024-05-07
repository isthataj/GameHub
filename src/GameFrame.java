import javax.swing.JFrame;

/**
 * The {@code GameFrame} class extends {@link JFrame} to create a frame for the Snake game.
 */
public class GameFrame extends JFrame {

    /**
     * Constructs a new {@code GameFrame} instance.
     * Initializes the frame with a {@link GamePanel}, sets the title,
     * default close operation, resizability, packing, visibility, and centering of the window.
     */
    GameFrame() {
        // Add a new GamePanel to the frame
        this.add(new GamePanel());
        // Set the title of the frame
        this.setTitle("Snake");
        // Set the default close operation to exit the application
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the frame to be non-resizable
        this.setResizable(false);
        // Pack the frame to fit the preferred size and layouts of its subcomponents
        this.pack();
        // Make the frame visible
        this.setVisible(true);
        // Center the frame on the screen
        this.setLocationRelativeTo(null);
    }

}
