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
    this.edgeReporter.addNode();
  }

  public List<N> getNodes() {
    return nodes;
  }

  public void addNodes(List<N> nodes) {
    this.nodes.addAll(nodes);
    this.edgeReporter.addNodes(nodes.size());
  }

  public E getDisconnectedDistance() {
    return edgeReporter.disconnectedDistance();
  }

  public E getEdge(N node1, N node2) {
    return edgeReporter.distanceBetween(nodes.indexOf(node1), nodes.indexOf(node2));
  }

  public void deleteNode(N node) {
    int nodeIndex = nodes.indexOf(node);
    edgeReporter.deleteNode(nodeIndex);
    nodes.remove(nodeIndex);
  }

  public void deleteListOfNodes(ArrayList<N> nodesToDelete) {
    nodesToDelete.forEach(n -> deleteNode(n));
  }
}
