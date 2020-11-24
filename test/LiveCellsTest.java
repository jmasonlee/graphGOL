import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LiveCellsTest {
    @Test
    public void testEmptyGraphCreation(){
        assertEquals(new LiveCells().count(), 0);
    }
}
