package com.company.chapter1.section5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;

public class ErdosRenyi {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);     // number of trials
    int[] edges = new int[trials];              // record statistics

    // repeat the experiment trials times
    for (int t = 0; t < trials; t++) {
      edges[t] = count(n);
    }
    System.out.println(Arrays.toString(edges));

    // report statistics
    StdOut.println("1/2 n ln n = " + 0.5 * n * Math.log(n));
    StdOut.println("mean       = " + StdStats.mean(edges));
    StdOut.println("stddev     = " + StdStats.stddev(edges));
  }

  public static int count(int n) {
    int edges = 0;
    QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(n);
    while (uf.count() > 1) {
      int firstNumber = StdRandom.uniform(n);
      int secondNumber = StdRandom.uniform(n);
      uf.union(firstNumber, secondNumber);
      edges++;
    }
    return edges;
  }
}
