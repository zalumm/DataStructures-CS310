package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Hashtable<K extends Comparable<K>, V> implements DictionaryADT<K, V> {
  private int currentSize, maxSize, tableSize;
  private int modCounter;
  private LinkedListDS<DictionaryNode<K, V>>[] list;

  public Hashtable(int max) {
    currentSize = 0;
    maxSize = max;
    modCounter = 0;
    tableSize = (int) (maxSize * 1.3f);
    list = new LinkedListDS[tableSize];
    for (int i = 0; i < tableSize; i++) list[i] = new LinkedListDS<DictionaryNode<K, V>>();
  }

  @Override
  public boolean contains(K key) {
    return list[getHashCode(key)].contains(new DictionaryNode<K, V>(key, null));
  }

  @Override
  public boolean add(K key, V value) {
    if (isFull() || contains(key)) return false; // No dupes
    list[getHashCode(key)].addLast(new DictionaryNode<K, V>(key, value));
    currentSize++;
    modCounter++;
    return true;
  }

  @Override
  public boolean delete(K key) {
    if (isEmpty() || !contains(key)) return false;
    list[getHashCode(key)].remove(new DictionaryNode<K, V>(key, null));
    currentSize--;
    modCounter++;
    return true;
  }

  @Override
  public V getValue(K key) {
    if (isEmpty() || !contains(key)) return null;
    DictionaryNode<K, V> tmp = list[getHashCode(key)].find(new DictionaryNode(key, null));
    if (tmp == null) return null;
    return tmp.value;
  }

  @Override
  public K getKey(V value) {
    if (isEmpty()) return null;
    for (int i = 0; i < tableSize; i++)
      for (DictionaryNode<K, V> w : list[i])
        if (((Comparable<V>) value).compareTo(w.value) == 0) return w.key;
    return null;
  }

  @Override
  public int size() {
    return currentSize;
  }

  @Override
  public boolean isFull() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return currentSize == 0;
  }

  @Override
  public void clear() {
    for (int i = 0; i < tableSize; i++) list[i].makeEmpty();
    currentSize = 0;
    modCounter++;
  }

  @Override
  public Iterator<K> keys() {
    return new KeyIteratorHelper();
  }

  @Override
  public Iterator<V> values() {
    return new ValueIteratorHelper();
  }

  class DictionaryNode<K, V> implements Comparable<DictionaryNode<K, V>> {
    K key;
    V value;

    public DictionaryNode(K k, V v) {
      key = k;
      value = v;
    }

    public int compareTo(DictionaryNode<K, V> w) {
      return ((Comparable<K>) key).compareTo(w.key);
    }
  }

  abstract class IteratorHelper<E> implements Iterator<E> {
    protected DictionaryNode<K, V>[] nodes;
    protected int index;
    protected int modCheck;

    public IteratorHelper() {
      nodes = new DictionaryNode[currentSize];
      index = 0;
      int j = 0;
      modCheck = modCounter;
      for (int i = 0; i < tableSize; i++) for (DictionaryNode n : list[i]) nodes[j++] = n;
      quickSortHelper(nodes, 0, nodes.length - 1);
    }

    public boolean hasNext() {
      if (modCheck != modCounter) throw new ConcurrentModificationException();
      return index < currentSize;
    }

    public abstract E next();

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  class KeyIteratorHelper<K> extends IteratorHelper<K> {
    public KeyIteratorHelper() {
      super();
    }

    public K next() {
      if (!hasNext()) throw new NoSuchElementException();
      return (K) nodes[index++].key;
    }
  }

  class ValueIteratorHelper<V> extends IteratorHelper<V> {
    public ValueIteratorHelper() {
      super();
    }

    public V next() {
      if (!hasNext()) throw new NoSuchElementException();
      return (V) nodes[index++].value;
    }
  }

  private int getHashCode(K key) {
    return (key.hashCode() & 0x7FFFFFFF) % tableSize;
  }

  // Referenced GeeksForGeeks and RigginsReader
  private <T extends Comparable<T>> void quickSortHelper(T arr[], int low, int high) {
    if (low < high) {
      // partition index
      int index = partition(arr, low, high);

      // Recursively sort elements before
      // partition and after partition
      quickSortHelper(arr, low, index - 1);
      quickSortHelper(arr, index + 1, high);
    }
  }

  private <T extends Comparable<T>> int partition(T arr[], int low, int high) {
    T pivot = arr[high];
    int i = (low - 1); // index of smaller element
    for (int j = low; j < high; j++) {
      // If current element is smaller than or
      // equal to pivot
      if (arr[j].compareTo(pivot) <= 0) {
        i++;

        // Swap elements
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }

    T temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    return i + 1;
  }

  /*  LinkedList
   *  Singly linked list
   *  PKraft Spring 2019
   */

  class LinkedListDS<E> implements ListADT<E> {
    /////////////////////////////////////////////////////////////////
    class Node<T> {
      T data;
      Node<T> next;

      public Node(T obj) {
        data = obj;
        next = null;
      }
    }
    // END CLASS NODE ///////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////
    class ListIteratorHelper implements Iterator<E> {
      Node<E> index;

      public ListIteratorHelper() {
        index = head;
      }

      public boolean hasNext() {
        return index != null;
      }

      public E next() {
        if (!hasNext()) throw new NoSuchElementException();
        E tmp = index.data;
        index = index.next;
        return tmp;
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    }
    // END CLASS LIST_ITERATOR_HELPER //////////////////////////////

    private Node<E> head, tail;
    private int currentSize;

    public LinkedListDS() {
      head = tail = null;
      currentSize = 0;
    }

    public void addFirst(E obj) {
      Node<E> newNode = new Node<E>(obj);
      if (isEmpty()) head = tail = newNode;
      else {
        newNode.next = head;
        head = newNode;
      }
      currentSize++;
    }

    public void addLast(E obj) {
      Node<E> newNode = new Node<E>(obj);
      if (isEmpty()) head = tail = newNode;
      else {
        tail.next = newNode;
        tail = newNode;
      }
      currentSize++;
    }

    public E removeFirst() {
      if (isEmpty()) return null;
      E tmp = head.data;
      head = head.next;
      if (head == null) head = tail = null;
      currentSize--;
      return tmp;
    }

    public E removeLast() {
      if (isEmpty()) return null;
      E tmp = tail.data;
      if (head == tail) // only one element in the list
      head = tail = null;
      else {
        Node<E> previous = null, current = head;
        while (current != tail) {
          previous = current;
          current = current.next;
        }
        previous.next = null;
        tail = previous;
      }

      currentSize--;
      return tmp;
    }

    public E peekFirst() {
      if (head == null) return null;
      return head.data;
    }

    public E peekLast() {
      if (tail == null) return null;
      return tail.data;
    }

    public E find(E obj) {
      if (head == null) return null;
      Node<E> tmp = head;
      while (tmp != null) {
        if (((Comparable<E>) obj).compareTo(tmp.data) == 0) return tmp.data;
        tmp = tmp.next;
      }
      return null;
    }

    public boolean remove(E obj) {
      if (isEmpty()) return false;
      Node<E> previous = null, current = head;
      while (current != null) {
        if (((Comparable<E>) current.data).compareTo(obj) == 0) {
          if (current == head) removeFirst();
          else if (current == tail) removeLast();
          else {
            previous.next = current.next;
            currentSize--;
          }
          return true;
        }
        previous = current;
        current = current.next;
      }
      return false;
    }

    // not in the interface.  Removes all instances of the key obj
    public boolean removeAllInstances(E obj) {
      Node<E> previous = null, current = head;
      boolean found = false;
      while (current != null) {
        if (((Comparable<E>) obj).compareTo(current.data) == 0) {
          if (previous == null) { // node to remove is head
            head = head.next;
            if (head == null) tail = null;
          } else if (current == tail) {
            previous.next = null;
            tail = previous;
          } else previous.next = current.next;
          found = true;
          currentSize--;
          current = current.next;
        } else {
          previous = current;
          current = current.next;
        }
      } // end while
      return found;
    }

    public void makeEmpty() {
      head = tail = null;
      currentSize = 0;
    }

    public boolean contains(E obj) {
      Node current = head;
      while (current != null) {
        if (((Comparable<E>) current.data).compareTo(obj) == 0) return true;
        current = current.next;
      }
      return false;
    }

    public boolean isEmpty() {
      return head == null;
    }

    public boolean isFull() {
      return false;
    }

    public int size() {
      return currentSize;
    }

    public Iterator<E> iterator() {
      return new ListIteratorHelper();
    }
  }

  interface ListADT<E> extends Iterable<E> {

    //  Adds the Object obj to the beginning of the list
    void addFirst(E obj);

    //  Adds the Object obj to the end of the list
    void addLast(E o);

    //  Removes the first Object in the list and returns it.
    //  Returns null if the list is empty.
    E removeFirst();

    //  Removes the last Object in the list and returns it.
    //  Returns null if the list is empty.
    E removeLast();

    //  Returns the first Object in the list, but does not remove it.
    //  Returns null if the list is empty.
    E peekFirst();

    //  Returns the last Object in the list, but does not remove it.
    //  Returns null if the list is empty.
    E peekLast();

    //  Finds and returns the Object obj if it is in the list, otherwise
    //  returns null.  Does not modify the list in any way
    E find(E obj);

    //  Removes the first instance of thespecific Object obj from the list, if it exists.
    //  Returns true if the Object obj was found and removed, otherwise false
    boolean remove(E obj);

    //  The list is returned to an empty state.
    void makeEmpty();

    //  Returns true if the list contains the Object obj, otherwise false
    boolean contains(E obj);

    //  Returns true if the list is empty, otherwise false
    boolean isEmpty();

    //  Returns true if the list is full, otherwise false
    boolean isFull();

    //  Returns the number of Objects currently in the list.
    int size();

    //  Returns an Iterator of the values in the list, presented in
    //  the same order as the list.
    Iterator<E> iterator();
  }
}
