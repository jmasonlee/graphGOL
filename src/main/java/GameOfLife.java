import graph.AdjacencyMatrix;
import graph.Graph;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class GameOfLife {
    private Graph<Cell, Integer> boardGraph = new Graph<Cell, Integer>(new AdjacencyMatrix<>(-1));

    public GameOfLife(List<Cell> initialCells) {
        boardGraph.addNodes(initialCells);
    }

    public String outputBoard() {
        return BoardCreator.createBoardOutput(boardGraph.getNodes());
    }

    public void next() {
        //Get list of cells to kill
            //Check cells to determine which have < 2 neighbours
        //Get list of cells to make alive
            //Do some fancy stuff with set intersections...
        //kill all isolated cells
            //Remove cells with <2 neighbours from graph
        boardGraph.deleteNode(boardGraph.getNodes().get(0));
        //make all viable cells alive
    }
}
