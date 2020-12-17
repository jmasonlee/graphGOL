package cell;

import java.util.HashMap;
import java.util.Map;

public class RelationshipClassifier {
  public static Map<Relationships, Cell> classify(Cell cell, Cell otherCell) {
    Map<Relationships, Cell> relationshipsMap = new HashMap<Relationships, Cell>();
    relationshipsMap.put(Relationships.SELF, otherCell);
    return relationshipsMap;
  }

  public enum Relationships {
    DISCONNECTED(-1),
    SELF(0),
    NEIGHBOUR(1),
    COPARENT(2);

    public final int maxDistance;

    private Relationships(int maxDistance) {
      this.maxDistance = maxDistance;
    }
  }
}
