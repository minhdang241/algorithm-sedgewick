package com.company.chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Ex33 {
  public static class Matrix {
    public static double dot(double[] x, double[] y) {
      if (x == null || y == null || x.length != y.length) {
        throw new IllegalArgumentException();
      }

      double sum = 0;

      for (int i = 0; i < x.length; i++) {
        sum += x[i] * y[i];
      }

      return sum;
    }

    public static double[][] mult(double[][] a, double[][] b) {
      int N = a.length;
      int M = b[0].length;
      double[][] result = new double[N][M];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < a[i].length; j++) {
          for (int m = 0; m < M; m++) {
            result[i][m] += a[i][j] * b[j][m];
          }
        }
      }
      return result;
    }

    public static double[][] transpose(double[][] a) {
      if (a == null) throw new IllegalArgumentException();
      if (a.length == 0) return a;

      int N = a.length;
      int M = a[0].length;
      double[][] result = new double[M][N];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          result[j][i] = a[i][j];
        }
      }

      return result;
    }

    public static void print2DMatrix(double[][] a) {
      for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < a[0].length; j++) {
          StdOut.print(a[i][j] + " ");
        }
        StdOut.println();
      }
    }

    public static double[] mult(double[][] a, double[] x) {
      int N = a.length;
      double[] result = new double[N];
      for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < a[0].length; j++) {
          result[i] += a[i][j] * x[j];
        }
      }
      return result;
    }

    public static double[] mult(double[] y, double[][] a) {
      int N = a[0].length;
      double[] result = new double[N];
      for (int i = 0; i < y.length; i++) {
        for (int j = 0; j < N; j++) {
          result[j] += y[i] * a[i][j];
        }
      }
      return result;
    }

    public static void main(String[] args) {
      // Vector dot product
      double[] x = {2.0, 3.0, 4.0};
      double[] y = {3.0, 2.0, 5.5};

      StdOut.println("Dot: " + dot(x, y));

      // Matrix-matrix product
      double[][] a = {{1, 2}};
      double[][] b = {
        {2, 3, 4},
        {2, 3, 4}
      };
      double[][] c = mult(a, b);

      StdOut.println("\nMatrix multiplication:");
      for (int i = 0; i < c.length; i++) {
        for (int j = 0; j < c[0].length; j++) {
          StdOut.print(c[i][j] + " ");
        }
        StdOut.println();
      }

      // Transpose
      double[][] d = {
        {1, 2, 3},
        {4, 5, 6}
      };

      double[][] e = transpose(d);

      StdOut.println("\nTranspose:");
      for (int i = 0; i < e.length; i++) {
        for (int j = 0; j < e[0].length; j++) {
          StdOut.print(e[i][j] + " ");
        }
        StdOut.println();
      }

      // Matrix-vector product
      double[][] f = {
        {1, 2, 3},
        {4, 5, 6}
      };

      double[] g = {1, 2, 3};

      double[] h = mult(f, g);

      StdOut.println("\nMatrix-vector product:");
      for (int i = 0; i < h.length; i++) {
        StdOut.print(h[i] + " ");
      }

      StdOut.println();
      // Vector-matrix product
      double[] i = {1, 2};

      double[][] j = {
        {1, 2, 3},
        {4, 5, 6}
      };

      double[] k = mult(i, j);

      StdOut.println("\nVector-matrix product:");
      for (int l = 0; l < k.length; l++) {
        StdOut.print(k[l] + " ");
      }
    }
  }
}
