package gameOfLife;

import gameOfLife.TestUtils.NeighbourGenerator;
import gameOfLife.cell.Cell;
import gameOfLife.output.BoardOutputter;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class GodTest {
  @Test
  public void testDoesNotReturnIsolatedCells() {
    LiveCellsGraph liveCellsGraph = new LiveCellsGraph(Arrays.asList(new Cell[]{new Cell(0, 1)}));
    assertEquals(new ArrayList<>(), God.livingCellsOnNextTurn(liveCellsGraph));
  }

  @Test
  public void returnsCellsWithEnoughNeighbours() {
    List<List<Cell>> cellsAndNeighbours = new ArrayList<>();

    List<Cell> centres = createRegularlySpacedCentres(9);

    for (int i = 0; i < centres.size(); i++) {
      List<Cell> allPossibleNeighbours = NeighbourGenerator.generateAllPossibleNeighboursForCell(centres.get(i));
      List<Cell> cellWithTwoNeighbours = NeighbourGenerator.allCombinationsOfNeighbours(allPossibleNeighbours, i).get(0);
      cellWithTwoNeighbours.add(centres.get(i));

      cellsAndNeighbours.add(cellWithTwoNeighbours);
    }

    gameOfLife.LiveCellsGraph liveCellsGraph = new gameOfLife.LiveCellsGraph(
        cellsAndNeighbours
            .stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList()));

    StringBuilder toVerify = new StringBuilder();
    toVerify.append(BoardOutputter.createBoardOutput(liveCellsGraph.getCells()));
    toVerify.append("\n\n");

    toVerify.append(BoardOutputter.createBoardOutput(God.livingCellsOnNextTurn(liveCellsGraph)));
    toVerify.append("\n");

    Approvals.verify(toVerify);
  }

  private List<Cell> createRegularlySpacedCentres(int numberOfCentres) {
    List<Cell> centres = new ArrayList<>();
    int spacer = 5;

    for (int i = 0; i < numberOfCentres; i++) {
      centres.add(new Cell(spacer * i, 0));
    }

    return centres;
  }
}