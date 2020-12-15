package graph;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdjacencyMatrixTest {
  Integer nodeIndex1 = 0;
  Integer nodeIndex2 = 1;
  Integer nodeIndex3 = 2;

  Double distance = 3.00;

  @Test
  public void testDistanceBetweenTwoNodes() {
    AdjacencyMatrix<Double> graph = buildTestGraph();
    assertEquals(distance, graph.distanceBetween(nodeIndex1, nodeIndex2));
    assertEquals(distance, graph.distanceBetween(nodeIndex2, nodeIndex1));
  }

  @Test
  public void testDistanceBetweenTwoDisconnectedNodes() {
    AdjacencyMatrix<Double> graph = buildTestGraph();
    assertEquals(graph.disconnectedDistance(), graph.distanceBetween(nodeIndex2, nodeIndex3));
  }

  @Test
  public void testDeleteDisconnectedNode() {
    AdjacencyMatrix<Double> graph = setupNodeDeletionTest(nodeIndex3);
    assertEquals(distance, graph.distanceBetween(nodeIndex1, nodeIndex2));
    assertEquals(2, graph.size());
  }

  @Test
  public void testDeleteConnectedNode() {
    AdjacencyMatrix<Double> graph = setupNodeDeletionTest(nodeIndex2);
    assertEquals(graph.disconnectedDistance(), graph.distanceBetween(nodeIndex1, nodeIndex2));
    assertEquals(2, graph.size());
  }

  @Test
  public void testRemoveEdge() {
    AdjacencyMatrix<Double> graph = buildTestGraph();
    graph.deleteEdge(nodeIndex1, nodeIndex2);
    assertEquals(graph.disconnectedDistance(), graph.distanceBetween(nodeIndex1, nodeIndex2));
  }

  @Test
  public void testUpdateEdge() {
    Double newDistance = 7.00;
    AdjacencyMatrix<Double> graph = buildTestGraph();
    graph.changeEdge(nodeIndex1, nodeIndex2, newDistance);
    assertEquals(newDistance, graph.distanceBetween(nodeIndex1, nodeIndex2));
  }

  @Test
  public void testFetchIndexOfNodesWithEdgeLengthsEqualToDistance() {
    AdjacencyMatrix<Double> graph = buildTestGraph();
    List<Integer> nodeIndicesAtDistance =
        graph.getIndexOfNodesWithEdgesMatchingDistance(nodeIndex2, distance);
    assertEquals(1, nodeIndicesAtDistance.size());
    assertEquals(nodeIndex1, nodeIndicesAtDistance.get(0));
  }

  private AdjacencyMatrix buildTestGraph() {
    int nodes = 3;
    Double disconnectedDistance = -1.00;
    AdjacencyMatrix<Double> graph = new AdjacencyMatrix(disconnectedDistance);
    graph.addNodes(nodes);
    graph.createEdge(nodeIndex1, nodeIndex2, distance);
    return graph;
  }

  private AdjacencyMatrix setupNodeDeletionTest(Integer nodeToDelete) {
    AdjacencyMatrix<Double> graph = buildTestGraph();
    assertEquals(3, graph.size());
    graph.deleteNode(nodeToDelete);
    return graph;
  }
}
