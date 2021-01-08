package gameOfLife;

import gameOfLife.TestUtils.NeighbourGenerator;
import gameOfLife.cell.Cell;
import gameOfLife.output.BoardOutputter;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GodTest {
  @Test
  public void testDoesNotReturnIsolatedCells() {
    LiveCellsGraph liveCellsGraph = new LiveCellsGraph(Arrays.asList(new Cell[]{new Cell(0,1)}));
    assertEquals(new ArrayList<>(), God.livingCellsOnNextTurn(liveCellsGraph));
  }

  @Test
  public void returnsCellsWithEnoughNeighbours(){
    List<List<Cell>> cellsAndNeighbours = new ArrayList<>();

    List<Cell> centres = createRegularlySpacedCentres(9);

    List<Cell> allPossibleNeighbours = NeighbourGenerator.generateAllPossibleNeighboursForCell(centres.get(0));
    List<Cell> cellWithTwoNeighbours = NeighbourGenerator.allCombinationsOfNeighbours(allPossibleNeighbours, 2).get(0);
    cellWithTwoNeighbours.add(centres.get(0));

    cellsAndNeighbours.add(cellWithTwoNeighbours);

    StringBuilder toVerify = new StringBuilder();
    cellsAndNeighbours.forEach(cellGroup -> {
      toVerify.append(BoardOutputter.createBoardOutput(cellGroup));
      toVerify.append("\n\n");

      gameOfLife.LiveCellsGraph liveCellsGraph = new gameOfLife.LiveCellsGraph(cellWithTwoNeighbours);

      toVerify.append(BoardOutputter.createBoardOutput(God.livingCellsOnNextTurn(liveCellsGraph)));
      toVerify.append("\n");
    });

    Approvals.verify(toVerify);
  }

  private List<Cell> createRegularlySpacedCentres(int numberOfCentres) {
    List<Cell> centres = new ArrayList<>();
    int spacer = 7;

    for (int i = 0; i < numberOfCentres; i++) {
      centres.add(new Cell(spacer*i, 0));
    }

    return centres;
  }
}