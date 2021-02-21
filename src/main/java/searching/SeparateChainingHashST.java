package searching;

import fundamentals.Queue;

public class SeparateChainingHashST<Key, Value> {
    private int n;  // number of key-value pairs
    private int m;  // hash table size
    private SequentialSearchST<Key, Value>[] st;    // array of ST objects

    // constructor
    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        // Create M linked lists.
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    }

    // hash function for keys = returns value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // put key-value pair into the tabl
    void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }

    // value paired with key (null if key is absent)
    Value get(Key key) {
        return st[hash(key)].get(key);
    }

    // remove key (and its value) from table
    void delete(Key key) {
        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);
    }

    // is there a value paired with key?
    boolean contains(Key key) {
        return get(key) != null;
    }

    // is the table empty?
    boolean isEmpty() {
        return size() == 0;
    }

    // number of key-value pairs in the table
    int size() {
        return n;
    }

    // all the keys in the table
    Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }
}
