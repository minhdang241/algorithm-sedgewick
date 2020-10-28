import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final Site[][] grid;
  private final WeightedQuickUnionUF uf;
  private int numOpenSites;
  private boolean isPercolated;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) throw new IllegalArgumentException("n should be greater than zero");
    uf = new WeightedQuickUnionUF(n * n);
    isPercolated = false;
    grid = new Site[n][n];

    // initialize the site grid
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = new Site(false, false, false);
      }
    }
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    if (!validateInput(row, col)) {
      throw new IllegalArgumentException();
    }
    if (!isOpen(row, col)) {
      Site site = grid[row - 1][col - 1];
      site.setIsOpened(true);
      if (grid.length == 1) isPercolated = true;

      // specify the position of surrounding sites in the UF data structure
      int index = (row - 1) * grid.length + col - 1;
      int upIndex = index - grid.length;
      int downIndex = index + grid.length;
      int leftIndex = index - 1;
      int rightIndex = index + 1;

      if (row == 1) {
        site.setIsFull(true);
      }

      if (row == grid.length) {
        site.setIsBottomConnected(true);
      }

      if (row > 1 && isOpen(row - 1, col)) {
        Site firstRoot = getRootSiteByIndex(index);
        Site secondRoot = getRootSiteByIndex(upIndex);
        uf.union(index, upIndex);
        Site rootSite = getRootSiteByIndex(index);
        rootSite.setIsFull(firstRoot.getIsFull() ? firstRoot.getIsFull() : secondRoot.getIsFull());
        rootSite.setIsBottomConnected(firstRoot.getIsBottomConnected() ? firstRoot.getIsBottomConnected() : secondRoot.getIsBottomConnected());
        if (rootSite.getIsFull() && rootSite.getIsBottomConnected()) {
          isPercolated = true;
        }
      }

      if (row < grid.length && isOpen(row + 1, col)) {
        Site firstRoot = getRootSiteByIndex(index);
        Site secondRoot = getRootSiteByIndex(downIndex);
        uf.union(index, downIndex);
        Site rootSite = getRootSiteByIndex(index);
        rootSite.setIsFull(firstRoot.getIsFull() ? firstRoot.getIsFull() : secondRoot.getIsFull());
        rootSite.setIsBottomConnected(firstRoot.getIsBottomConnected() ? firstRoot.getIsBottomConnected() : secondRoot.getIsBottomConnected());
        if (rootSite.getIsFull() && rootSite.getIsBottomConnected()) {
          isPercolated = true;
        }
      }

      if (col > 1 && isOpen(row, col - 1)) {
        Site firstRoot = getRootSiteByIndex(index);
        Site secondRoot = getRootSiteByIndex(leftIndex);
        uf.union(index, leftIndex);
        Site rootSite = getRootSiteByIndex(index);
        rootSite.setIsFull(firstRoot.getIsFull() ? firstRoot.getIsFull() : secondRoot.getIsFull());
        rootSite.setIsBottomConnected(firstRoot.getIsBottomConnected() ? firstRoot.getIsBottomConnected() : secondRoot.getIsBottomConnected());
        if (rootSite.getIsFull() && rootSite.getIsBottomConnected()) {
          isPercolated = true;
        }
      }

      if (col < grid.length && isOpen(row, col + 1)) {
        Site firstRoot = getRootSiteByIndex(index);
        Site secondRoot = getRootSiteByIndex(rightIndex);
        uf.union(index, rightIndex);
        Site rootSite = getRootSiteByIndex(index);
        rootSite.setIsFull(firstRoot.getIsFull() ? firstRoot.getIsFull() : secondRoot.getIsFull());
        rootSite.setIsBottomConnected(firstRoot.getIsBottomConnected() ? firstRoot.getIsBottomConnected() : secondRoot.getIsBottomConnected());
        if (rootSite.getIsFull() && rootSite.getIsBottomConnected()) {
          isPercolated = true;
        }
      }

      numOpenSites++;
    }
  }

  private Site getRootSiteByIndex(int index) {
    int root = uf.find(index);
    int rowRoot = root / grid.length;
    int colRoot = root % grid.length;
    return grid[rowRoot][colRoot];
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    if (!validateInput(row, col)) {
     throw new IllegalArgumentException();
    }
    return grid[row - 1][col - 1].getIsOpened();
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    if (!validateInput(row, col)) {
      throw new IllegalArgumentException();
    }
    int index = (row - 1) * grid.length + col - 1;
    Site rootSite = getRootSiteByIndex(index);
    return rootSite.getIsFull();
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return numOpenSites;
  }

  // does the system percolate?
  public boolean percolates() {
    return isPercolated;
  }

  private boolean validateInput(int row, int col) {
    if (row < 1 || row > grid.length || col < 1 || col > grid.length) return false;
    return true;
  }

  private static class Site {
    private boolean isOpened;
    private boolean isFull;
    private boolean isBottomConnected;

    public Site(boolean isOpened, boolean isFull, boolean isBottomConnected) {
      this.isOpened = isOpened;
      this.isFull = isFull;
      this.isBottomConnected = isBottomConnected;
    }

    public boolean getIsOpened() {
      return isOpened;
    }

    public void setIsOpened(boolean isOpened) {
      this.isOpened = isOpened;
    }

    public boolean getIsFull() {
      return isFull;
    }

    public void setIsFull(boolean isFull) {
      this.isFull = isFull;
    }

    public boolean getIsBottomConnected() {
      return isBottomConnected;
    }

    public void setIsBottomConnected(boolean isBottomConnected) {
      this.isBottomConnected = isBottomConnected;
    }
  }

  // test client (optional)
  public static void main(String[] args) {
    int n = 4;
    Percolation percolation = new Percolation(n);
    while (!percolation.percolates()) {
      int row = StdRandom.uniform(1, n + 1);
      int col = StdRandom.uniform(1, n + 1);
      percolation.open(row, col);
    }

    StdOut.println("Probability p: " + (double) percolation.numberOfOpenSites() / (n * n));
  }
}
