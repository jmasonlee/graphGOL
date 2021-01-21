package gameOfLife.output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Coordinates {
  List<String> xCoordinates;
  List<String> yCoordinates;
  Integer widestCoordinateString;

  public Coordinates(List<String> xCoordinates, List<String> yCoordinates) {
    this.xCoordinates = xCoordinates;
    this.yCoordinates = yCoordinates;
    this.widestCoordinateString = getLargestNumberOfCharsInCoordinate();
  }

  private Integer getLargestNumberOfCharsInCoordinate() {
    List<String> allCoordinateStrings = new ArrayList<>(xCoordinates);
    allCoordinateStrings.addAll(yCoordinates);
    return Collections.max(allCoordinateStrings, Comparator.comparing(String::length)).length();
  }
}
