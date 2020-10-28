package com.company.chapter1.section5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionByHeightUF {
  private int[] parent;
  private int[] heights;
  private int count;

  public WeightedQuickUnionByHeightUF(int N) {
    count = N;
    parent = new int[N];
    heights = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
    }
  }

  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) return;
    if (heights[rootP] > heights[rootQ]) {
      parent[rootQ] = rootP;
    } else if (heights[rootP] < heights[rootQ]) {
      parent[rootP] = rootQ;
    } else {
      parent[rootQ] = rootP;
      heights[rootP]++;
    }
    count--;
  }

  public void validate(int p) {
    int n = parent.length;
    if (p < 0 || p > n) {
      throw new IllegalArgumentException("index " + p + " is not between 0 and " + n);
    }
  }

  public int find(int p) {
    validate(p);
    while (p != parent[p]) p = parent[p];
    return p;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public int count() {
    return count;
  }

  public static void main(String[] args) {
    int n = StdIn.readInt();
    QuickFindUF uf = new QuickFindUF(n);
    while (!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if (uf.find(p) == uf.find(q)) continue;
      uf.union(p, q);
      StdOut.println(p + " " + q);
    }
    StdOut.println(uf.count() + " components");
  }
}
