import gameOfLife.cell.Cell;
import graph.AdjacencyMatrix;
import graph.Graph;

import java.util.List;

public class LiveCellsGraph {
  Graph<Cell, Integer> liveCellsGraph = new Graph<Cell, Integer>(new AdjacencyMatrix<>(-1));

  public LiveCellsGraph(List<Cell> cells) {
    this.liveCellsGraph.addNodes(cells);
  }

  public List<Cell> getCells() {
    return liveCellsGraph.getNodes();
  }

  public List<Cell> getCellsWithNumberOfNeighbours(int desiredNumberOfNeighbours) {
    return liveCellsGraph.getNodes();
  }
}
