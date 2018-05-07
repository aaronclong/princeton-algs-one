import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE = 1.96;
    private final int dimensions;
    private final int numberOfTrials;
    private double[] results;

    public PercolationStats(int n, int trials) {
        if (0 >= n || 0 >= trials) {
            throw new IllegalArgumentException();
        }

        dimensions = n;
        numberOfTrials = trials;
        results = new double[numberOfTrials];

        for (int i = 0; i < numberOfTrials; i++) {
            results[i] = runSimulation();
        }
    }

    private double runSimulation() {
        Percolation percolation = new Percolation(numberOfTrials);

        while (!percolation.percolates()) {
            randomlyOpenCell(percolation, dimensions);
        }

        return (double) percolation.numberOfOpenSites() / (dimensions * dimensions);
    }

    private static boolean randomlyOpenCell(Percolation union, int dimensions) {
        int row = StdRandom.uniform(dimensions);
        int col = StdRandom.uniform(dimensions);
        if (!union.isOpen(row, col)) {
            union.open(row, col);
            return true;
        }
        return false;
    }

    public double mean() {
        return StdStats.mean(results);
    }
    public double stddev() {
        return StdStats.stddev(results);
    }                        // sample standard deviation of percolation threshold
    public double confidenceLo() {
        return mean() - ((CONFIDENCE * stddev()) / Math.sqrt(numberOfTrials));
    }

    public double confidenceHi() {
        return mean() + ((CONFIDENCE * stddev()) / Math.sqrt(numberOfTrials));
    }

    public static void main(String[] args) {
        int dimensions = Integer.parseInt(args[0]);
        int intervals = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(dimensions, intervals);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
