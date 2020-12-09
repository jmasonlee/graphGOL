public class Cell implements Comparable {
    public Integer y;
    public Integer x;

    public Cell(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
