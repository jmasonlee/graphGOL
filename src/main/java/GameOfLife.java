import graph.AdjacencyMatrix;
import graph.Graph;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class GameOfLife {
    private LiveCellsGraph board;

    public GameOfLife(List<Cell> initialCells) {
        board = new LiveCellsGraph(initialCells);
    }

    public String outputBoard() {
        return BoardCreator.createBoardOutput(board.getCells());
    }

    public void next() {
        //Get list of cells to kill
            //Check cells to determine which have < 2 neighbours
        //Get list of cells to make alive
            //Do some fancy stuff with set intersections...
        //kill all isolated cells
            //Remove cells with <2 neighbours from graph
        //make all viable cells alive
    }
}
