import javax.swing.*;

public class GameTimer {

    private Timer timer;
    private int elapsedSeconds;
    private JLabel timerLabel;

    public GameTimer(JLabel timerLabel) {
        this.timerLabel = timerLabel;
        this.elapsedSeconds = 0;

        timer = new Timer(1000, e -> {
            elapsedSeconds++;
            this.timerLabel.setText("Time: " + elapsedSeconds);
        });
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void resetTimer() {
        stopTimer();
        elapsedSeconds = 0;
        timerLabel.setText("Time: 0");
    }

    public int getElapsedSeconds() {
        return elapsedSeconds;
    }
}