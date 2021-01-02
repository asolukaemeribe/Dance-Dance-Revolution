import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DDRTest {

    @Test
    public void testReset() {
        DDR ddr = new DDR();
        String s = ddr.getOrdersString();

        ddr.reset();
        String s2 = ddr.getOrdersString();

        assertFalse(s.equals(s2));
    }

    @Test
    public void testResetEntireGame() {
        DDR ddr = new DDR();
        for (int row = 0; row < ddr.getGrid().length; row++) {
            for (int col = 0; col < ddr.getGrid()[row].length; col++) {
                ddr.getGrid()[row][col] = null;
            }
        }

        ddr.awardPoints();

        int i = ddr.getScore();

        ddr.resetEntireGame();

        int i2 = ddr.getScore();

        assertFalse(i == i2);
    }

    @Test
    public void testAwardPointsFalse() {
        DDR ddr = new DDR();
        assertFalse(ddr.awardPoints());
    }

    @Test
    public void testClickGridOnNonNullElement() {
        DDR ddr = new DDR();
        int fRow = 0;
        int fCol = 0;
        for (int row = 0; row < ddr.getGrid().length; row++) {
            for (int col = 0; col < ddr.getGrid()[row].length; col++) {
                if (ddr.getGrid()[row][col].equals(ddr.getOrders().getFirst())) {
                    ddr.clickGrid(row, col);
                    fRow = row;
                    fCol = col;
                }
            }
        }
        assertEquals(ddr.getGrid()[fRow][fCol], null);
    }

    @Test
    public void testClickGridOnNullElement() {
        DDR ddr = new DDR();
        ddr.getGrid()[0][0] = null;
        ddr.clickGrid(0, 0);
        assertEquals(ddr.getGrid()[0][0], null);
    }

    @Test
    public void testGetArrowObject() {
        DDR ddr = new DDR();
        ddr.getGrid()[0][0] = ArrowDirection.LEFTARROW;
        assertTrue(ddr.getArrowObject(ddr.getGrid()[0][0]) instanceof GameObj);
    }

}