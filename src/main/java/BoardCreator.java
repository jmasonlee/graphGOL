import cell.Cell;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class BoardCreator {

    public static String createBoardOutput(List<Cell> cells){
        StringBuilder emptyBoard = emptyBoardOfSize(getBoardWidth(), 5);
        return populateBoardWithCells(emptyBoard, cells);
    }

    private static String populateBoardWithCells(StringBuilder emptyBoard, List<Cell> cells) {
        StringBuilder populatedBoard = emptyBoard;
        cells.forEach(c -> {
            Integer positionInBoard = getCellPositionInBoard(c);
            populatedBoard.setCharAt(positionInBoard, 'X');
        });
        return populatedBoard.toString();
    }

    private static Integer getCellPositionInBoard(Cell cell) {
        return (cell.x * getBoardWidth())+ cell.x + cell.y;
    }

    private static Integer getBoardWidth() {
        return 5;
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