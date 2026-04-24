import javax.swing.*;

public class DifficultyMenu extends JPanel {

    private JRadioButton easyButton;
    private JRadioButton hardButton;

    public DifficultyMenu() {
        easyButton = new JRadioButton("Easy 3x3", true);
        hardButton = new JRadioButton("Hard 5x5");

        ButtonGroup group = new ButtonGroup();
        group.add(easyButton);
        group.add(hardButton);

        add(easyButton);
        add(hardButton);
    }

    public int getGridSize() {
        if (hardButton.isSelected()) {
            return 5;
        }
        return 3;
    }
}