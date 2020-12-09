import graph.AdjacencyMatrix;
import graph.Graph;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class GameOfLife {
    public String outputBoard() {
        StringBuilder emptyBoard = emptyBoardOfSize(5,5);
    };

    private StringBuilder emptyBoardOfSize(int width, int height) {
        StringBuilder board = new StringBuilder();

        for(int i = 0; i < height; i++) {
            board.append(StringUtils.repeat(" ", width));
            board.append("\n");
        }

        return board;
    }
}
