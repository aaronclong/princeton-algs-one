import org.junit.Assert;
import org.junit.Test;

public class PercolationTest {
    @Test(expected = IllegalArgumentException.class)
    public void zeroOrLessThrowsException() {
        new Percolation(0);
        new Percolation(-1);
    }

    @Test
    public void columnRowOutOfRangeNotOpen() {
        Percolation percolation = new Percolation(5);
        percolation.open(6, 6);
        boolean result = percolation.isOpen(6, 6);
        Assert.assertFalse("Assert that out of range index is closed", result);
    }

    @Test
    public void assertNumberOfOpenCells() {
        Percolation percolation = new Percolation(5);
        percolation.open(1, 2);
        percolation.open(0, 3);
        percolation.open(2, 1);
        Assert.assertEquals("Assert number of open cells", 3, percolation.numberOfOpenSites());
    }
}
