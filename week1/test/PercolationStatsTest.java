import org.junit.Test;

public class PercolationStatsTest {
    @Test(expected = IllegalArgumentException.class)
    public void zeroOrLessThrowsException() {
        new PercolationStats(0, 3);
        new PercolationStats(-1, 3);
    }
}
