/* Salim Aweys
   cssc1454
*/
package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<K extends Comparable<K>, V> implements DictionaryADT<K, V> {
  private int currentSize, modCounter;
  private K k;
  private Node<K, V> root;

  public BinarySearchTree() {
    root = null;
    currentSize = 0;
    modCounter = 0;
  }

  @Override
  public boolean contains(K key) {
    return find(key, root) != null;
  }

  // Worked From riggins reader
  private V find(K key, Node<K, V> n) {
    if (n == null) return null;
    if (key.compareTo(n.key) < 0) return find(key, n.leftChild);
    else if (key.compareTo(n.key) > 0) return find(key, n.rightChild);
    else return n.value;
  }

  @Override
  // Guided by Riggins reader
  public boolean add(K key, V value) {
    if (contains(key)) return false;
    if (root == null) root = new Node<K, V>(key, value); // isempty
    else insert(key, value, root, null, false);
    currentSize++;
    modCounter++;
    return true;
  }

  // Guided by Riggins reader
  private void insert(K k, V v, Node<K, V> n, Node<K, V> parent, boolean wasLeft) {
    if (n == null) { // at leaf node for insert
      if (wasLeft) parent.leftChild = new Node<K, V>(k, v);
      else parent.rightChild = new Node<K, V>(k, v);
    } else if (k.compareTo(n.key) < 0) insert(k, v, n.leftChild, n, true); // go left
    else insert(k, v, n.rightChild, n, false); // go right
  }

  @Override
  public boolean delete(K key) {
    if (isEmpty()) return false;
    if (!remove(key, root, null, false)) return false;
    currentSize--;
    modCounter++;
    return true;
  }

  private boolean remove(K k, Node<K, V> n, Node<K, V> parent, boolean wasLeft) {
    if (n == null) return false;
    // Compare with root to see what side to go L or R

    if (k.compareTo(n.key) > 0) return remove(k, n.rightChild, n, false);
    else if (k.compareTo(n.key) < 0) return remove(k, n.leftChild, n, true);
    else {
      // no children , happy case
      if (n.leftChild == null && n.rightChild == null) {
        if (parent == null) root = null;
        else if (wasLeft) parent.leftChild = null;
        else parent.rightChild = null;
        // 1 childRight
      } else if (n.leftChild == null) {
        if (parent == null) root = n.rightChild;
        else if (wasLeft) parent.leftChild = n.rightChild;
        else parent.rightChild = n.rightChild;
        // 1 child left
      } else if (n.rightChild == null) {
        if (parent == null) root = n.leftChild;
        else if (wasLeft) parent.leftChild = n.leftChild;
        else parent.rightChild = n.leftChild;
      } else { // TODO getPredess
        Node<K, V> successorNode = getSuccessor(n.rightChild);
        if (successorNode == null) {
          n.key = n.rightChild.key;
          n.value = n.rightChild.value;
          n.rightChild = n.rightChild.rightChild;
        } else {
          n.key = successorNode.key;
          n.value = successorNode.value;
        }
      }
    }
    return true;
  }

  // Traversal Helper method to find inorder succesor to node that will be deltede
  private Node<K, V> getSuccessor(Node<K, V> n) {
    Node<K, V> parent = null;
    while (n.leftChild != null) {
      parent = n;
      n = n.leftChild;
    }
    if (parent == null) return null;
    else parent.leftChild = n.rightChild;
    return n;
  }

  @Override
  // Guided by Riggins reader
  public V getValue(K key) {
    if (isEmpty()) return null;
    return find(key, root);
  }

  @Override
  public K getKey(V value) {
    if (isEmpty()) return null;
    k = null;
    findKey(root, value);
    return k;
  }

  private void findKey(Node<K, V> n, V value) {
    if (n == null) return;
    if (((Comparable<V>) value).compareTo(n.value) == 0) {
      k = n.key;
      return;
    }
    findKey(n.leftChild, value);
    findKey(n.rightChild, value);
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

  abstract class IteratorHelper<E> implements Iterator<E> {

    protected Node<K, V>[] nodes;
    protected int index, j;
    protected int modCheck;

    public IteratorHelper() {
      nodes = new Node[currentSize];
      index = 0;
      j = 0;
      modCheck = modCounter;
      inOrder(root);
    }

    public boolean hasNext() {
      if (modCheck != modCounter) throw new ConcurrentModificationException();
      return (index < currentSize);
    }

    private void inOrder(Node<K, V> node) {
      if (node == null) return;
      inOrder(node.leftChild);
      nodes[j++] = node;
      inOrder(node.rightChild);
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

  // Iterator Helper specific to values
  class ValueIteratorHelper<V> extends IteratorHelper<V> {

    public ValueIteratorHelper() {
      super();
    }

    public V next() {
      if (!hasNext()) throw new NoSuchElementException();
      return (V) nodes[index++].value;
    }
  }

  public Iterator<K> keys() {
    return new KeyIteratorHelper();
  }

  public Iterator<V> values() {
    return new ValueIteratorHelper();
  }
}
