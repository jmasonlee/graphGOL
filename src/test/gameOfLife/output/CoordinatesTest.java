package gameOfLife.output;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinatesTest {

  @Test
  public void testWillCreateCoordinateObject() {
    List<String> xCoordinates =
        IntStream.rangeClosed(-106, -95).boxed().map(i -> i.toString()).collect(Collectors.toList());
    List<String> yCoordinates =
        IntStream.rangeClosed(12, 22).boxed().map(i -> i.toString()).collect(Collectors.toList());

    Approvals.verify(new Coordinates(xCoordinates, yCoordinates).toString());
  }
}
