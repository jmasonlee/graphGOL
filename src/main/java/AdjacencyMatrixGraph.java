import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AdjacencyMatrixGraph {
    private ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

    public AdjacencyMatrixGraph(){}

    public Integer distanceBetween(Integer node1, Integer node2) {
        return matrix.get(node1).get(node2);
    }

    public void addNode(Integer node1) {
        ArrayList<Integer> edges = new ArrayList<Integer>(Collections.nCopies(matrix.size(), -1));
        matrix.add(edges);
        matrix.stream().forEach(n -> n.add( -1));
    }

    public void createEdge(Integer node1, Integer node2, Integer distance) {
        matrix.get(node1).set(node2, distance);
    }

    public void addNodes(Integer[] nodes) {
        Arrays.stream(nodes).forEach(n -> addNode(n));
    }

//    public void deleteNode(Integer node) {
//        matrix.remove(node);
//        matrix.stream().forEach(n -> n.remove(node));
//    }

    public int size(){
        return matrix.size();
    }
}
