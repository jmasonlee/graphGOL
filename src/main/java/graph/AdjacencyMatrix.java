package graph;

import java.util.ArrayList;
import java.util.Collections;

public class AdjacencyMatrix<T extends Comparable> implements EdgeReporter<T> {
  private T disconnectedDistance;
  private ArrayList<ArrayList<T>> matrix = new ArrayList<>();

  public AdjacencyMatrix(T disconnectedDistance) {
    this.disconnectedDistance = disconnectedDistance;
  }

  public T distanceBetween(int nodeIndex1, int nodeIndex2) {
    return matrix.get(nodeIndex1).get(nodeIndex2);
  }

  public void addNode() {
    ArrayList<T> edges = new ArrayList<T>(Collections.nCopies(matrix.size(), disconnectedDistance));
    matrix.add(edges);
    matrix.forEach(n -> n.add(disconnectedDistance));
  }

  public void createEdge(int nodeIndex1, int nodeIndex2, T distance) {
    matrix.get(nodeIndex1).set(nodeIndex2, distance);
  }

  public void addNodes(int numberToAdd) {
    for (int i = 0; i < numberToAdd; i++) {
      addNode();
    }
  }

  public void deleteNode(int nodeIndex) {
    matrix.remove(nodeIndex);
    matrix.forEach(n -> n.remove(nodeIndex));
  }

  public int size() {
    return matrix.size();
  }

  public void deleteEdge(int nodeIndex1, int nodeIndex2) {
    changeEdge(nodeIndex1, nodeIndex2, disconnectedDistance);
  }

  public void changeEdge(int nodeIndex1, int nodeIndex2, T newDistance) {
    matrix.get(nodeIndex1).set(nodeIndex2, newDistance);
    matrix.get(nodeIndex2).set(nodeIndex1, newDistance);
  }

  public T disconnectedDistance() {
    return disconnectedDistance;
  }
}
