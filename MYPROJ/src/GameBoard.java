import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
 * Oracle. (n.d.). GridLayout (Java Platform SE 8). Retrieved April 17, 2026, from 
 * https://docs.oracle.com/javase/8/docs/api/java/awt/GridLayout.html
 * 
 * Oracle. (n.d.). Collections.shuffle(List) (Java Platform SE 8). Retrieved April 17, 2026, from 
 * https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#shuffle-java.util.List-
 * 
 * Oracle. (n.d.). ArrayList (Java Platform SE 8). Retrieved April 17, 2026, from 
 * https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * 
 * Stack Overflow. (n.d.). Random shuffling of an array or list in Java. Retrieved April 18, 2026, from
 * https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
 * 
 * Version/Date: V1 5/17/26
 * 
 * Responsibilities of class:
 * Represents the game board for the memory game.
 * Creates the grid of tiles, generates the correct pattern,
 * and handles tile interactions and game logic.
 */

// GameBoard is a JPanel
public class GameBoard extends JPanel 
{
    // Has a grid size
    private int gridSize;

    // Has a 2D array storing tile buttons
    private TileButton[][] tiles;

    // Has a pattern that is correct
    private int[][] correctPattern;

    // TileButton has selectedtiles
    private TileButton selectedTile;

    // MemoryGame is a memorygame
    private MemoryGame memoryGame;
    
    private boolean gameStarted;

    /**
     * Constructor - initializes board and sets up game
     */
    public GameBoard(int gridSize, MemoryGame memoryGame) 
    {
        this.gridSize = gridSize;
        this.memoryGame = memoryGame;

        // Create tile grid and pattern storage
        this.tiles = new TileButton[gridSize][gridSize];
        this.correctPattern = new int[gridSize][gridSize];
        
        // Create correct patter
        generatePattern();
        
        // Create GUI tiles
        buildBoard();   
        
        // Display pattern
        showPattern();     
    }

    /**
     * Generates a shuffled pattern of numbers
     */
    public void generatePattern() 
    {
        List<Integer> patternValues = new ArrayList<>();

        // Fill list with values 1 to gridSize^2
        for (int i = 1; i <= gridSize * gridSize; i++) 
        {
            patternValues.add(i);
        }

        // Shuffle values randomly
        Collections.shuffle(patternValues);

        // Copy shuffled values into 2D array
        int index = 0;
        for (int row = 0; row < gridSize; row++) 
        {
            for (int col = 0; col < gridSize; col++) 
            {
                correctPattern[row][col] = patternValues.get(index);
                index++;
            }
        }
    }

    /**
     * Builds the board UI with buttons
     */
    public void buildBoard() 
    {
        // Grid layout for tiles
        setLayout(new GridLayout(gridSize, gridSize, 5, 5));

        
        for (int row = 0; row < gridSize; row++) 
        {
            for (int col = 0; col < gridSize; col++) 
            {
                // Create tile with value and position
                TileButton tile = new TileButton(correctPattern[row][col], row, col);

                // Set font size based on grid size
                tile.setFont(new Font("Arial", Font.BOLD, gridSize == 3 ? 36 : 20));

                // Add click behavior
                tile.addActionListener(new TileButtonListener(this, tile));
                   

                tiles[row][col] = tile;
                add(tile); // add tile to panel
            }
        }
    }

    /**
     * Display the correct pattern 
     */
    public void showPattern() 
    {
        for (int row = 0; row < gridSize; row++) 
        {
            for (int col = 0; col < gridSize; col++) 
            {
                tiles[row][col].setValue(correctPattern[row][col]);
            }
        }
    }

    /**
     * Scrambles the board so it is different from the correct
     */
    public void scrambleBoard() 
    {
        List<Integer> values = new ArrayList<>();

        // Collect all correct values into a list
        for (int row = 0; row < gridSize; row++) 
        {
            for (int col = 0; col < gridSize; col++) 
            {
                values.add(correctPattern[row][col]);
            }
        }

        // Shuffle until it is NOT the same
        do 
        {
            Collections.shuffle(values);
        } while (isSameAsCorrect(values));

        // Apply shuffled values to tiles
        int index = 0;
        for (int row = 0; row < gridSize; row++) 
        {
            for (int col = 0; col < gridSize; col++) 
            {
                tiles[row][col].setValue(values.get(index));
                index++;
            }
        }
    }

    /**
     * Checks if a list matches the correct pattern
     */
    private boolean isSameAsCorrect(List<Integer> values) 
    {
        int index = 0;

        for (int row = 0; row < gridSize; row++) 
        {
            for (int col = 0; col < gridSize; col++) 
            {
                if (values.get(index) != correctPattern[row][col]) 
                {
                    return false;
                }
                index++;
            }
        }
        return true;
    }

    /**
     * Swap values between the two tiles selected
     */
    public void swapTiles(TileButton first, TileButton second) 
    {
        int temp = first.getValue();
        first.setValue(second.getValue());
        second.setValue(temp);
    }

    /**
     * Checks if current board matches correct pattern
     * @return true if solved, false otherwise
     */
    public boolean isCorrectOrder() 
    {
        for (int row = 0; row < gridSize; row++) 
        {
            for (int col = 0; col < gridSize; col++) 
            {
                if (tiles[row][col].getValue() != correctPattern[row][col]) 
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * 
     * Purpose: Returns the selected tile from gameboard
     * 
     * @return the selected TileButton
     */
    public TileButton getSelectedTile()
    {
        return selectedTile;
    }

    /**
     * 
     * Purpose: Set the current selected tile on the gameboard
     * 
     * @param tile in tileButton to select
     */
    public void setSelectedTile(TileButton tile)
    {
        selectedTile = tile;
    }
	/**
	 * Purpose: Get memory game
	 * 
	 * @return MemoryGame object
	 */
	public MemoryGame getMemoryGame()
	{
		return memoryGame;
	}
	
}