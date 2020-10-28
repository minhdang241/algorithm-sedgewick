package com.company.chapter2.section1.assignment2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
  public static void main(String[] args) {
    RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      randomizedQueue.enqueue(s);
    }

    int n = Integer.parseInt(args[0]);
    for (int i = 0; i < n; i++) {
      StdOut.println(randomizedQueue.dequeue());
    }
  }
}
