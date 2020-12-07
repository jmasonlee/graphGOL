import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdjacencyMatrix {
    private ArrayList<ArrayList<Double>> matrix = new ArrayList<>();

    public static final Double DISCONNECTED_DISTANCE = -1.00;

    public AdjacencyMatrix(){}

    public Double distanceBetween(int nodeIndex1, int nodeIndex2) {
        return matrix.get(nodeIndex1).get(nodeIndex2);
    }

    public void addNode() {
        ArrayList<Double> edges = new ArrayList<Double>(Collections.nCopies(matrix.size(), DISCONNECTED_DISTANCE));
        matrix.add(edges);
        matrix.forEach(n -> n.add(DISCONNECTED_DISTANCE));
    }

    public void createEdge(int nodeIndex1, int nodeIndex2, Double distance) {
        matrix.get(nodeIndex1).set(nodeIndex2, distance);
    }

    public void addNodes(int numberToAdd) {
       for(int i = 0; i< numberToAdd; i++){
           addNode();
       }
    }

    public void deleteNode(int nodeIndex) {
        matrix.remove(nodeIndex);
        matrix.forEach(n -> n.remove(nodeIndex));
    }

    public int size(){
        return matrix.size();
    }

    public void deleteEdge(int nodeIndex1, int nodeIndex2) {
        changeEdge(nodeIndex1, nodeIndex2, DISCONNECTED_DISTANCE);
    }

    public void changeEdge(int nodeIndex1, int nodeIndex2, Double newDistance) {
        matrix.get(nodeIndex1).set(nodeIndex2, newDistance);
        matrix.get(nodeIndex2).set(nodeIndex1, newDistance);
    }

    public List<Integer> findNeighboursThatAreWithinAMaxDistance(Integer nodeIndex1, Double maxDistance) {
        List<Integer> indexes = new ArrayList<>();
        ArrayList<Double> distances = matrix.get(nodeIndex1) ;

        for( int i = 0; i < distances.size(); i++){
            Double distance = distances.get(i);
            if(distanceIsWithinMax(maxDistance, distance)){
                indexes.add(i);
            }
        }

        return indexes;
    }

    private boolean distanceIsWithinMax(Double maxDistance, Double distance) {
        return distance != DISCONNECTED_DISTANCE && distance <= maxDistance;
    }
}
