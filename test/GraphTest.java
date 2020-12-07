import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphTest {
  @Test
  public void testCreateEmptyGraph() {
    Graph<Integer> graph = new Graph<>();
    assertEquals(0, graph.size());
  }

  // Graph should be able to delete a node
  // Graph should be able to delete a list of nodes
  // Graph should be able to update an edge between two nodes
  // Graph should be able to return a list of nodes within a specified distance from a node
  @Test
  public void testAddNode() {
    Graph<Integer> graph = new Graph<>();
    graph.addNode(10);
    assertEquals(1, graph.size());
    assertEquals(new Integer(10), graph.getNodes().get(0));
  }

  @Test
  public void testAddNodeList() {
    List<Integer> nodes = new ArrayList<>();
    nodes.addAll(Arrays.asList(new Integer[] {20, 9, 0, 2, 1, 0}));

    Graph<Integer> graph = new Graph<>();
    graph.addNodes(nodes);

    assertEquals(nodes.size(), graph.size());
    assertEquals(nodes, graph.getNodes());
  }

}
