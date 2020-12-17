import cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.*;

public class BoardCreatorTest {
  @Test
  public void testBoardIsGeneratedWithCellsInCorrectLocation() {
      List<Integer> coordinateNumbers = Arrays.asList(0, 1, 2, 3, 4);
      String[] allBoards = new String[25];


      for(int x = 0; x < coordinateNumbers.size(); x++){
          for(int y = 0; y < coordinateNumbers.size(); y++){
              int index = x*5 + y;
              String input = String.format("(%d,%d):\n",x,y);
              allBoards[index] = input + BoardCreator.createBoardOutput(Arrays.asList(new Cell(x,y)));
          }
      }

      Approvals.verifyAll("",allBoards);
  }

}