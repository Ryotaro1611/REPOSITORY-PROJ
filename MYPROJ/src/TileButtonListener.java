import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;
/**
* Lead Author(s):
* @author Ryotaro Hikichi 5550221052
* @author Full name; student ID
* <<Add additional lead authors here>>
*
* Other Contributors:
* Full name; student ID or contact information if not in class
* <<Add additional contributors (mentors, tutors, friends) here, with contact information>>
*
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
* 
* Oracle. (n.d.).
* ActionListener (Java Platform SE 8).
* Retrieved May 17, 2026, from
* https://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html
* 
* Oracle. (n.d.).
* ActionEvent (Java Platform SE 8).
* Retrieved May 17, 2026, from
* https://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionEvent.html
* 
* Oracle. (n.d.).
* Color (Java Platform SE 8).
* Retrieved May 17, 2026, from
* https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html
*
* Version: 6/1/26
* Responsibilities of class:
* This class handles button clicks keeps track of tile swaps and selected tiles
* 
*/


// Is a Action Listener
public class TileButtonListener implements ActionListener
{
	// Has a board
	private GameBoard board;
	
	// Has a tile
	private TileButton tile;
	/**
	 * 
	 * Purpose: creates a listener for a tile button
	 * @param board the GameBoard connected to the listener
	 * @param tile the TileButton connected to the listener
	 */
	TileButtonListener(GameBoard board, TileButton tile)
	
	{
		// Store to gameboard
		this.board = board;
		
		// Store to specific tile
		this.tile = tile;
	}
	
	/**
	 * Handles the tile button clicks to determine what to do when the user performs specific actions.
	 * 
	 * @param e the ActionEvent triggered by clicking a tile
	 */
	@Override
	public void actionPerformed(ActionEvent E)
	{
		try
		{
			// Do no do anything if the games has not started
			if (!board.getMemoryGame().isGameStarted())
			{
				throw new IllegalStateException(
						"Start timer before selecting tiles.");
			}
		
			// First selected tile
			if(board.getSelectedTile() == null)
			{
				// Make tile selected
				board.setSelectedTile(tile);
			
				// Highlight selected tile
				tile.setBackground(Color.CYAN);
			}
		
			// When clicking the same tile again
			else if (board.getSelectedTile() == tile)
			{
				// Remove the highlight 
				tile.setBackground(null);
			
				// Unselect the tile
				board.setSelectedTile(null);
			}
		
			// Second selection to swap
			else
			{
				// Swap the values between the two tiles
				board.swapTiles(board.getSelectedTile(), tile);
			
				// remove tile highlight after the swap
				board.getSelectedTile().setBackground(null);
			
				// Unselect the tile
				board.setSelectedTile(null);
			}
		}
		
			// If try block does not execute throw this exception
			catch(IllegalStateException exception)
			{
				// Display message why the game cannot start on the gui
				JOptionPane.showMessageDialog(null, exception.getMessage());
			}
	}
}
	