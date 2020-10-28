package com.company;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Bag<Item> implements Iterable<Item> {
  private Node first;
  private int n;

  // helper linked list class
  private class Node {
    private Item item;
    private Node next;
  }

  // initialize the Bag
  public Bag() {
    first = null;
    n = 0;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public int size() {
    return n;
  }

  public void add(Item item) {
    Node oldNode = first;
    first = new Node();
    first.item = item;
    first.next = oldNode;
    n++;
  }

  @Override
  public Iterator<Item> iterator() {
    return new LinkedIterator(first);
  }

  private class LinkedIterator implements Iterator<Item> {

    private Node current;

    public LinkedIterator(Node node) {
      current = node;
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Does not support remove operation");
    }
  }

  public static void main(String[] args) {
    Bag<String> myBag = new Bag<>();
//    while(!StdIn.isEmpty()) {
//      String item = StdIn.readString();
//      myBag.add(item);
//    }
    myBag.add("Minh");
    myBag.add("Linh");
    myBag.add("HT");
    myBag.add("Quan");
    myBag.add("Hien");
    myBag.add("QA");

    StdOut.printf("The size of the bag: %d\n", myBag.size());
    for (String s : myBag) {
      StdOut.println(s);
    }

    Bag<Integer> myBag2 = new Bag<>();
//    while(!StdIn.isEmpty()) {
//      String item = StdIn.readString();
//      myBag.add(item);
//    }
    myBag2.add(1);
    myBag2.add(2);
    myBag2.add(3);

    StdOut.printf("The size of the bag: %d\n", myBag2.size());
    for (int s : myBag2) {
      StdOut.println(s);
    }
  }
}
