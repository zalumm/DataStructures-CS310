/**
 * Program 2 1-2 Line Description of class/program CS-310 Date : 3/5/2019
 *
 * @author Salim Aweys csscXXXX
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class LinearList<E extends Comparable<E>> implements LinearListADT<E> {
  private int modCount;
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
    modCount++;

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
    modCount++;
    return false;
  }

  @Override
  public E removeFirst() {
    if (currentSize == 0) throw new NoSuchElementException();
    E data = head.data;
    Node<E> headTmp = head;
    if (head == tail) tail = null;
    else head.next.previous = null;
    head = head.next;
    headTmp.next = null;
    currentSize--;
    modCount++;

    return data;
  }

  @Override
  public E removeLast() {
    if (currentSize == 0) throw new NoSuchElementException();
    E data = tail.data;
    Node<E> tailTmp = tail;
    if (head == tail) head = null;
    else tail.previous.next = null;
    tail = tail.previous;
    tailTmp.previous= null;
    currentSize--;
    modCount++;
    return data;
  }

  @Override
  public E remove(E obj) {
	  int count = 0 ; 
	  Node<E> index = head; 
	  E foundData = obj; 
	  while(count < currentSize) {
		  if (((Comparable<E>) index.data).compareTo(foundData) == 0) {
			  if(index == head) 
				  return this.removeFirst(); 
			  else if(index == tail)
				  return this.removeLast();
			  else {
				  index.previous.next = index.next;
				  index.next.previous = index.previous;
				  index.next = index.previous =  null;
				  currentSize--;
				  modCount++;
				  return index.data;
			  }
				  
		  }
		  index = index.next;
		  count++;
		  
	  }
    return null;
  }

  @Override
  public E peekFirst() {
    if (head == null) return null;
    return head.data;
  }

  @Override
  public E peekLast() {
    if (tail == null) return null;
    return tail.data;
  }

  @Override
  public boolean contains(E obj) {
	  return find(obj) != null;
  }

  @Override
  public E find(E obj) {
      for (E temp : this)
          if (((Comparable<E>) obj).compareTo(temp) == 0)
              return temp;
return null;
//

  }

  @Override
  public void clear() {
    currentSize = 0;
    head = tail = null;
    modCount++;
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
	  return new IteratorHelper();
  }
  private class IteratorHelper implements Iterator<E> {
	  Node<E> index = head;
      int count = 0;
      int expectedModCount = modCount;


      @Override
      public boolean hasNext() {
          return count < currentSize;
      }

      @Override
      public E next() {
          if (modCount != expectedModCount)
              throw new ConcurrentModificationException();
          if (!hasNext())
              throw new NoSuchElementException();
          E data = index.data;
          index = index.next;
          count++;
          return data;


      }

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
