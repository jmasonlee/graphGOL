import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdjacencyMatrixTest {
  Integer nodeIndex1 = 0;
  Integer nodeIndex2 = 1;
  Integer nodeIndex3 = 2;

  Double distance = 3.00;
  Double disconnectedDistance = AdjacencyMatrix.DISCONNECTED_DISTANCE;

  @Test
  public void testDistanceBetweenTwoNodes() {
    AdjacencyMatrix graph = buildTestGraph();
    assertEquals(distance, graph.distanceBetween(nodeIndex1, nodeIndex2));
  }

  @Test
  public void testDistanceBetweenTwoDisconnectedNodes() {
    AdjacencyMatrix graph = buildTestGraph();
    assertEquals(disconnectedDistance, graph.distanceBetween(nodeIndex2, nodeIndex3));
  }

  @Test
  public void testDeleteDisconnectedNode() {
    AdjacencyMatrix graph = setupNodeDeletionTest(nodeIndex3);
    assertEquals(distance, graph.distanceBetween(nodeIndex1, nodeIndex2));
    assertEquals(2, graph.size());
  }

  @Test
  public void testDeleteConnectedNode() {
    AdjacencyMatrix graph = setupNodeDeletionTest(nodeIndex2);
    assertEquals(disconnectedDistance, graph.distanceBetween(nodeIndex1, nodeIndex2));
    assertEquals(2, graph.size());
  }

  @Test
  public void testRemoveEdge() {
    AdjacencyMatrix graph = buildTestGraph();
    graph.deleteEdge(nodeIndex1, nodeIndex2);
    assertEquals(disconnectedDistance, graph.distanceBetween(nodeIndex1, nodeIndex2));
  }

  @Test
  public void testUpdateEdge() {
    Double newDistance = 7.00;
    AdjacencyMatrix graph = buildTestGraph();
    graph.changeEdge(nodeIndex1, nodeIndex2, newDistance);
    assertEquals(newDistance, graph.distanceBetween(nodeIndex1, nodeIndex2));
  }

  @Test
  public void testNeighboursWithinAMaxDistance() {
    AdjacencyMatrix graph = buildTestGraph();
    graph.createEdge(nodeIndex1, nodeIndex3, 5.00);
    List<Integer> nodes = graph.findNeighboursThatAreWithinAMaxDistance(nodeIndex1, distance);
    assertEquals(1, nodes.size());
    assertEquals(nodeIndex2, nodes.get(0));
  }

  private AdjacencyMatrix buildTestGraph() {
    int nodes = 3;
    AdjacencyMatrix graph = new AdjacencyMatrix();
    graph.addNodes(nodes);
    graph.createEdge(nodeIndex1, nodeIndex2, distance);
    return graph;
  }

  private AdjacencyMatrix setupNodeDeletionTest(Integer nodeToDelete) {
    AdjacencyMatrix graph = buildTestGraph();
    assertEquals(3, graph.size());
    graph.deleteNode(nodeToDelete);
    return graph;
  }
}
