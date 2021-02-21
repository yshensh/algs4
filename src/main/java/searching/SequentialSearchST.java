package searching;

import fundamentals.Queue;

public class SequentialSearchST<Key, Value> {
    private int n;          // number of key-value pairs
    private Node first;     // first node in the linked list

    // the linked list of key-value pair
    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    // constructor
    public SequentialSearchST() {
    }

    // put key-value pair into the tabl
    void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }   // Search hit: update val
        }
        first = new Node(key, val, first);
        n++;
    }

    // value paired with key (null if key is absent)
    Value get(Key key) {
        // Search for key. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;   // search hit
        }
        return null;    // search miss: add new node
    }

    // remove key (and its value) from table
    void delete(Key key) {
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
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
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }
}
