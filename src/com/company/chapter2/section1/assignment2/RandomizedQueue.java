package com.company.chapter2.section1.assignment2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Object[] items;
  private int size;

  // construct an empty randomized queue
  public RandomizedQueue() {
    items = new Object[10];
    size = 0;
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return size;
  }

  private void resize(int newSize) {
    Object[] newItemArray = new Object[newSize];
    for (int i = 0; i < size; i++) {
      newItemArray[i] = items[i];
    }
    items = newItemArray;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) throw new IllegalArgumentException();
    if (size >= items.length) resize(size * 2);
    items[size] = item;
    size++;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException();
    int randomIndex = StdRandom.uniform(size);
    Item removedItem = (Item) items[randomIndex];
    items[randomIndex] = items[size - 1];
    items[size - 1] = null;
    size--;
    if (size > 0 && (size == items.length / 4)) resize(items.length / 2);
    return removedItem;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (size == 0) throw new NoSuchElementException();
    int randomIndex = StdRandom.uniform(size);
    Item item = (Item) items[randomIndex];
    return item;
  }

  public Iterator<Item> iterator() {
    return new ResizedArrayIterator<>(size, items);
  }

  private class ResizedArrayIterator<Item> implements Iterator<Item> {
    private Object[] items;
    private int currIndex;

    public ResizedArrayIterator(int size, Object[] values) {
      items = new Object[size];
      for (int i = 0; i < size; i++) {
        items[i] = values[i];
      }
      StdRandom.shuffle(items);
      currIndex = 0;
    }

    @Override
    public boolean hasNext() {
      return currIndex < size;
    }

    @Override
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException("End of queue");
      Item item = (Item) items[currIndex];
      currIndex++;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Operation remove is not supported");
    }
  }

  private static <Item> void show(RandomizedQueue<Item> queue) {
    for (Item value : queue) {
      StdOut.print(value + " ");
    }
    StdOut.println();
  }
  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<String> strQueue = new RandomizedQueue<>();
    strQueue.enqueue("Minh");
    strQueue.enqueue("Linh");
    strQueue.enqueue("Bao");
    strQueue.enqueue("Hien");
    // show(strQueue);
    // StdOut.println(strQueue.sample());
    // show(strQueue);
    StdOut.println(strQueue.dequeue());
    StdOut.println(strQueue.dequeue());
    // StdOut.println(strQueue.dequeue());
    // StdOut.println(strQueue.dequeue());

    show(strQueue);
  }
}
