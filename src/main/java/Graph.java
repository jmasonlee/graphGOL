import java.util.ArrayList;
import java.util.List;

public class Graph<N extends Comparable, E extends Comparable> {
  private final EdgeReporter<E> edgeReporter;
  List<N> nodes = new ArrayList<>();

  public Graph(EdgeReporter<E> edgeReporter) {
    this.edgeReporter = edgeReporter;
  }

  public int size() {
    return nodes.size();
  }

  public void addNode(N node) {
    nodes.add(node);
  }

  public List<N> getNodes() {
    return nodes;
  }

  public void addNodes(List<N> nodes) {
    this.nodes.addAll(nodes);
  }

  public E getDisconnectedDistance() {
    return edgeReporter.disconnectedDistance();
  }
}
