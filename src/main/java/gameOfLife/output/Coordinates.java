package gameOfLife.output;

import java.util.List;

public class Coordinates {
  List<String> coordinates;
  Integer widestCoordinateString;

  public Coordinates(List<String> coordinates) {
    this.coordinates = coordinates;
    this.widestCoordinateString = getLargestNumberOfCharsInCoordinate();
  }

  private Integer getLargestNumberOfCharsInCoordinate() {
    Integer widest = coordinates.get(0).length();

    for(String coordinate : coordinates) {
      if (coordinate.length() > widest){
        widest = coordinate.length();
      }
    }

    return widest;
  }
}
