package output;

import cell.Cell;

import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Board {
    private int width;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int height;

    public Board(List<Cell> cellsForBoard) {
        calculateBoardDimensions(cellsForBoard);
    }

    private void calculateBoardDimensions(List<Cell> cellsForBoard){
        this.width = calculateBoardWidth(cellsForBoard);
        this.height = calculateBoardHeight(cellsForBoard);
    }

    private int calculateBoardHeight(List<Cell> cells) {
        int upperYValue = Collections.min(cells, Comparator.comparing(cell -> cell.x)).x;
        int lowerYValue = Collections.max(cells, Comparator.comparing(cell -> cell.x)).x;

        return Math.abs(upperYValue - lowerYValue);
    }

    private int calculateBoardWidth(List<Cell> cells){
        int leftmostXValue = Collections.min(cells, Comparator.comparing(cell -> cell.y)).y;
        int rightmostXValue = Collections.max(cells, Comparator.comparing(cell -> cell.y)).y;

        return Math.abs(leftmostXValue - rightmostXValue);
    }
}
