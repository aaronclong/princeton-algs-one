public class Percolation {

    private final int gridDimension;
    private final byte[][] grid;
    private int numberOfSites;

    public Percolation(int n) {
        if (0 >= n) {
            throw new IllegalArgumentException();
        }
        gridDimension = n;
        grid = new byte[n][n];
        numberOfSites = 0;
    }

    public void open(int row, int col) {
        if (isInRange(row, col)) {
            grid[row][col] = 1;
            numberOfSites++;
        }
    }

    public boolean isOpen(int row, int col) {
        if (isInRange(row, col)) {
            return grid[row][col] == 1;
        }
        return false;
    }

    public boolean isFull(int row, int col) { return true; }  // is site (row, col) full?

    public int numberOfOpenSites()  {
        return numberOfSites;
    }

    public boolean percolates() { return true; }             // does the system percolate?

    private boolean isInRange(int row, int column) {
        return gridDimension > row || column < gridDimension;
    }

    public static void main(String[] args){}   // test client (optional)
}