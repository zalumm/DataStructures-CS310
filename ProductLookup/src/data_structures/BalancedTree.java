/* Salim Aweys
   cssc1454
*/
package data_structures;

import java.util.Iterator;
import java.util.TreeMap;

public class BalancedTree<K extends Comparable<K>, V> implements DictionaryADT<K, V> {
  TreeMap balanacedTree;
  K key;
  int currentSize;
  int modCounter;

  public BalancedTree() {
    balanacedTree = new TreeMap();
    currentSize = 0;
    modCounter = 0;
  }
    @Override
  public boolean contains(K key) {
    return balanacedTree.containsKey(key);
  }
    @Override
  public boolean add(K key, V value) {
    if (balanacedTree.containsKey(key)) return false; //No Dupe keys
    else balanacedTree.put(key, value);
    modCounter++;
    return true;
  }
    @Override
  public boolean delete(K key) {
    return balanacedTree.remove(key) != null;
  }

  public V getValue(K key) {
    if (isEmpty()) return null;
    modCounter++;
    return (V) balanacedTree.get(key);
  }

  public K getKey(V value) {
    Iterator<V> values = values();
    Iterator<K> keys = keys();
    key = null;
    while (values.hasNext()) {
      if (((Comparable<V>) values.next()).compareTo(value) == 0) {
        key = keys.next();
        return key;
      }
      keys.next();
    }
    return null;
  }
    @Override
  public int size() {
    return balanacedTree.size();
  }
    @Override
  public boolean isFull() {
    return false;
  }
    @Override
  public boolean isEmpty() {
    return balanacedTree.size() == 0;
  }
    @Override
  public void clear() {
    currentSize = 0;
    modCounter++;
    balanacedTree.clear();
  }
    @Override
  public Iterator<K> keys() {
    return balanacedTree.keySet().iterator();
  }
    @Override
  public Iterator<V> values() {
    return balanacedTree.values().iterator();
  }
}
