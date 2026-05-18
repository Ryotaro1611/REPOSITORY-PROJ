import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
/**
* Lead Author(s):
* @author Ryotaro Hikichi
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
* Version: 2026-05-17
*/

/**
 * TileButton is associated with gameboard and a Tilebutton object to perform swaps
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
		this.board = board;
		
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
		// Do no do anything if the games has not started
		if (!board.getMemoryGame().isGameStarted())
		{
			return;
		}
		
		// First selected tile
		if(board.getSelectedTile() == null)
		{
			board.setSelectedTile(tile);
			
			// Highlight selected tile
			tile.setBackground(Color.CYAN);
		}
		
		// When clicking the same tile again
		else if (board.getSelectedTile() == tile)
		{
			tile.setBackground(null);
			
			board.setSelectedTile(null);
		}
		
		// Second selection to swap
		else
		{
			board.swapTiles(board.getSelectedTile(), tile);
			
			// remove tile highlight after the swap
			board.getSelectedTile().setBackground(null);
			
			board.setSelectedTile(null);
		}
	}
}
	