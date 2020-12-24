package gameOfLife;

import gameOfLife.cell.Cell;
import gameOfLife.output.BoardOutputter;

import java.util.ArrayList;
import java.util.List;

public class GameOfLife {
    private LiveCellsGraph board;

    public GameOfLife(List<Cell> initialCells) {
        board = new LiveCellsGraph(initialCells);
    }

    public String outputBoard() {
        return BoardOutputter.createBoardOutput(board.getCells()).toString();
    }

    public void next() {
        board = new LiveCellsGraph(new ArrayList<>());
        //Get list of cells to kill
            //Check cells to determine which have < 2 neighbours
        //Get list of cells to make alive
            //Do some fancy stuff with set intersections...
        //kill all isolated cells
            //Remove cells with <2 neighbours from gameOfLife.graph
        //make all viable cells alive
    }
}
