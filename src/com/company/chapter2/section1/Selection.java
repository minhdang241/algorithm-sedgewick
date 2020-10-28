package com.company.chapter2.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Selection {
  public Selection() {}

  public static void sort(Comparable[] a) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
      int min = i;
      for (int j = i + 1; j < n; j++) {
        if (less(a[j], a[min])) min = j;
      }
      exch(a, i, min);
      assert isSorted(a, 0, i);
    }
    assert isSorted(a);
  }

  /***************************************************************************
   *  Helper sorting functions.
   ***************************************************************************/
  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static void exch(Object[] a, int index, int minIndex) {
    Object tempValue = a[index];
    a[index] = a[minIndex];
    a[minIndex] = tempValue;
  }

  /***************************************************************************
   *  Check if array is sorted - useful for debugging.
   ***************************************************************************/
  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i  <= hi; i++) {
      if (less(a[i], a[i - 1])) return false;
    }
    return true;
  }

  public static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }

  public static void main(String[] args) {
    String[] a = StdIn.readAllStrings();
    Selection.sort(a);
    show(a);
  }
}
