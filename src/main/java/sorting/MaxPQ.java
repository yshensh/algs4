package sorting;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key> implements Iterable<Key> {
    private Key[] pq;   // store items at indices 1 to n
    private int n;      // number of items on priority queue
    private Comparator<Key> comparator;     // optional comparator

    /***************************************************
     * MaxPQ constructors
     ****************************************************/

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapcity the initial capacity of this priority queue
     */
    public MaxPQ(int initCapcity) {
        pq = (Key[]) new Object[initCapcity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param initCapacity the initial capacity of this priority queue
     * @param comparator comparator the order in which to compare the keys
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param comparator the order in which to compare the keys
     */
    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param keys keys the array of keys
     */
    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n ; i++)
            pq[i+1] = keys[i];
        for (int k = n/2; k >= 1; k--)
            sink(k);
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /***************************************************
     * MaxPQ API: insert, delMax, max
     ****************************************************/

    /**
     * Returns true if priority queue is empty.
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Adds a new key to priority queue.
     *
     * @param x the new key to add to this priority queue.
     */
    public void insert(Key x) {
        if (n == pq.length - 1) resize(2 * pq.length);

        // add x and maintain heap invariant
        pq[++n] = x;
        swim(n);
    }

    /**
     * Removes and returns a largest key on priority queue.
     *
     * @return a largest key on priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        // take the largest item off the top
        Key max = pq[1];
        // put the item from the end of the heap at the top, decrement the size of the heap
        exch(1, n--);
        // sink down through the heap to restore heap invariant
        sink(1);

        pq[n+1] = null;     // to avoid loitering and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);

        return max;
    }

    /**
     * Returns a largest key on priority queue.
     *
     * @return a largest key on priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        // take the largest item off the top
        Key max = pq[1];
        return max;
    }

    /***************************************************
     * Helper functions to restore the heap invariant
     ****************************************************/
    // bottom-up reheapify (swim)
    private void swim(int k) {
        // if the heap order is violated: a node's key > node's parent key
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);   // exchange the node with its parent
        }
    }

    // top-down heapify (sink)
    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;    // left child
            if (j < n && less(j, j+1)) j++; // right child (if it is the larger of two children)

            if (!less(k, j)) break;

            // if the heap order is violated: a node's key < node's children's keys, then exchange the node with the larger of its two children.
            exch(k, j);

            // moving down the heap
            k = j;
        }
    }


    /***************************************************
     * Helper functions for compares and swaps
     ***************************************************/

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        // create a new pq
        private MaxPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null) copy = new MaxPQ<Key>(size());
            else                    copy = new MaxPQ<Key>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }
}
