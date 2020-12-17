package cell;

public class RelationshipClassifier {
    public static Relationships classify(Cell cell, Cell cell1) {
        return Relationships.SELF;
    }

    public enum Relationships {
        DISCONNECTED(-1), SELF(0), NEIGHBOUR(1), COPARENT(2);

        public final int maxDistance;

        private Relationships(int maxDistance){
            this.maxDistance = maxDistance;
        }
    }


}
