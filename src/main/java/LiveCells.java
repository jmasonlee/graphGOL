public class LiveCells {
  private Graph<int[], DefaultEdge> liveCells;

  public LiveCells() {
    this.liveCells = new DefaultUndirectedGraph<int[], DefaultEdge>(DefaultEdge.class);
  }

  public int count() {
    return 0;
  }
}
