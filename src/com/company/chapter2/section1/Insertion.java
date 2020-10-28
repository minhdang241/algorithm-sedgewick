package com.company.chapter2.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Insertion {

  public Insertion() {}

  /**
   * Rearranges the subarray a[lo..hi) in ascending order
   *
   * @param a the array to be sorted
   */
//  public static void sort(Comparable[] a) {
//    for (int i = 1; i < a.length; i++) {
//      for (int j = i; j > 0; j--) {
//        if (less(a[j], a[j - 1])) {
//          exch(a, j, j - 1);
//        } else break;
//      }
//      assert isSorted(a, 0, i);
//    }
//    assert isSorted(a);
//  }
  public static void sort(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      int j;
      Comparable v = a[i];
      for (j = i - 1; j >= 0; j--) {
          if (less(a[j], v)) break;
          a[j + 1] = a[j];
      }
      a[j + 1] = v;
    }
    assert isSorted(a);
  }

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  public static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }

  public static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) {
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
    Insertion.sort(a);
    show(a);
  }
}
