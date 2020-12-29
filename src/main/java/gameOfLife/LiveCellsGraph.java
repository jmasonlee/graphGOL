package gameOfLife;

import gameOfLife.cell.Cell;
import gameOfLife.cell.RelationshipClassifier;
import gameOfLife.cell.Relationships;
import gameOfLife.graph.AdjacencyMatrix;
import gameOfLife.graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LiveCellsGraph {
  Graph<Cell, Relationships> liveCellsGraph =
      new Graph<Cell, Relationships>(new AdjacencyMatrix<>(Relationships.DISCONNECTED));

  public LiveCellsGraph(List<Cell> cells) {
    this.liveCellsGraph.addNodes(cells);

    cells.forEach(cell -> {
      Map<Relationships, List<Cell>> relatedCells = RelationshipClassifier.classify(cell, cells).getRelationships();
      setAllRelationshipsOfTypeForCell(cell, relatedCells, Relationships.NEIGHBOUR);
      setAllRelationshipsOfTypeForCell(cell, relatedCells, Relationships.COPARENT);
    });

  }

  private void setAllRelationshipsOfTypeForCell(Cell cell, Map<Relationships, List<Cell>> relatedCells, Relationships relationshipType) {
    if(relatedCells.containsKey(relationshipType)){
      for (Cell relatedCell: relatedCells.get(relationshipType)){
        liveCellsGraph.setEdge(cell, relatedCell, relationshipType);
      }
    }
  }

  public List<Cell> getCells() {
    return liveCellsGraph.getNodes();
  }

  public List<Cell> filterCellsByNeighbourCount(int desiredNumberOfNeighbours) {
    List<Cell> cellsWithEnoughNeighbours = new ArrayList<>();

    for(Cell cell: liveCellsGraph.getNodes()) {
      if(liveCellsGraph.findAllNodesWithSpecifiedDistance(cell, Relationships.NEIGHBOUR).size() == desiredNumberOfNeighbours){
        cellsWithEnoughNeighbours.add(cell);
      }
    }

    return cellsWithEnoughNeighbours;
  }
}
