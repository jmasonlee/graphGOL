package gameOfLife;

import gameOfLife.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class God {
    public static List<Cell> livingCellsOnNextTurn(LiveCellsGraph liveCellsGraph) {
        return liveCellsGraph.filterCellsByNeighbourCount(2);
    }
}
