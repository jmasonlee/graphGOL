package gameOfLife.output;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Coordinates {
  List<String> coordinates;
  Integer widestCoordinateString;

  public Coordinates(List<String> coordinates) {
    this.coordinates = coordinates;
    this.widestCoordinateString = getLargestNumberOfCharsInCoordinate();
  }

  private Integer getLargestNumberOfCharsInCoordinate() {
    Integer widest = Collections.max(coordinates, Comparator.comparing(String::length)).length();

    return widest;
  }
}
