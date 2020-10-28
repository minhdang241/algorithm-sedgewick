package com.company.chapter2.section1.assignment2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that
 * supports adding and removing items from either the front or the back of the data structure. Create a generic
 * data type Deque that implements the following API:
 */
public class Deque<Item> implements Iterable<Item> {
  // construct an empty deque
  private static class Node<Item> {
    public Node(Item item) {
      this.item = item;
    }

    Item item;
    Node<Item> next;
    Node<Item> prev;
  }

  private Node<Item> mHead;
  private Node<Item> mTail;
  private int size;

  public Deque() {
    mHead = null;
    mTail = null;
    size = 0;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return mHead == null;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    Node<Item> newNode = new Node<>(item);
    if (isEmpty()) {
      mHead = newNode;
      mTail = newNode;
    } else {
      newNode.next = mHead;
      mHead.prev = newNode;
      mHead = newNode;
    }
    size++;
  }

  // add the item to the back
  public void addLast(Item item) {
    Node<Item> newNode = new Node<>(item);
    if (isEmpty()) {
      mHead = newNode;
      mTail = newNode;
    } else {
      mTail.next = newNode;
      newNode.prev = mTail;
      mTail = newNode;
    }
    size++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) throw new NoSuchElementException("Deque is empty");
    Item removeItem = mHead.item;
    mHead = mHead.next;
    if (mHead == null) mTail = null;
    else mHead.prev = null;
    size--;
    return removeItem;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (isEmpty()) throw new NoSuchElementException("Deque is empty");
    Item removeItem = mTail.item;
    mTail = mTail.prev;
    if (mTail == null) mHead = null;
    else mTail.next = null;
    size--;
    return removeItem;
  }

  // return an iterator over items in order from front to back
  @Override
  public Iterator<Item> iterator() {
    return new LinkedListIterator<>(mHead);
  }

  private static class LinkedListIterator<Item> implements Iterator<Item> {

    Node<Item> currNode;

    public LinkedListIterator(Node<Item> list) {
      currNode = list;
    }

    @Override
    public boolean hasNext() {
      return currNode != null;
    }

    @Override
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException("Deque is empty");
      Item currItem = currNode.item;
      currNode = currNode.next;
      return currItem;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Remove operation is not supported");
    }
  }

  private static <Item> void show(Deque<Item> deque) {
    for (Item value : deque) {
      System.out.println(value);
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<String> myQue = new Deque<>();
    //    Deque<Integer> integerQue = new Deque<>();
    //    integerQue.addFirst(1);
    //    integerQue.addFirst(2);
    //    integerQue.addFirst(3);
    //    integerQue.addFirst(4);
    myQue.addFirst("Minh");
    myQue.addFirst("Linh");
    myQue.addLast("HT");
    myQue.addFirst("Bao");
    System.out.println(myQue.size());
    myQue.removeFirst();
    System.out.println(myQue.size());
    myQue.removeLast();
    myQue.removeFirst();
    myQue.removeFirst();
    show(myQue);
    System.out.println(myQue.size());
  }
}
