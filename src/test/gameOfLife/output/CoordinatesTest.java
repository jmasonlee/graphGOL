package gameOfLife.output;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CoordinatesTest {

  @Test
  public void testWillCalculateTheNumberOfCharsInTheLongestCoordinateValue() {
    List<String> xCoordinates = Arrays.asList(new String[]{"1", "7", "-5674027"});
    List<String> yCoordinates = Arrays.asList(new String[]{"-1", "7", "-5"});
    int maxCharWidth = new Coordinates(xCoordinates, yCoordinates).widestCoordinateString;
    assertEquals(8, maxCharWidth);
  }
}
