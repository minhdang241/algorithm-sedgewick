import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  // perform independent trials on an n-by-n grid
  private final double[] p;
  private double mean;
  private double stddev;

  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0)
      throw new IllegalArgumentException("n and trials should be greater than zero");
    p = new double[trials];
    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);
      while (!percolation.percolates()) {
        int row = StdRandom.uniform(1, n + 1);
        int col = StdRandom.uniform(1, n + 1);
        percolation.open(row, col);
      }
      p[i] = (double) percolation.numberOfOpenSites() / (n * n);
    }
    stddev = StdStats.stddev(p);
    mean = StdStats.mean(p);

  }

  // sample mean of percolation threshold
  public double mean() {
    return mean;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return stddev;
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean - (1.96 * stddev)/Math.sqrt(p.length);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean + (1.96 * stddev)/Math.sqrt(p.length);
  }
  // test client (see below)
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats percolationStats = new PercolationStats(n, trials);
    StdOut.printf("mean                     =%.16f\n", percolationStats.mean());
    StdOut.printf("stddev                   =%.16f\n", percolationStats.stddev());
    StdOut.printf("95%% confidence interval  =[%.16f, %.16f]", percolationStats.confidenceLo(), percolationStats.confidenceHi());
  }
}
