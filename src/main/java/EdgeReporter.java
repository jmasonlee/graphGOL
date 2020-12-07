public interface EdgeReporter<T extends Comparable> {
  public T disconnectedDistance();

  public T distanceBetween(int nodeIndex1, int nodeIndex2);

  public void addNode();

  public void createEdge(int nodeIndex1, int nodeIndex2, T distance);

  public void addNodes(int numberToAdd);

  public void deleteNode(int nodeIndex);

  public int size();

  public void deleteEdge(int nodeIndex1, int nodeIndex2);

  public void changeEdge(int nodeIndex1, int nodeIndex2, T newDistance);
}
