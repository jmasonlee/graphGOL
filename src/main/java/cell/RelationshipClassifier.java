package cell;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class RelationshipClassifier {
  public static Map<Relationships, List<Cell>> classify(Cell cell, List<Cell> otherCells) {
    Map<Relationships, List<Cell>> relationshipsMap =
        otherCells
            .stream()
            .collect(
                groupingBy(
                    otherCell -> {
                      Double distanceBetweenCells =
                          calculateEuclideanDistanceBetweenCells(cell, (Cell) otherCell);
                      if (distanceBetweenCells == Relationships.SELF.maxDistance) {
                        return Relationships.SELF;
                      } else if (distanceBetweenCells <= Relationships.NEIGHBOUR.maxDistance) {
                        return Relationships.NEIGHBOUR;
                      } else if (distanceBetweenCells <= Relationships.COPARENT.maxDistance) {
                        return Relationships.COPARENT;
                      } else {
                        return Relationships.DISCONNECTED;
                      }
                    }));

    return relationshipsMap;
  }

  private static Double calculateEuclideanDistanceBetweenCells(Cell cell1, Cell cell2) {
    Integer xDistance = Math.abs(cell1.x - cell2.x);
    Integer yDistance = Math.abs(cell1.y - cell2.y);

    return Math.hypot(xDistance, yDistance);
  }

  public enum Relationships {
    DISCONNECTED(-1),
    SELF(0),
    NEIGHBOUR(Math.sqrt(2)),
    COPARENT(Math.sqrt(8));

    public final double maxDistance;

    private Relationships(double maxDistance) {
      this.maxDistance = maxDistance;
    }
  }
}
