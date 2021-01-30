package gameOfLife.output;

public enum BoardSquare {
  TOP_BOUNDARY_END(" \n"),
  EMPTY_CELL("|__"),
  ROW_END("|\n"),
  SPACE(" "),
  LIVE_CELL("|X_");

  private String value;

  public String getValue() {
    return value;
  }

  BoardSquare(String value) {
    this.value = value;
  }
}
