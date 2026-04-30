import javax.swing.*;
import java.awt.*;

/**
* 
 * Lead Author(s):
 * @author Ryotaro Hikichi
 * @author 
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Gaddis, T. (2015). Starting out with Java: From control structures through objects. Addison-Wesley. 
 * 
 * Oracle. (n.d.). JFrame (Java Platform SE 8). Retrieved April 20, 2026, from 
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html
 * 
 * Oracle. (n.d.). BorderLayout (Java Platform SE 8). Retrieved April 20, 2026, from 
 * https://docs.oracle.com/javase/8/docs/api/java/awt/BorderLayout.html
 * 
 * Oracle. (n.d.). GridLayout (Java Platform SE 8). Retrieved April 20, 2026, from 
 * https://docs.oracle.com/javase/8/docs/api/java/awt/GridLayout.html
 *
 * Stack Overflow. (n.d.). JOptionPane showMessageDialog example. Retrieved April 20, 2026, from 
 * https://stackoverflow.com/questions/7080205/joptionpane-showmessagedialog-example
 *  
 * Version/Date: V1 4/23/26
 *  
 * Responsibilities of class:
 * Acts as the main window (JFrame) for the memory game.
 * Manages UI components, user interactions, and overall game flow.
 */

// MemoryGame is a JFrame
public class MemoryGame extends JFrame 
{
    // Has instructions 
    private JLabel instructionLabel;

    // Has a label for elapsed time
    private JLabel timerLabel;

    // Has a start button
    private JButton startButton;
    
    // Has a check button
    private JButton checkButton;
    
    // Has a reset button
    private JButton resetButton;

    // Has a difficulty menu
    private DifficultyMenu difficultyMenu;

    // Has a gameboard
    private GameBoard gameBoard;

    // Has a game timer
    private GameTimer gameTimer;

    // Tracks whether the game has started
    private boolean gameStarted;

    /**
     * Constructor sets up the main game window and UI
     */
    public MemoryGame() 
    {
        setTitle("Number Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use BorderLayout for main layout
        setLayout(new BorderLayout(10, 10));

        // Instruction label at top
        instructionLabel = new JLabel(
                "Memorize the pattern, then press Start Timer.",
                SwingConstants.CENTER
        );

        // Timer label below instructions
        timerLabel = new JLabel("Time: 0", SwingConstants.CENTER);

        // Panel to hold instructions and timer
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(instructionLabel);
        topPanel.add(timerLabel);

        // Difficulty selection menu
        difficultyMenu = new DifficultyMenu();

        // Create control buttons
        startButton = new JButton("Start Timer");
        checkButton = new JButton("Check");
        resetButton = new JButton("Reset");

        // Bottom panel for controls
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(difficultyMenu);
        bottomPanel.add(startButton);
        bottomPanel.add(checkButton);
        bottomPanel.add(resetButton);

        // Create timer object
        gameTimer = new GameTimer(timerLabel);

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize game board
        setupBoard();

        // Button actions
        startButton.addActionListener(e -> startGame());
        checkButton.addActionListener(e -> checkGame());
        resetButton.addActionListener(e -> resetGame());

        // Final window setup
        pack();
        setLocationRelativeTo(null); // center window
        setVisible(true);
    }

    /**
     * Sets up or resets the game board
     */
    public void setupBoard() 
    {
        // Remove old board if it exists
        if (gameBoard != null) 
        {
            remove(gameBoard);
        }

        // Get selected difficulty (3x3 or 5x5)
        int gridSize = difficultyMenu.getGridSize();

        // Create new board
        gameBoard = new GameBoard(gridSize, this);

        // Add board to center of frame
        add(gameBoard, BorderLayout.CENTER);

        // Reset game state
        gameStarted = false;
        gameTimer.resetTimer();

        // Reset instructions
        instructionLabel.setText("Memorize the pattern, then press Start Timer.");

        // Refresh UI
        revalidate();
        repaint();
    }

    /**
     * Starts the game and timer
     */
    public void startGame() 
    {
        // Prevent restarting if already started
        if (gameStarted) 
        {
            return;
        }

        // Scramble board and start timer
        gameBoard.scrambleBoard();
        gameTimer.resetTimer();
        gameTimer.startTimer();

        gameStarted = true;

        // Update instructions
        instructionLabel.setText("Swap the numbers back into the correct order, then press Check.");
    }

    /**
     * Checks if the player solved the puzzle
     */
    public void checkGame() 
    {
        // Must start game first
        if (!gameStarted) 
        {
            JOptionPane.showMessageDialog(this, "Press Start Timer first.");
            return;
        }

        // Stop timer when checking
        gameTimer.stopTimer();

        // Check if board matches correct pattern
        if (gameBoard.isCorrectOrder()) 
        {
            JOptionPane.showMessageDialog(this, "You remembered!");
            instructionLabel.setText("You remembered! Press Reset to play again.");
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "You forgot");
            instructionLabel.setText("You forgot. Press Reset to try again.");
        }

        // End game
        gameStarted = false;
    }

    /**
     * Resets game
     */
    public void resetGame() 
    {
        gameTimer.stopTimer();
        setupBoard();
    }

    /**
     * Returns whether the game has started
     */
    public boolean isGameStarted() 
    {
        return gameStarted;
    }

    /**
     * Main Launches the program
     */
    public static void main(String[] args) 
    {
        // Ensures GUI runs on Event Dispatch Thread
        SwingUtilities.invokeLater(MemoryGame::new);
    }
}