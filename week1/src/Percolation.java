import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Percolation {
    private static final int TOP = 2;
    private static final int BOTTOM = 3;

    private final int gridDimension;
    private final byte[][] grid;
    private Map<Integer, Integer> opened;

    public Percolation(int n) {
        if (0 >= n) {
            throw new IllegalArgumentException();
        }
        gridDimension = n;
        grid = new byte[n][n];
        opened = new HashMap<>();
    }

    public void open(int row, int col) {
        if (isInRange(row, col)) {
            grid[row - 1][col - 1] = 1;
            opened.put(row, col);
        }
    }

    public boolean isOpen(int row, int col) {
        return isInRange(row, col) && grid[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) {
        if (isInRange(row, col)) {
            List<Cell> queue = new ArrayList<>();
            List<Cell> visited = new ArrayList<>();

            queue.addAll(getSurroundingCells(row, col));
            boolean isFull = false;
            while (queue.size() != 0) {
                Cell cell = queue.remove(0);
                if (isRoof(cell)) {
                    isFull = true;
                    visited.add(cell);
                    break;
                }
                queue.addAll(getSurroundingCells(cell.getRow(), cell.getCol()));
                visited.add(cell);
            }

            visited.addAll(queue);
            for (Cell cell : visited) {
                cell.resetVisitedCount(grid);
            }

            return isFull;
        }
        return false;
    }

    private List<Cell> getSurroundingCells(int row, int col) {
        List<Cell> surroundingCells = new ArrayList<>();
        if (isOpen(row + 1, col) && !hasBeenVisited(row + 1, col)) {
            Cell cell = new Cell(row + 1, col);
            surroundingCells.add(cell);
            cell.incrementVisitedCount(grid);
        }

        if (isOpen(row - 1, col) && !hasBeenVisited(row - 1, col)) {
            Cell cell = new Cell(row - 1, col);
            surroundingCells.add(cell);
            cell.incrementVisitedCount(grid);
        }

        if (isOpen(row, col + 1) && !hasBeenVisited(row, col + 1)) {
            Cell cell = new Cell(row, col + 1);
            surroundingCells.add(cell);
            cell.incrementVisitedCount(grid);
        }

        if (isOpen(row, col - 1) && !hasBeenVisited(row, col - 1)) {
            Cell cell = new Cell(row, col - 1);
            surroundingCells.add(cell);
            cell.incrementVisitedCount(grid);
        }

        return surroundingCells;
    }

    private boolean hasBeenVisited(int row, int col) {
        return grid[row - 1][col - 1] > 1;
    }

    private static boolean isRoof(Cell cell) {
        return cell.getRow() == 1;
    }

    private boolean isFloor(Cell cell) {
        return cell.getRow() == gridDimension;
    }

    private static class Cell {
        private final int row;
        private final int col;

        Cell(int row, int col) {
            this.row  = row;
            this.col = col;
        }

        int getRow() { return row; }
        int getCol() { return col; }

        void incrementVisitedCount(byte[][] grid) {
            if (grid[row - 1][col - 1] > 0) {
                grid[row - 1][col - 1] += 1;
            }
        }

        void resetVisitedCount(byte[][] grid) {
            if (grid[row - 1][col - 1] > 0) {
                grid[row - 1][col - 1] = 1;
            }
        }
    }

    public int numberOfOpenSites()  {
        return opened.size();
    }

    public boolean percolates() {
        List<Cell> queue = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : opened.entrySet()) {
            queue.add(new Cell(entry.getKey(), entry.getValue()));
            while (queue.size() != 0) {
                Cell cell = queue.remove(0);
                queue.addAll(getSurroundingCells(cell.getRow(), cell.getCol()));
            }
        }

        return false;
    }

    private boolean isInRange(int row, int column) {
        return ((row > 0 && column > 0 ) && (gridDimension >= row && column <= gridDimension));
    }

    public static void main(String[] args){}   // test client (optional)
}