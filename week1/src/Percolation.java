import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int gridDimension;
    private final boolean[][] grid;
    private final WeightedQuickUnionUF union;
    private int openSites;

    public Percolation(int n) {
        if (0 >= n) {
            throw new IllegalArgumentException();
        }
        gridDimension = n;
        grid = new boolean[n][n];
        openSites = 0;
        union = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int row, int col) {
        if (isInRange(row, col) && !isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            int currentCell = pointsToSingleInteger(row, col);

            if (row == 1) {
                union.union(0, currentCell);
            } else if (row == gridDimension) {
                union.union(gridDimension+2, currentCell);
            }

            addSurroundingCells(row, col);

            openSites++;
        }
    }

    public boolean isOpen(int row, int col) {
        return isInRange(row, col) && grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (isInRange(row, col)) {
            int cellAsSinglePoint = pointsToSingleInteger(row, col);
            return union.connected(0, cellAsSinglePoint);
        }
        return false;
    }

    private void addSurroundingCells(int row, int col) {
        final int currentCell = pointsToSingleInteger(row, col);
        if (isOpen(row + 1, col)) {
            int surroundingCell = pointsToSingleInteger(row + 1, col);
            union.union(currentCell, surroundingCell);
        }

        if (isOpen(row - 1, col)) {
            int surroundingCell = pointsToSingleInteger(row - 1, col);
            union.union(currentCell, surroundingCell);
        }

        if (isOpen(row, col + 1)) {
            int surroundingCell = pointsToSingleInteger(row, col + 1);
            union.union(currentCell, surroundingCell);
        }

        if (isOpen(row, col - 1)) {
            int surroundingCell = pointsToSingleInteger(row, col - 1);
            union.union(currentCell, surroundingCell);
        }
    }

    public int numberOfOpenSites()  {
        return openSites;
    }

    public boolean percolates() {
        return union.connected(0, gridDimension+2);
    }

    private int pointsToSingleInteger(int x, int y) {
        return ((x -1) * gridDimension) + y;
    }

    private boolean isInRange(int row, int column) {
        return ((row > 0 && column > 0 ) && (gridDimension >= row && column <= gridDimension));
    }

    public static void main(String[] args){}   // test client (optional)
}