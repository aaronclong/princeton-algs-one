import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int TOP;
    private final int BOTTOM;
    private final int gridDimension;
    private final boolean[][] grid;
    private final WeightedQuickUnionUF union;
    private final WeightedQuickUnionUF backwash;
    private int openSites;

    public Percolation(int n) {
        if (0 >= n) {
            throw new IllegalArgumentException();
        }
        gridDimension = n;
        grid = new boolean[n][n];
        openSites = 0;
        TOP = 1;
        BOTTOM = n * n + 1;
        union = new WeightedQuickUnionUF(n * n + 2);
        backwash = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int row, int col) {
        if (isInRange(row, col) && !isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            int currentCell = pointsToSingleInteger(row, col);

            if (row == 1) {
                union.union(TOP, currentCell);
                backwash.union(TOP, currentCell);
            }

            if (row == gridDimension) {
                union.union(currentCell, BOTTOM);
            }

            addSurroundingCells(row, col);

            openSites++;
        } else if (!isInRange(row, col)) throw new IllegalArgumentException();
    }

    public boolean isOpen(int row, int col) {
        if (!isInRange(row, col)) {
            throw new IllegalArgumentException();
        }
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            int cellAsSinglePoint = pointsToSingleInteger(row, col);
            return backwash.connected(TOP, cellAsSinglePoint);
        } else if (!isInRange(row, col)) throw new IllegalArgumentException();
        return false;
    }

    private void addSurroundingCells(int row, int col) {
        final int currentCell = pointsToSingleInteger(row, col);
        if (isInRange(row + 1, col) && isOpen(row + 1, col)) {
            int surroundingCell = pointsToSingleInteger(row + 1, col);
            union.union(currentCell, surroundingCell);
            backwash.union(currentCell, surroundingCell);
        }

        if (isInRange(row - 1, col) && isOpen(row - 1, col)) {
            int surroundingCell = pointsToSingleInteger(row - 1, col);
            union.union(currentCell, surroundingCell);
            backwash.union(currentCell, surroundingCell);
        }

        if (isInRange(row, col + 1) && isOpen(row, col + 1)) {
            int surroundingCell = pointsToSingleInteger(row, col + 1);
            union.union(currentCell, surroundingCell);
            backwash.union(currentCell, surroundingCell);
        }

        if (isInRange(row, col - 1) && isOpen(row, col - 1)) {
            int surroundingCell = pointsToSingleInteger(row, col - 1);
            union.union(currentCell, surroundingCell);
            backwash.union(currentCell, surroundingCell);
        }
    }

    public int numberOfOpenSites()  {
        return openSites;
    }

    public boolean percolates() {
        return union.connected(TOP,  BOTTOM);
    }

    private int pointsToSingleInteger(int x, int y) {
        return (gridDimension * (x -1)) + y;
    }

    private boolean isInRange(int row, int column) {
        return ((row > 0 && column > 0 ) && (gridDimension >= row && column <= gridDimension));
    }

}