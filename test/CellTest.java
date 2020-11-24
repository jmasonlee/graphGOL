import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CellTest {

    @Test
    public void testCellCreatedWithCorrectCoordinates(){
        int[] coordinates = {0,1};
        assertEquals(new Cell(coordinates).getCoordinates(),coordinates);
    }
}
