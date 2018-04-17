public class Percolation {
    public Percolation(int n) {}           // create n-by-n grid, with all sites blocked
    public    void open(int row, int col) {}    // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col) { return true; }  // is site (row, col) open?
    public boolean isFull(int row, int col){ return true; }  // is site (row, col) full?
    public     int numberOfOpenSites()  { return 1; }     // number of open sites
    public boolean percolates() { return true; }             // does the system percolate?
    public static void main(String[] args){}   // test client (optional)
}