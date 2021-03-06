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

  public Coordinates(BoardBounds boardBounds) {
    this.xCoordinates = createXCoordinates(boardBounds.getLeftmostXValue(), boardBounds.getWidth());
    this.yCoordinates = createYCoordinates(boardBounds.getUpperYValue(), boardBounds.getHeight());
    this.widestCoordinateString = getLargestNumberOfCharsInCoordinate();
  }

  private static List<String> createXCoordinates(int leftmostXValue, int width) {
    int endAt = leftmostXValue + width -1;
    return createCoordinates(leftmostXValue, endAt);
  }

  private static List<String> createYCoordinates(int upperYValue, int dimensionSize) {
    int startAt = upperYValue - dimensionSize + 1;

    List<String> coordinateNumbers = createCoordinates(startAt, upperYValue);

    Collections.reverse(coordinateNumbers);

    return coordinateNumbers;
  }

  private static List<String> createCoordinates(int startAt, int endAt) {
    return IntStream.rangeClosed(startAt, endAt).boxed()
      .map(Object::toString)
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
      "\n xCoordinates=" + xCoordinates +
      ",\n yCoordinates=" + yCoordinates +
      ",\n widestCoordinateString=" + widestCoordinateString +
      "\n}";
  }
}
