import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdjacencyMatrixTest {
  Integer nodeIndex1 = 0;
  Integer nodeIndex2 = 1;
  Integer nodeIndex3 = 2;

  Integer distance = 3;
  Integer disconnectedDistance = AdjacencyMatrix.DISCONNECTED;

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
    Integer newDistance = 7;
    AdjacencyMatrix graph = buildTestGraph();
    graph.changeEdge(nodeIndex1, nodeIndex2, newDistance);
    assertEquals(newDistance, graph.distanceBetween(nodeIndex1, nodeIndex2));
  }

  private AdjacencyMatrix buildTestGraph() {
    int[] nodes = new int[] {nodeIndex1, nodeIndex2, nodeIndex3};
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
