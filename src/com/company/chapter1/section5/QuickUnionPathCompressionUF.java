package com.company.chapter1.section5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionPathCompressionUF {
    int[] parent;
    int[] sizes;
    int count;

    public QuickUnionPathCompressionUF(int n) {
        parent = new int[n];
        sizes = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            sizes[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public void validate(int p) {
        int n = parent.length;
        if (p < 0 || p > n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + n);
        }
    }

    public int find(int p) {
        validate(p);
        int initialValue = p;
        while (parent[p] != p) p = parent[parent[p]];
        while (parent[initialValue] != initialValue) {
            int currentValue = initialValue;
            initialValue = parent[initialValue];
            parent[currentValue] = p;
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (sizes[rootP] >= sizes[rootQ]) {
            parent[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
        } else {
            parent[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
        }
        count--;
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

