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

  // Graph should be able to delete a list of nodes
  // Graph should be able to update an edge between two nodes
  // Graph should omly allow unique nodes
  // Graph should be able to retrieve edge between nodes
  // Graph should be able to retrieve all edges between nodes

  @Test
  public void testAddNode() {
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

    assertEquals(5, graph.size());
    assertFalse(graph.getNodes().contains(2));
  }

  @Test
  public void testDeleteManyNodes() {
    populateGraphWithManyNodes();
    ArrayList<Integer> nodesToDelete = new ArrayList<>();
    nodesToDelete.addAll(Arrays.asList(new Integer[] {20, 9, 2, 1}));
    graph.deleteListOfNodes(nodesToDelete);

    assertEquals(2, graph.size());
    nodesToDelete.forEach(n -> assertFalse(graph.getNodes().contains(n)));
  }

  private void populateGraphWithManyNodes() {
    nodes.addAll(Arrays.asList(new Integer[] {20, 9, 0, 2, 1, 0}));

    graph.addNodes(nodes);
  }
}
