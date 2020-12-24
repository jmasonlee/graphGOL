package gameOfLife.cell;

public enum Relationships {
  DISCONNECTED(-1),
  SELF(0),
  NEIGHBOUR(Math.sqrt(2)),
  COPARENT(Math.sqrt(8));

  public final double maxDistance;

  Relationships(double maxDistance) {
    this.maxDistance = maxDistance;
  }
}
