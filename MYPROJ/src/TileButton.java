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
 * <<add more references here>>
 *  
 * Version/date: 
 * 
 * Responsibilities of class:
 * Represents a tile in the game whilist storing its values and position
 * on the button
 */
/**
 */

// Tile Button is a JButton
public class TileButton extends JButton {

	// Tile button has a value
    private int value;
    
    // Tile button has a specific row
    private int row;
    
    // Tile button has a specific column
    private int col;

    /**
     * Constructor: creates a tile with a value and position
     * 
     * @param value on the tile
     * @param row in the grid
     * @param col in the grid
     */
    public TileButton(int value, int row, int col) 
    {
    	this.value = value;
        this.row = row;
        this.col = col;

        // Display the value as button test
        setText(String.valueOf(value));
        
        // Set fixed button size for consistent grid layeout
        setPreferredSize(new Dimension(90, 90));
        
    }

    /**
     * Returns the value of the tile
     */
    public int getValue() 
    {
        return value;
    }

    /**
     * Updates the tile's value and display text
     */
    public void setValue(int value) 
    {
        this.value = value;
        
        // update what is shown on the button
        setText(String.valueOf(value));
    }

    /**
     * Returns the row position of the tile
     */
    public int getRowPosition() 
    {
        return row;
    }

    /**
     * Returns the column position of the tile
     */
    public int getColPosition() 
    {
        return col;
    }
}

