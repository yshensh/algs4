package sorting;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * IPQ is able to dynamically update the (priority) of certain (key) efficiently
 *
 * It supports the usual insert and delete-the-minimum operations, along with
 * delete and change-the-key methods
 *
 * Change pq[] to hold indices, add an array keys[] to hold the key values, and
 * add an array qp that is the inverse of pq[] - qp[i] gives the position of i
 * in pq[] (the index such that pq[j] is i)
 *
 * qp[] is a position map that gives the position of index of the node in the
 * heap (index) given key index (i)
 * qp[] returns the index of internal structure given an external index user provides
 * qp[i] = heap index
 * input: i
 * output: heap index
 *
 * pq[] is a inverse map that gives the key index (i) given the position of index of
 * the node in the heap (index)
 * pq[heap index] = i
 * input: heap index
 * output: i
 *
 */
public class IndexMinPQ<Key extends Comparable <Key>> {
    private int maxN;                   // maximum number of elements on PQ
    private int n;                      // number of elements on PQ
    private int[] pq;                   // binary heap using 1-based indexing
    private int[] qp;                   // inverse: pq[qp[i]] = i
    private Key[] keys;                 // items with priorities, keys[i] = priority of i
    private Comparator<Key> comparator; // optional comparator

    public IndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        // use the convention that qp[i] = -1 if i is not on the queue
        for (int i = 0; i <= maxN; i++) qp[i] = -1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    /**
     * Associates key with index i.
     *
     * @param i an index
     * @param key the key to associate with index i
     */
    public void insert(int i, Key key) {
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;  // insert key into the indexed binary heap
        swim(n);
    }

    public Key minKey() {
        return keys[pq[1]];
    }

    /**
     * Removes a minimum key and returns its associated index.
     *
     * @return an index associated with a minimum key
     */
    public int delMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int indexOfMin = pq[1];
        exch(1, n--);
        sink(1);
        keys[pq[n+1]] = null;   // to help with garbage collection
        qp[pq[n+1]] = -1;       // delete node index
        return indexOfMin;
    }

    /**
     * Returns an index associated with a minimum key.
     *
     * @return an index associated with a minimum key
     */
    public int minIndex() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    /**
     * Change the key associated with index i to the specified value.
     *
     * @param i the index of the key to change
     * @param key change the key associated with index i to this key
     */
    public void change(int i, Key key) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        // qp[i] - heap index
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * Remove the key associated with index i.
     *
     * @param i the index of the key to remove
     */
    public void delete(int i) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        // heap index - the index of the node in the heap for a given key index (i)
        int index = qp[i];
        exch(index,  n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    /***************************************************
     * Helper functions to restore the heap invariant
     ***************************************************/

    // bottom-up reheapify (swim)
    private void swim(int k) {
        // if the heap order is violated: a node's key < node's parent key
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);   // exchange the node with its parent
            k = k/2;          // move to parent node
        }
    }

    // top-down heapify (sink)
    private void sink(int k) {
        while (2*k <= n) {
            // move to child node
            int j = 2*k;    // left child
            if (j < n && greater(j, j+1)) j++; // right child (if it is the larger of two children)
            if (!greater(k, j)) break;
            // if the heap order is violated: a node's key < node's children's keys, then exchange the node with the smaller of its two children.
            exch(k, j);
            // moving down the heap
            k = j;
        }
    }

    /***************************************************
     * Helper functions for compares and swaps
     ***************************************************/
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
}
