package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class BinarySearchTree<K extends Comparable<K>, V> implements DictionaryADT<K, V> {

  private Node<K, V> root;
  private int currentSize, modCounter;
  private K k;
  private boolean wasLeft;

  public BinarySearchTree() {
    root = null;
    currentSize = 0;
    modCounter = 0;
  }

  @Override
  public boolean contains(K key) {
      return find(key,root) != null;
  }

  @Override
  //Guided by Riggins reader
  public boolean add(K key, V value) {
    if (contains(key)) return false;
    if (root == null) root = new Node<K, V>(key, value);
    else insert(key, value, root, null, false);
    currentSize++;
    modCounter++;
    return true;
  }

  private void insert(K k, V v, Node<K, V> n, Node<K, V> parent, boolean wasLeft) {
    if (n == null) { // at leaf node for insert
      if (wasLeft) parent.leftChild = new Node<K, V>(k, v);
      else parent.rightChild = new Node<K, V>(k, v);
    } else if (((Comparable<K>) k).compareTo((K) n.key) < 0)
      insert(k, v, n.leftChild, n, true); // go left
    else insert(k, v, n.rightChild, n, false); // go right
  }

  @Override
  public boolean delete(K key) {
      //TODO
      if(isEmpty()) return false;
//      if(!remove(key,root,null,false))
//          return false;
      currentSize--;
      modCounter++;
      return true;
  }

  @Override
  public V getValue(K key) {
    if (isEmpty()) return null;
    return find(key, root);
  }

  // Worked From riggins reader
  private V find(K key, Node<K, V> n) {
    if (n == null) return null;
    if (((Comparable<K>) key).compareTo(n.key) < 0) return find(key, n.leftChild);
    else if (((Comparable<K>) key).compareTo(n.key) > 0) return find(key, n.rightChild);
    else return (V) n.value;
  }

  @Override
  public K getKey(V value) {
      if(isEmpty())
          return null;
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
    root = null;
    currentSize = 0;
    modCounter++;
  }

  private class Node<K, V> {
    private K key;
    private V value;
    private Node<K, V> leftChild, rightChild;

    public Node(K k, V v) {
      key = k;
      value = v;
      leftChild = rightChild = null;
    }
  }

  @Override
  public Iterator<K> keys() {
    return null;
  }

  @Override
  public Iterator<V> values() {
    return null;
  }
}
