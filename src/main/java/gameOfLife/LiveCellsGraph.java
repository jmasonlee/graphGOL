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
    for(int i = 0; i < cells.size(); i++){
      Map<Relationships, List<Cell>> relatedCells = RelationshipClassifier.classify(cells.get(i),cells);
      for(Relationships relationshipType : relatedCells.keySet()){
        if(relationshipType == Relationships.NEIGHBOUR || relationshipType == Relationships.COPARENT){
          setAllRelationshipsOfTypeForCell(cells, i, relatedCells, relationshipType);
        }
      }
    }
  }

  private void setAllRelationshipsOfTypeForCell(List<Cell> cells, int i, Map<Relationships, List<Cell>> relatedCells, Relationships relationshipType) {
    for (Cell cell: relatedCells.get(relationshipType)){
      liveCellsGraph.setEdge(cells.get(i), cell, relationshipType);
    }
  }

  public List<Cell> getCells() {
    return liveCellsGraph.getNodes();
  }

  public List<Cell> getCellsWithNumberOfNeighbours(int desiredNumberOfNeighbours) {
    List<Cell> cellsWithEnoughNeighbours = new ArrayList<>();

    for(Cell cell: liveCellsGraph.getNodes()) {
      if(liveCellsGraph.findAllNodesWithSpecifiedDistance(cell, Relationships.NEIGHBOUR).size() == desiredNumberOfNeighbours){
        cellsWithEnoughNeighbours.add(cell);
      }
    }

    return cellsWithEnoughNeighbours;
  }
}
