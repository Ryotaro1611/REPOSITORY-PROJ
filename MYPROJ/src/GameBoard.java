import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard extends JPanel {

    private int gridSize;
    private TileButton[][] tiles;
    private int[][] correctPattern;
    private TileButton selectedTile;
    private MemoryGame memoryGame;

    public GameBoard(int gridSize, MemoryGame memoryGame) {
        this.gridSize = gridSize;
        this.memoryGame = memoryGame;
        this.tiles = new TileButton[gridSize][gridSize];
        this.correctPattern = new int[gridSize][gridSize];

        generatePattern();
        buildBoard();
        showPattern();
    }

    public void generatePattern() {
        List<Integer> patternValues = new ArrayList<>();

        for (int i = 1; i <= gridSize * gridSize; i++) {
            patternValues.add(i);
        }

        Collections.shuffle(patternValues);

        int index = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                correctPattern[row][col] = patternValues.get(index);
                index++;
            }
        }
    }

    public void buildBoard() {
        setLayout(new GridLayout(gridSize, gridSize, 5, 5));

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                TileButton tile = new TileButton(correctPattern[row][col], row, col);
                tile.setFont(new Font("Arial", Font.BOLD, gridSize == 3 ? 36 : 20));

                tile.addActionListener(e -> {
                    if (!memoryGame.isGameStarted()) {
                        return;
                    }

                    TileButton clicked = (TileButton) e.getSource();

                    if (selectedTile == null) {
                        selectedTile = clicked;
                        selectedTile.setBackground(Color.CYAN);
                    } else if (selectedTile == clicked) {
                        selectedTile.setBackground(null);
                        selectedTile = null;
                    } else {
                        swapTiles(selectedTile, clicked);
                        selectedTile.setBackground(null);
                        selectedTile = null;
                    }
                });

                tiles[row][col] = tile;
                add(tile);
            }
        }
    }

    public void showPattern() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                tiles[row][col].setValue(correctPattern[row][col]);
            }
        }
    }

    public void scrambleBoard() {
        List<Integer> values = new ArrayList<>();

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                values.add(correctPattern[row][col]);
            }
        }

        do {
            Collections.shuffle(values);
        } while (isSameAsCorrect(values));

        int index = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                tiles[row][col].setValue(values.get(index));
                index++;
            }
        }
    }

    private boolean isSameAsCorrect(List<Integer> values) {
        int index = 0;

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (values.get(index) != correctPattern[row][col]) {
                    return false;
                }
                index++;
            }
        }
        return true;
    }

    public void swapTiles(TileButton first, TileButton second) {
        int temp = first.getValue();
        first.setValue(second.getValue());
        second.setValue(temp);
    }

    public boolean isCorrectOrder() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (tiles[row][col].getValue() != correctPattern[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }
}