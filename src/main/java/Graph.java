import java.util.ArrayList;
import java.util.List;

public class Graph<T extends Comparable> {
  List<T> nodes = new ArrayList<>();

  public int size() {
    return nodes.size();
  }

  public void addNode(T node) {
    nodes.add(node);
  }

  public List<T> getNodes() {
    return nodes;
  }

  public void addNodes(List<T> nodes) {
    this.nodes.addAll(nodes);
  }
}
