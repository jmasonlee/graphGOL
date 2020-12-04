import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdjacencyMatrixGraphTest {
  Integer node1 = 0;
  Integer node2 = 1;
  Integer node3 = 2;

  Integer distance = 3;
  Integer disconnectedDistance = -1;

  @Test
  public void testDistanceBetweenTwoNodes() {
    AdjacencyMatrixGraph graph = buildTestGraph();
    assertEquals(distance, graph.distanceBetween(node1, node2));
  }

  @Test
  public void testDistanceBetweenTwoDisconnectedNodes() {
    AdjacencyMatrixGraph graph = buildTestGraph();
    assertEquals(disconnectedDistance, graph.distanceBetween(node2, node3));
  }

  @Test
  public void testDeleteDisconnectedNode() {
    AdjacencyMatrixGraph graph = buildTestGraph();
    assertEquals(3, graph.size());
    graph.deleteNode(node3);
    assertEquals(distance, graph.distanceBetween(node1, node2));
    assertEquals(2, graph.size());
  }

  @Test
  public void testDeleteConnectedNode() {
    AdjacencyMatrixGraph graph = buildTestGraph();
    assertEquals(3, graph.size());
    graph.deleteNode(node2);
    assertEquals(disconnectedDistance, graph.distanceBetween(node1, node2));
    assertEquals(2, graph.size());
  }

  @Test
  public void testRemoveEdge() {
    AdjacencyMatrixGraph graph = buildTestGraph();
    graph.deleteEdge(node1,node2);
    assertEquals(disconnectedDistance, graph.distanceBetween(node1, node2));
  }

  @Test
  public void testUpdateEdge() {
      Integer newDistance = 7;
      AdjacencyMatrixGraph graph = buildTestGraph();
      graph.changeEdge(node1, node2, newDistance);
      assertEquals(newDistance, graph.distanceBetween(node1, node2));
  }

    private AdjacencyMatrixGraph buildTestGraph() {
    Integer[] nodes = new Integer[] {node1, node2, node3};
    AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
    graph.addNodes(nodes);
    graph.createEdge(node1, node2, distance);
    return graph;
  }
}
