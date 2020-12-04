import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AdjacencyMatrix {
    private ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

    public static final Integer DISCONNECTED = -1;

    public AdjacencyMatrix(){}

    public Integer distanceBetween(int nodeIndex1, int nodeIndex2) {
        return matrix.get(nodeIndex1).get(nodeIndex2);
    }

    public void addNode(int nodeIndex1) {
        ArrayList<Integer> edges = new ArrayList<Integer>(Collections.nCopies(matrix.size(), DISCONNECTED));
        matrix.add(edges);
        matrix.stream().forEach(n -> n.add( DISCONNECTED ));
    }

    public void createEdge(int nodeIndex1, int nodeIndex2, Integer distance) {
        matrix.get(nodeIndex1).set(nodeIndex2, distance);
    }

    public void addNodes(int[] nodeIndexes) {
        Arrays.stream(nodeIndexes).forEach(n -> addNode(n));
    }

    public void deleteNode(int nodeIndex) {
        matrix.remove(nodeIndex);
        matrix.stream().forEach(n -> n.remove(nodeIndex));
    }

    public int size(){
        return matrix.size();
    }

    public void deleteEdge(int nodeIndex1, int nodeIndex2) {
        changeEdge(nodeIndex1, nodeIndex2, DISCONNECTED);
    }

    public void changeEdge(int nodeIndex1, int nodeIndex2, Integer newDistance) {
        matrix.get(nodeIndex1).set(nodeIndex2, newDistance);
        matrix.get(nodeIndex2).set(nodeIndex1, newDistance);
    }
}
