package searching;

import fundamentals.Queue;

/**
 * open-addressing hashing methods:
 * Store N key-value pairs in a hash table of size M > N,
 * replying on empty entries in the tale to help with collision resolution.
 * <p>
 * This implementation uses the simplest open-addressing method called
 * linear probing: when there is a collision (when we hash to a table
 * index that is already occupied with a key different from the search
 * key), then we just check the next entry in the table (by
 * incrementing the index)
 * - key equal to search key: search hit
 * - empty position (null key at indexed position): search miss
 * - key not equal to search key: try next entry
 */
public class LinearProbingHashST<Key, Value> {
    private int n;          //number of key-value pairs in the table
    private int m;          // size of linear-probing table
    private Key[] keys;     // the keys
    private Value[] vals;   // the values

    // constructor
    public LinearProbingHashST() {
        this(16);
    }

    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    // hash function for keys = returns value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // assure that alpha (n/m) <= 1/2
    // alpha is the percentage of table entries that are occupied
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < m; i++)
            if (keys[i] != null)
                t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        m = t.m;
    }

    // put key-value pair into the table
    public void put(Key key, Value val) {
        // double table size if 1/2 full
        if (n >= m / 2) resize(2 * m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    // value paired with key (null if key is absent)
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    // remove key (and its value) from table
    public void delete(Key key) {
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % m;

        // set the key's table position to null
        keys[i] = null;
        vals[i] = null;

        // rehash all keys to the right of the deleted key
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % m;
        }
        n--;

        // halves size of array if it's 1/8 full or less
        if (n > 0 && n == m / 8)
            resize(m / 2);
    }

    // is there a value paired with key?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // is the table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of key-value pairs in the table
    public int size() {
        return n;
    }

    // all the keys in the table
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }

}
