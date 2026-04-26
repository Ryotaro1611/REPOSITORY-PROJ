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
 * Oracle. (n.d.). Timer (Java Platform SE 8). Retrieved April 22, 2026, from 
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/Timer.html
 * 
 * Stack Overflow. (n.d.). How to use a Swing Timer in Java. Retrieved April 22, 2026, from 
 * https://stackoverflow.com/questions/4044726/java-swing-timer-example
 * 
 * Version/Date: V1 4/24/26
 * 
 * Responsibilities of class:
 * Manages the game timer.
 * Tracks elapsed time and updates the timer label every second.
 */

public class GameTimer 
{
    // Gametimer has a timer
    private Timer timer;

    // GameTimer has seconds as time
    private int elapsedSeconds;

    // Gametimer has a timer label
    private JLabel timerLabel;

    /**
     * Initializes timer and label
     */
    public GameTimer(JLabel timerLabel) 
    {
        this.timerLabel = timerLabel;
        this.elapsedSeconds = 0;

        // Timer updates every 1000 ms(Oracle)
        timer = new Timer(1000, e -> 
        {
        	// increase time
            elapsedSeconds++;
            
            //update label
            this.timerLabel.setText("Time: " + elapsedSeconds);
        });
    }

    /**
     * Starts the timer
     */
    public void startTimer() 
    {
        timer.start();
    }

    /**
     * Stops the timer
     */
    public void stopTimer() 
    {
        timer.stop();
    }

    /**
     * Resets timer to 0 and updates label
     */
    public void resetTimer() 
    {
    	// stop if running
        stopTimer();
        elapsedSeconds = 0;
        timerLabel.setText("Time: 0");
    }

    /**
     * Returns elapsed time in seconds
     */
    public int getElapsedSeconds() 
    {
        return elapsedSeconds;
    }
}