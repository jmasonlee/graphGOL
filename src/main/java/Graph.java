import java.util.ArrayList;

public class Graph<T extends Comparable> {
  ArrayList<T> nodes = new ArrayList<>();

  public int size() {
    return nodes.size();
  }

  public void addNode(T node) {
      nodes.add(node);
  }

  public ArrayList<T> getNodes() {
    return nodes;
  }
}
