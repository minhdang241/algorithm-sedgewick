package com.company.chapter1.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Ex39 {
    public static void main(String[] args) {
        int t = Integer.parseInt(args[0]);
        int[] ns = {1000, 10000, 100000, 1000000};
        int[] a = {1,2,3,4,5,6};
        randomMatches(t, ns);
    }

    public static int[] generateSixDigitArray(int N) {
        int[] a = new int[N];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(100000, 1000000);
        }
        return a;
    }

    public static int countDuplicateNumber(int[] a, int[] a2) {
        Arrays.sort(a2);
        int count = 0;
        for (int value : a) {
            int index = binarySearch(a2, value);
            if (index != -1) count++;
        }
        return count;
    }

    public static void randomMatches(int trials, int[] experiments) {
        int length = experiments.length;
        double[] results = new double[length];

        for (int i = 0; i < trials; i++) {
            for (int j = 0; j < length; j++) {
                int[] a = generateSixDigitArray(experiments[j]);
                int[] a2 = generateSixDigitArray(experiments[j]);
                results[j] += countDuplicateNumber(a, a2);
                if (i == trials - 1) {
                    results[j] /= trials;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            StdOut.printf("%9d: %9.2f\n", experiments[i], results[i]);
        }
    }

    public static int binarySearch(int[] a, int key) {
       int h = a.length - 1, l = 0;
       while (l <= h) {
          int mid = l + (h - l) / 2;
          if (a[mid] > key) h = mid - 1;
          else if (a[mid] < key) l = mid + 1;
          else return mid;
       }
       return -1;
    }
}
