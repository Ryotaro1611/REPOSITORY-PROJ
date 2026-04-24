import javax.swing.*;
import java.awt.*;

public class MemoryGame extends JFrame {

    private JLabel instructionLabel;
    private JLabel timerLabel;

    private JButton startButton;
    private JButton checkButton;
    private JButton resetButton;

    private DifficultyMenu difficultyMenu;
    private GameBoard gameBoard;
    private GameTimer gameTimer;

    private boolean gameStarted;

    public MemoryGame() {
        setTitle("Number Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        instructionLabel = new JLabel(
                "Memorize the pattern, then press Start Timer.",
                SwingConstants.CENTER
        );

        timerLabel = new JLabel("Time: 0", SwingConstants.CENTER);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(instructionLabel);
        topPanel.add(timerLabel);

        difficultyMenu = new DifficultyMenu();

        startButton = new JButton("Start Timer");
        checkButton = new JButton("Check");
        resetButton = new JButton("Reset");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(difficultyMenu);
        bottomPanel.add(startButton);
        bottomPanel.add(checkButton);
        bottomPanel.add(resetButton);

        gameTimer = new GameTimer(timerLabel);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        setupBoard();

        startButton.addActionListener(e -> startGame());
        checkButton.addActionListener(e -> checkGame());
        resetButton.addActionListener(e -> resetGame());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setupBoard() {
        if (gameBoard != null) {
            remove(gameBoard);
        }

        int gridSize = difficultyMenu.getGridSize();
        gameBoard = new GameBoard(gridSize, this);

        add(gameBoard, BorderLayout.CENTER);

        gameStarted = false;
        gameTimer.resetTimer();
        instructionLabel.setText("Memorize the pattern, then press Start Timer.");

        revalidate();
        repaint();
        pack();
    }

    public void startGame() {
        if (gameStarted) {
            return;
        }

        gameBoard.scrambleBoard();
        gameTimer.resetTimer();
        gameTimer.startTimer();
        gameStarted = true;

        instructionLabel.setText("Swap the numbers back into the correct order, then press Check.");
    }

    public void checkGame() {
        if (!gameStarted) {
            JOptionPane.showMessageDialog(this, "Press Start Timer first.");
            return;
        }

        gameTimer.stopTimer();

        if (gameBoard.isCorrectOrder()) {
            JOptionPane.showMessageDialog(this, "You remembered!");
            instructionLabel.setText("You remembered! Press Reset to play again.");
        } else {
            JOptionPane.showMessageDialog(this, "You forgot");
            instructionLabel.setText("You forgot. Press Reset to try again.");
        }

        gameStarted = false;
    }

    public void resetGame() {
        gameTimer.stopTimer();
        setupBoard();
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemoryGame::new);
    }
}