package gameOfLife.output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coordinates {
  List<String> xCoordinates;
  List<String> yCoordinates;
  int widestCoordinateString;

  public Coordinates(List<String> xCoordinates, List<String> yCoordinates) {
    this.xCoordinates = xCoordinates;
    this.yCoordinates = yCoordinates;
    this.widestCoordinateString = getLargestNumberOfCharsInCoordinate();
  }

  static List<String> createXCoordinates(int leftmostXValue, int width) {
    int endAt = leftmostXValue + width -1;
    return createCoordinates(leftmostXValue, endAt);
  }

  static List<String> createYCoordinates(int upperYValue, int dimensionSize) {
    int startAt = upperYValue - dimensionSize + 1;

    List<String> coordinateNumbers = createCoordinates(startAt, upperYValue);

    Collections.reverse(coordinateNumbers);

    return coordinateNumbers;
  }

  private static List<String> createCoordinates(int startAt, int endAt) {
    return IntStream.rangeClosed(startAt, endAt).boxed()
      .map(coord -> coord < 0 || 9 < coord ? coord.toString() : " " + coord.toString())
      .collect(Collectors.toList());
  }

  private int getLargestNumberOfCharsInCoordinate() {
    List<String> allCoordinateStrings = new ArrayList<>(xCoordinates);
    allCoordinateStrings.addAll(yCoordinates);
    return Collections.max(allCoordinateStrings, Comparator.comparing(String::length)).length();
  }

  @Override
  public String toString() {
    return "Coordinates{" +
      "xCoordinates=" + xCoordinates +
      ", yCoordinates=" + yCoordinates +
      ", widestCoordinateString=" + widestCoordinateString +
      '}';
  }
}
