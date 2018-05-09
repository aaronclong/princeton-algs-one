import org.junit.Assert;
import org.junit.Test;

public class PercolationTest {
    @Test(expected = IllegalArgumentException.class)
    public void zeroOrLessThrowsException() {
        new Percolation(0);
        new Percolation(-1);
    }

    @Test(expected = IllegalArgumentException.class)
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
        percolation.open(3, 3);
        percolation.open(2, 1);
        Assert.assertEquals("Assert number of open cells", 3, percolation.numberOfOpenSites());
    }

    @Test
    public void assertIsFullPositive() {
        Percolation percolation = new Percolation(20);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        percolation.open(5, 1);
        Assert.assertEquals("Assert number of open cells", 5, percolation.numberOfOpenSites());
        Assert.assertTrue("Assert cell is full", percolation.isFull(5, 1));
        Assert.assertTrue("Assert cell is full", percolation.isFull(4, 1));
        Assert.assertTrue("Assert cell is full", percolation.isFull(3, 1));
        Assert.assertTrue("Assert cell is full", percolation.isFull(2, 1));
        Assert.assertTrue("Assert cell is full", percolation.isFull(1, 1));
    }

    @Test
    public void assertIsFullNegative() {
        Percolation percolation = new Percolation(20);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        percolation.open(5, 1);
        Assert.assertEquals("Assert number of open cells", 5, percolation.numberOfOpenSites());
        Assert.assertFalse("Assert cell is full", percolation.isFull(4, 17));
    }

    @Test
    public void assertPercolates() {
        Percolation percolation = new Percolation(5);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        percolation.open(5, 1);
        Assert.assertEquals("Assert number of open cells", 5, percolation.numberOfOpenSites());
        Assert.assertTrue("Assert the percolator", percolation.percolates());
    }
}
