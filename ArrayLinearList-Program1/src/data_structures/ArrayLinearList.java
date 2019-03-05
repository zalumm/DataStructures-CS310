/**
 * Program 1
 * 1-2 Line Description of class/program
 * CS108-2 (or CS108-3)
 * Date
 *
 * @author Salim Aweys csscXXXX
 */

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;


public class ArrayLinearList<E> implements LinearListADT<E> {

    public static final int DEFAULT_MAX_CAPACITY = 100;
    private int currentSize,
            maxSize,
            front,
            rear,
            modCount;
    private E[] list;

    @SuppressWarnings("unchecked")
    public ArrayLinearList(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("Cannot instanciate a list < 1 element-");
        }
        this.maxSize = maxSize;
        currentSize = 0;
        list = (E[]) new Object[maxSize];
        front = rear = 0;
    }

    //If not user input genates with default max
    @SuppressWarnings("unchecked")
    public ArrayLinearList() {
        this(DEFAULT_MAX_CAPACITY);
    }

    @Override
    public void ends() {
        System.out.println("Front: " + front + ' ' + "Rear: " + rear);

    }

    @Override
    public boolean addFirst(E obj) {
        if (currentSize == maxSize)
            return false;
        else
            front -= 1;
        if (front < 0)
            front = (maxSize - 1);

        currentSize++;
        if (currentSize == 1 || currentSize == 0)
            rear = front;
        list[front] = obj;

        modCount++;

        return true;
    }

    @Override
    public boolean addLast(E obj) {
        if (currentSize == maxSize)
            return false;
        else
            rear++;
        if (rear == maxSize)
            rear = 0;
        currentSize++;
        list[rear] = obj;

        if (currentSize == 1 || currentSize == 0)
            front = rear;

        modCount++;

        return true;
    }

    @Override
    public E removeFirst() {
        if (currentSize == 0)
            return null;

        E foundObj = list[front];
        list[front] = null;
        ++front;
        if (front >= maxSize)
            front = 0;

        --currentSize;
        if (currentSize == 1 || currentSize == 0)
            front = rear;

        modCount++;
        return foundObj;
    }

    @Override
    public E removeLast() {
        if (currentSize == 0)
            return null;
        E foundObj = list[rear];
        list[rear] = null;
        --rear;
        if (rear < 0)
            rear += maxSize;

        --currentSize;
        if (currentSize == 1 || currentSize == 0)
            rear = front;


        modCount++;
        return foundObj;
    }

    @Override
    public E remove(E obj) {
        int index = front;

        if (currentSize == 0)
            return null;
        while (((Comparable<E>) list[index]).compareTo(obj) != 0) {
            if (index == rear)
                return null;

            index++;
            if (index == maxSize)
                index = 0;
        }

        while (index != rear) {
            if (index == (maxSize - 1)) {
                list[index] = list[0];
                index = 0;
            } else {
                list[index] = list[index + 1];
                index++;
            }
        }
        currentSize--;
        rear--;
        if (rear < 0)
            rear = (maxSize - 1);

        modCount++;

        return obj;

    }

    @Override
    public E peekFirst() {
        if (currentSize == 0)
            return null;

        return list[front];
    }

    @Override
    public E peekLast() {
        if (currentSize == 0)
            return null;
        return list[rear];
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
    }

    @Override
    public void clear() {
        front = rear = currentSize = 0;
        modCount++;
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
    public int size() {
        return currentSize;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }


    private class IteratorHelper implements Iterator<E> {
        int index = front;
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
            E returnObject;
            if (!hasNext())
                throw new NoSuchElementException();
            if (index == (maxSize - 1)) {
                returnObject = list[index];
                index = 0;
                count++;
                return returnObject;
            }
            else {
                count++;
                return list[index++];
            }

        }

    }


}