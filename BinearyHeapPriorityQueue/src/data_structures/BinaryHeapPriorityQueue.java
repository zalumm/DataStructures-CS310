/* Salim Aweys
 cssc1454
 Implement PG using Binary Heap
 Logn insert and remove
*/
package data_structures;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
  private Wrapper<E>[] queue; // Generic Array Container
  private int currentSize, maxSize, modCounter, entryNumber;

  public BinaryHeapPriorityQueue(int size) {
    maxSize = size;
    currentSize = 0;
    entryNumber = 0;
    modCounter = 0;
    queue = new Wrapper[maxSize];
  }

  public BinaryHeapPriorityQueue() {
    this(DEFAULT_MAX_CAPACITY);
  }

  @Override
  public boolean insert(E obj) {
    if (isFull()) return false;
    queue[currentSize] = new Wrapper<E>(obj);
    currentSize++;
    modCounter++;
    trickleUp();
    return true;
  }

  //  Used Alan Riggins Reader Methods as a guide
  private void trickleUp() {
    int newIndex = currentSize - 1;
    int parentIndex = (newIndex - 1) >> 1; // efficient division by 2
    Wrapper<E> newValue = queue[newIndex];
    // checks to see if it is still a minHeap
    while (parentIndex >= 0 && newValue.compareTo(queue[parentIndex]) < 0) {
      queue[newIndex] = queue[parentIndex];
      newIndex = parentIndex;
      parentIndex = (parentIndex - 1) >> 1;
    }
    queue[newIndex] = newValue;
  }

  @Override
  public E remove() {
    if (isEmpty()) return null;
    E tmp = queue[0].data;
    queue[0] = queue[currentSize - 1];
    currentSize--;
    trickleDown();
    modCounter++;
    return tmp;
  }

  //  Used Alan Riggins Reader Methods as a guide
  private void trickleDown() {
    int current = 0;
    int child = getNextChild(current);
    Wrapper<E> tmp;
    // Loop through children to see if minHeap is maintained
    while (child != -1 && queue[current].compareTo(queue[child]) > 0) {
      tmp = queue[current];
      queue[current] = queue[child];
      queue[child] = tmp;
      current = child;
      child = getNextChild(current);
    }
  }

  //  Used Alan Riggins Reader Methods as a guide
  private int getNextChild(int current) {
    int left = (current << 1) + 1;
    int right = left + 1;
    if (right < currentSize) {
      if (queue[left].compareTo(queue[right]) < 0) return left;
      return right;
    }
    if (left < currentSize) return left;
    return -1;
  }

  @Override
  public boolean delete(E obj) {
    boolean isDeleted = false;

    BinaryHeapPriorityQueue tmpHeap = new BinaryHeapPriorityQueue(maxSize);
    if (isEmpty()) return false;
    // check to see if only 1 element in array , no extra memory needed
    if (currentSize == 1 && obj.compareTo(queue[0].data) == 0) {
      clear();
      return true;
    }
    // loop through array and search for obj
    for (int i = 0; i < currentSize; i++) {
      if (obj.compareTo(queue[i].data) != 0) {
        tmpHeap.insert(queue[i].data);
      } else {
        isDeleted = true;
      }
    }
    if (isDeleted) {
      this.queue = tmpHeap.queue;
      this.currentSize = tmpHeap.size();
      this.entryNumber = tmpHeap.entryNumber;
      modCounter++;
    }
    return isDeleted;
  }

  @Override
  public E peek() {
    if (isEmpty()) return null;
    return queue[0].data;
  }

  @Override
  public boolean contains(E obj) {
    for (int i = 0; i < currentSize; i++) {
      if (obj.compareTo(queue[i].data) == 0) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int size() {
    return currentSize;
  }

  @Override
  public void clear() {
    currentSize = 0;
    modCounter++;
  }

  @Override
  public boolean isEmpty() {
    return currentSize == 0;
  }

  @Override
  public boolean isFull() {
    return currentSize == maxSize;
  }

  @Override
  public Iterator<E> iterator() {
    return new IteratorHelper();
  }

  private class IteratorHelper implements Iterator<E> {
    int iteratorIndex;
    int expectedModCount;
    E[] auxArray;

    public IteratorHelper() {
      iteratorIndex = 0;
      expectedModCount = modCounter;
      auxArray = (E[]) new Comparable[currentSize];

      for (int i = 0; i < currentSize; i++) {
        auxArray[i] = queue[i].data;
      }
      insertSort(auxArray);
    }

    @Override
    public boolean hasNext() {
      if (modCounter != expectedModCount) throw new ConcurrentModificationException();
      return iteratorIndex < currentSize;
    }

    @Override
    public E next() {
      if (!hasNext()) throw new NoSuchElementException();
      return auxArray[iteratorIndex++];
    }
  }

  //  Used Alan Riggins Reader Methods as a guide
  protected class Wrapper<E extends Comparable<E>> implements Comparable<Wrapper<E>> {
    long number;
    E data;

    public Wrapper(E obj) {
      number = entryNumber++;
      data = obj;
    }

    public int compareTo(Wrapper<E> obj) {
      if (data.compareTo(obj.data) == 0) return (int) (number - obj.number);
      return data.compareTo(obj.data);
    }
  }

  // Used GeeksForGeeks as a guide on insertion sorts
  void insertSort(E arr[]) {
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
      E key = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j].compareTo(key) > 0) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
  }
}
