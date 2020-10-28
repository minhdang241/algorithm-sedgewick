package com.company.chapter2.section1;

import edu.princeton.cs.algs4.StdIn;

public class Ex25 {
    public static void insertionSortWithoutExchange(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable v = a[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
               if (less(a[j], v)) break;
               a[j + 1] = a[j];
            }
            a[j + 1] = v;
        }
        assert isSorted(a);
    }

    public static void insertionSort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i - 1; j >= 0 && less(a[j + 1],a[j]); j--) {
                exch(a, j, j + 1);
            }
        }
        assert isSorted(a);
    }

    // Helper function
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
       Object temp = a[i];
       a[i] = a[j];
       a[j] = temp;
    }

    // Validation function
    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        insertionSortWithoutExchange(a);
    }

}
