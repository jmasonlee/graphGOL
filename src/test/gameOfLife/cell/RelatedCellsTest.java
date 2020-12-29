package gameOfLife.cell;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RelatedCellsTest {

  @Test
  public void testGetRelationshipsByType() {
    StringBuilder testOutput = new StringBuilder();

    Map<Relationships,List<Cell>> relatedCellsMap = new HashMap<>();

    List<Cell> selfList = Arrays.asList(new Cell[]{new Cell(0, -1)});
    relatedCellsMap.put(Relationships.SELF, selfList);

    List<Cell> neighbourList = Arrays.asList(new Cell[]{new Cell(-1, 0)});
    relatedCellsMap.put(Relationships.NEIGHBOUR, neighbourList);

    List<Cell> coparentList = Arrays.asList(new Cell[]{new Cell(-1, 2)});
    relatedCellsMap.put(Relationships.COPARENT, coparentList);

    List<Cell> disconnectedList = Arrays.asList(new Cell[]{new Cell(-1, 0)});
    relatedCellsMap.put(Relationships.DISCONNECTED, disconnectedList);

    RelatedCells relatedCells = new RelatedCells(relatedCellsMap);

    for(Relationships relationship: Relationships.values()){
      testOutput.append(relationship + " => " + relatedCells.getCellsOfRelationshipType(relationship) + "\n");
    }

    Approvals.verify(testOutput);
  }
}