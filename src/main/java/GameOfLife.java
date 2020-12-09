import graph.AdjacencyMatrix;
import graph.Graph;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class GameOfLife {
    private Graph<Cell, Integer> boardGraph = new Graph<Cell, Integer>(new AdjacencyMatrix<>(-1));

    public GameOfLife(List<Cell> cells) {
        boardGraph.addNodes(cells);
    }

    public String outputBoard() {
        StringBuilder emptyBoard = emptyBoardOfSize(5,5);
        return populateBoardWithCells(emptyBoard);
    };

    private String populateBoardWithCells(StringBuilder emptyBoard){
        StringBuilder populatedBoard = emptyBoard;
        boardGraph.getNodes().forEach(c -> {
            Integer positionInBoard = getCellPositionInBoard(c);
            populatedBoard.setCharAt(positionInBoard, 'X');
        });
        return populatedBoard.toString();
    }

    private Integer getCellPositionInBoard(Cell cell) {
        return (cell.y * getBoardWidth()) + cell.x;
    }

    private Integer getBoardWidth(){
        return 5;
    }

    private StringBuilder emptyBoardOfSize(int width, int height) {
        StringBuilder board = new StringBuilder();

        for(int i = 0; i < height; i++) {
            board.append(StringUtils.repeat(" ", width));
            board.append("\n");
        }

        return board;
    }
}
