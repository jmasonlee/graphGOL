package output;

import cell.Cell;
import org.apache.commons.lang.math.Range;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.*;

public class BoardOutputterTest {
  @Test
  public void testBoardIsGeneratedWithCellsInCorrectLocation() {
      List<Integer> coordinateNumbers = Arrays.asList(0, 1, 2, 3, 4);
      String[] allBoards = new String[25];


      for(int x = 0; x < coordinateNumbers.size(); x++){
          for(int y = 0; y < coordinateNumbers.size(); y++){
              int index = x*5 + y;
              allBoards[index] = createBoardWithCellInLocation(x,y);
          }
      }

      Approvals.verifyAll("",allBoards);
  }

  private String createBoardWithCellInLocation(Integer x, Integer y) {
    String input = String.format("(%d,%d):\n",x,y);
    return input + BoardOutputter.createBoardOutput(Arrays.asList(new Cell(x,y)));
  }
}
