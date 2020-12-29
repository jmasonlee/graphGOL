package gameOfLife;

import gameOfLife.cell.Cell;
import gameOfLife.cell.RelatedCells;
import gameOfLife.cell.RelationshipClassifier;
import gameOfLife.cell.Relationships;
import gameOfLife.graph.AdjacencyMatrix;
import gameOfLife.graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class LiveCellsGraph {
  Graph<Cell, Relationships> liveCellsGraph =
      new Graph<Cell, Relationships>(new AdjacencyMatrix<>(Relationships.DISCONNECTED));

  public LiveCellsGraph(List<Cell> cells) {
    this.liveCellsGraph.addNodes(cells);

    cells.forEach(cell -> {
      RelatedCells relatedCells = RelationshipClassifier.classify(cell, cells);
      setAllRelationshipsOfTypeForCell(cell, relatedCells, Relationships.NEIGHBOUR);
      setAllRelationshipsOfTypeForCell(cell, relatedCells, Relationships.COPARENT);
    });

  }

  private void setAllRelationshipsOfTypeForCell(Cell cell, RelatedCells relatedCells, Relationships relationshipType) {
    if(relatedCells.getRelationships().containsKey(relationshipType)){
      for (Cell relatedCell: relatedCells.getRelationships().get(relationshipType)){
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
