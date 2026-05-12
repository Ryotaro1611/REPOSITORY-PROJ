import javax.swing.*;

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
 * Oracle. (n.d.). JRadioButton (Java Platform SE 8). Retrieved April 22, 2026, from 
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JRadioButton.html
 * 
 * Oracle. (n.d.). ButtonGroup (Java Platform SE 8). Retrieved April 22, 2026, from 
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/ButtonGroup.html
 * 
 *  
 * Version/date: V1 4/30/26 
 * 
 * Responsibilities of class:
 * Represents a difficulty selection menu with radio buttons.
 * Allows user to choose 3x3 or 5x5
 */
/**
 */

// DifficultyMenu is a Jpanel
public class DifficultyMenu extends JPanel 
{
	// Has a radio button for easy mode 3x3
    private JRadioButton easyButton;
    
    // Has a radio button for hard mode 5x5
    private JRadioButton hardButton;

    /**
     * Constructor creates and sets up the difficulty menu
     */
    public DifficultyMenu() 
    {
    	// Create easy button and set it to default
        easyButton = new JRadioButton("Easy 3x3", true);
        
        // Create hard button 
        hardButton = new JRadioButton("Hard 5x5");

        // Button group ensures only one option and can be selected at a time
        ButtonGroup group = new ButtonGroup();
        group.add(easyButton);
        group.add(hardButton);

        // Add buttons to the panel so that they appear
        add(easyButton);
        add(hardButton);
    }

    /**
     * Returns the grid size based on selected difficulty
     * @return 5 if hard is selected, otherwise 3
     */
    public int getGridSize() 
    {
    	// If hard button is selected return 5x5
        if (hardButton.isSelected()) 
        {
            return 5;
        }
        
        // Otherwise return to easy mode 3x3
        return 3;
    }
}