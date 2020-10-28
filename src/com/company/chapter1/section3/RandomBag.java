package com.company.chapter1.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomBag<Item> implements Iterable<Item> {
  private static final int INIT_CAPACITY = 8;
  private Item[] a;
  private int n;

  public RandomBag() {
    a = (Item[]) new Object[INIT_CAPACITY];
    n = 0;
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public int size() {
    return n;
  }
  /**
   * Adds the item to this bag.
   *
   * @param item the item to add to this bag
   */
  public void add(Item item) {
    if (n == a.length) resize(n * 2);
    a[n++] = item;
  }

  public void resize(int capacity) {
    Item[] newArray = (Item[]) new Object[capacity];
    for (int i = 0; i < a.length; i++) {
      newArray[i] = a[i];
    }
    a = newArray;
  }

  public Iterator<Item> iterator() {
    return new RandomBagIterator();
  }

  private class RandomBagIterator implements Iterator<Item> {
    private int index = 0;
    private Item[] array = (Item[]) new Object[n];

    public RandomBagIterator() {
      for (int i = 0; i < n; i++) {
        array[i] = a[i];
      }
      StdRandom.shuffle(array);
    }

    @Override
    public boolean hasNext() {
      return index < n;
    }

    @Override
    public Item next() {
      Item item = array[index];
      index++;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("delete operation is not allowed");
    }
  }

  public static void main(String[] args) {
    RandomBag<Integer> randomBag = new RandomBag<>();
    randomBag.add(1);
    randomBag.add(2);
    randomBag.add(3);
    randomBag.add(4);
    randomBag.add(5);
    randomBag.add(6);
    randomBag.add(7);
    randomBag.add(8);
    randomBag.add(9);

    for (int value : randomBag) {
      StdOut.println(value);
    }
  }
}
