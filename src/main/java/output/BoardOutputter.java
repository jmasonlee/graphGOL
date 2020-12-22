package output;

import cell.Cell;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class BoardOutputter {

    public static String createBoardOutput(List<Cell> cells){
        BoardBounds boardBounds = new BoardBounds(cells);
        StringBuilder emptyBoard = emptyBoardOfSize(boardBounds.getWidth(), boardBounds.getHeight());
        return populateBoardWithCells(emptyBoard, cells, boardBounds);
    }

    private static String populateBoardWithCells(StringBuilder board, List<Cell> cells, BoardBounds boardBounds) {
        cells.forEach(c -> {
            int positionInBoard = getCellPositionInBoard(c, boardBounds);
            board.setCharAt(positionInBoard, 'X');
        });
        return board.toString();
    }

    private static int getCellPositionInBoard(Cell cell, BoardBounds boardBounds) {
        int adjustedX = Math.abs(boardBounds.getLeftmostXValue() - cell.x);
        int adjustedY = Math.abs(boardBounds.getUpperYValue() - cell.y);
        return (adjustedY * boardBounds.getWidth())+ adjustedY + adjustedX;
    }

    private static StringBuilder emptyBoardOfSize(int width, int height) {
        StringBuilder board = new StringBuilder();

        for (int i = 0; i < height; i++) {
            board.append(StringUtils.repeat(" ", width));
            if(i < height-1){
                board.append("\n");
            }
        }

        return board;
    }
}