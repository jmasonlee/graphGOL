package gameOfLife.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GraphTest {
  private final Double disconnectedDistance = -1.00;
  private Graph<Integer, Double> graph;
  private List<Integer> nodes = new ArrayList<>();

  @Before
  public void init() {
    graph = new Graph<>(new AdjacencyMatrix(new Double(-1.00)));
  }

  @Test
  public void testCreateEmptyGraphUsesDisconnectedValueFromEdgeReporter() {
    assertEquals(0, graph.size());
    assertEquals(disconnectedDistance, graph.getDisconnectedDistance());
  }

  @Test
  public void testAddNode() throws DuplicateNodeException {
    graph.addNode(10);
    assertEquals(1, graph.size());
    assertEquals(new Integer(10), graph.getNodes().get(0));
  }

  @Test
  public void testAddNodeList() {
    populateGraphWithManyNodes();

    assertEquals(nodes.size(), graph.size());
    assertEquals(nodes, graph.getNodes());
  }

  @Test
  public void testGraphReportsDisconnectedLengthBetweenTwoUnrelatedNodes() {
    populateGraphWithManyNodes();

    assertEquals(graph.getDisconnectedDistance(), graph.getEdge(20, 9));
  }

  @Test
  public void testDeleteANode() {
    populateGraphWithManyNodes();
    graph.deleteNode(2);

    assertEquals(nodes.size() - 1, graph.size());
    assertFalse(graph.getNodes().contains(2));
  }

  @Test
  public void testDeleteManyNodes() {
    populateGraphWithManyNodes();
    ArrayList<Integer> nodesToDelete = new ArrayList<>();
    nodesToDelete.addAll(Arrays.asList(new Integer[] {20, 9, 2, 1}));
    graph.deleteListOfNodes(nodesToDelete);

    assertEquals(1, graph.size());
    nodesToDelete.forEach(n -> assertFalse(graph.getNodes().contains(n)));
  }

  @Test
  public void testSetEdge() {
    populateGraphWithManyNodes();
    Double edgeDistance = 2.00;
    graph.setEdge(20, 9, edgeDistance);

    assertEquals(edgeDistance, graph.getEdge(20, 9));
  }

  @Test(expected = DuplicateNodeException.class)
  public void testCannotAddDuplicateNodes() throws DuplicateNodeException {
    populateGraphWithManyNodes();
    graph.addNode(0);
  }

  @Test
  public void testFindAllNodesWithSpecifiedDistance() {
    populateGraphWithManyNodes();
    Double edgeDistance = 2.00;
    Integer node1 = 20;
    Integer node2 = 9;

    graph.setEdge(node1, node2, edgeDistance);

    assertEquals(node1, graph.findAllNodesWithSpecifiedDistance(node2, edgeDistance).get(0));
  }

  private void populateGraphWithManyNodes() {
    nodes.addAll(Arrays.asList(new Integer[] {20, 9, 0, 2, 1}));

    graph.addNodes(nodes);
  }
}
