public enum CellRelationships {
    DISCONNECTED(-1), SELF(0), NEIGHBOUR(1), COPARENT(2);

    public final int maxDistance;

    private CellRelationships(int maxDistance){
        this.maxDistance = maxDistance;
    }
}
