import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphTest {
  @Test
  public void testCreateEmptyGraph() {
    Graph<Integer> graph = new Graph<>();
    assertEquals(0, graph.size());
  }
  
  // Graph should be able to add a bunch of nodes all at once
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
}
