import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
            grid[row - 1][col - 1] = 1;
            numberOfSites++;
        }
    }

    public boolean isOpen(int row, int col) {
        if (isInRange(row, col)) {
            return grid[row - 1][col - 1] == 1;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        if (isInRange(row, col)) {
            List<Cell> queue = new ArrayList<>();
            //Set<Cell> visited = new HashSet<>();
            queue.addAll(getSurroundingCells(row, col));

            while(queue.size() != 0) {
                Cell cell = queue.remove(0);
                if (isRoof(cell)) {
                    return true;
                }
                queue.addAll(getSurroundingCells(cell.getRow(), cell.getCol()));
            }
        }
        return false;
    }

    private List<Cell> getSurroundingCells(int row, int col) {
        List<Cell> surroundingCells = new ArrayList<>();
        if (isOpen(row + 1, col)) {
            Cell cell = new Cell(row + 1, col);
            surroundingCells.add(cell);
        }

        if (isOpen(row - 1, col)) {
            Cell cell = new Cell(row - 1, col);
            surroundingCells.add(cell);
        }

        if (isOpen(row, col + 1)) {
            Cell cell = new Cell(row, col + 1);
            surroundingCells.add(cell);
        }

        if (isOpen(row, col - 1)) {
            Cell cell = new Cell(row, col - 1);
            surroundingCells.add(cell);
        }

        return surroundingCells;
    }

    private static boolean isRoof(Cell cell) {
        return cell.getRow() == 1;
    }

    private static class Cell {
        private final int row;
        private final int col;

        public Cell(int row, int col) {
            this.row  = row;
            this.col = col;
        }

        public int getRow() { return row; }
        public int getCol() { return col; }
    }

    public int numberOfOpenSites()  {
        return numberOfSites;
    }

    public boolean percolates() {
        return true;
    }

    private boolean isInRange(int row, int column) {
        return ((row > 0 && column > 0 ) && (gridDimension >= row && column <= gridDimension));
    }

    public static void main(String[] args){}   // test client (optional)
}