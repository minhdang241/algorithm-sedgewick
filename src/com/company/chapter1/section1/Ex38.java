package com.company.chapter1.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Ex38 {

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();
        int key = 240198;
        long start = System.nanoTime();
        int index = bruteForceSearch(whitelist, key);
        long end = System.nanoTime();
        StdOut.printf("Brute Force Search approach takes %9d ms\n", end - start);

        Arrays.sort(whitelist);
        start = System.nanoTime();
        index = binarySearch(whitelist, key);
        end = System.nanoTime();
        StdOut.printf("Binary Search Search approach takes %9d ms\n", end - start);
    }

    public static int binarySearch(int[] a, int key) {
       int hi = a.length - 1, li = 0;
       while (li <= hi) {
           int mid = li + (hi - li) / 2;
           if (key < a[mid]) hi = mid - 1;
           else if (key > a[mid]) li = mid + 1;
           else return mid;
       }
       return -1;
    }
    public static int bruteForceSearch(int[] a, int key) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) return i;
        }
        return -1;
    }


}
