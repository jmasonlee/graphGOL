package gameOfLife.graph;

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

  public void addNode(N node) throws DuplicateNodeException {
    if (nodes.contains(node)) {
      throw new DuplicateNodeException();
    }
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

  public void setEdge(N node1, N node2, E edgeDistance) {
    int node1index = nodes.indexOf(node1);
    int node2index = nodes.indexOf(node2);

    edgeReporter.changeEdge(node1index, node2index, edgeDistance);
  }

  public List<N> findAllNodesWithSpecifiedDistance(N targetNode, E edgeDistance) {
    List<Integer> indicesOfNodes =
        edgeReporter.getIndexOfNodesWithEdgesMatchingDistance(
            nodes.indexOf(targetNode), edgeDistance);
    List<N> matchingNodes = new ArrayList<>();

    indicesOfNodes.forEach(i -> matchingNodes.add(nodes.get(i)));

    return matchingNodes;
  }
}
