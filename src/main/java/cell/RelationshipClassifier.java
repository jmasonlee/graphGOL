package cell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelationshipClassifier {
  public static Map<Relationships, List<Cell>> classify(Cell cell, Cell otherCell) {
    Map<Relationships, List<Cell>> relationshipsMap = new HashMap<>();
    relationshipsMap.put(Relationships.SELF, Arrays.asList(new Cell[]{otherCell}));
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
