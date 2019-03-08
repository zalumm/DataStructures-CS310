/**
 * Program 2 1-2 Line Description of class/program CS-310 Date : 3/5/2019
 *
 * @autor Salim Aweys csscXXXX
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinearList<E extends Comparable<E>> implements LinearListADT<E> {
  private int currentSize;
  private Node<E> head;
  private Node<E> tail;

  public LinearList() {
    currentSize = 0;
    head = tail = null;
  }

  @Override
  public boolean addFirst(E obj) {
    Node<E> tmpHead = head;
    Node<E> newNode = new Node<>(obj);
    newNode.next = tmpHead;
    head = newNode;
    if (currentSize == 0) head = tail = newNode;
    else tmpHead.previous = newNode;
    currentSize++;
    // TODO modCount++

    return true;
  }

  @Override
  public boolean addLast(E obj) {
    Node<E> tmpTail = tail;
    Node<E> newNode = new Node<>(obj);
    newNode.previous = tail;
    tail = newNode;
    if (currentSize == 0) head = newNode;
    else tmpTail.next = newNode;
    currentSize++;
    // TODO modCount++
    return false;
  }

  @Override
  public E removeFirst() {
    if (currentSize == 0) return null;
    E data = head.data;
    System.out.println("rmFirst" + head.data);
    Node<E> headTmp = head;
    if (head == tail) tail = null;
    else head.next.previous = null;
    head = head.next;
    headTmp.next = null;
    currentSize--;
    // TODO modCount++

    return data;
  }

  @Override
  public E removeLast() {
    if (currentSize == 0) throw new NoSuchElementException();
    E data = tail.data;
    Node<E> tailPrev = tail.previous;
    if (head == tail) head = null;
    else tail.previous.next = null;
    tail = tail.previous;
    tailPrev.previous = null;
    currentSize--;
    // TODO modCount++
    return data;
  }

  @Override
  public E remove(E obj) {
    return null;
  }

  @Override
  public E peekFirst() {
    if (head == null) throw new NoSuchElementException();
    return head.data;
  }

  @Override
  public E peekLast() {
    if (tail == null) return null;
    return tail.data;
  }

  @Override
  public boolean contains(E obj) {
    return false;
  }

  @Override
  public E find(E obj) {
    return null;
  }

  @Override
  public void clear() {
    currentSize = 0;
    head = tail = null;
  }

  @Override
  public boolean isEmpty() {
    return currentSize == 0;
  }

  @Override
  public boolean isFull() {
    return false;
  }

  @Override
  public int size() {
    return currentSize;
  }

  @Override
  public Iterator<E> iterator() {
    return null;
  }

  class Node<E> {
    E data;
    Node<E> next;
    Node<E> previous;

    public Node(E obj) {
      data = obj;
      next = previous = null;
    }
  }
}
