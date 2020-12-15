package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    matrix.get(nodeIndex2).set(nodeIndex1, distance);
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

  public List<Integer> getIndexOfNodesWithEdgesMatchingDistance(int nodeIndex, T distance) {
    List<Integer> matchingIndices = new ArrayList<>();

    for( int i = 0; i < matrix.size(); i++){
      if(matrix.get(nodeIndex).get(i).equals(distance)){
        matchingIndices.add(i);
      }
    }

    return matchingIndices;
  }

  public T disconnectedDistance() {
    return disconnectedDistance;
  }
}
