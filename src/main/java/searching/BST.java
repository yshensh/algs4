package searching;

import fundamentals.Queue;

import java.util.NoSuchElementException;

/**
 * Use Binary Search Tree (BST) to implement ordered symbol-table.
 * A binary search tree (BST) is a binary tree where each node
 * has a Comparable key (and an associated value) and satisfies
 * the restriction that the key in any node is larger than the
 * keys in all nodes in that node's left subtree and smaller than
 * the keys in all nodes in that node's right subtree.
 * <p>
 * Basic methods:
 * - get
 * - put
 * <p>
 * Order-based methods and deletion:
 * - minimum / maximum
 * - floor / ceiling
 * - selection
 * - rank
 * - delete the minimum and maximum
 * - delete
 * - range search
 * <p>
 * search
 * - worst cost: n
 * - avg cost: log(n)
 * <p>
 * insert
 * - worst cost: n
 * - avg cost: log(n)
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;              // root of BST

    private class Node {
        private Key key;            // sorted by key
        private Value val;          // associated data
        private Node left, right;   // left and right substrees
        private int size;           // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BST() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table.
     */
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     * and {@code null} if the key is not in the symbol table
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     *
     * @param key the key
     * @param val the value
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            // save a link to the node to be deleted in t
            Node t = x;
            // set x to point to its successor min(t.right)
            x = min(t.right);
            // set the right link of x (which is supposed to point to the
            // BST containing all the keys larger than x.key) to deleteMin(t.right)
            x.right = deleteMin(t.right);
            // set the left link of x (which was null) to t.left (all the
            // keys that are less than both the deleted key and its successor)
            x.left = t.left;
        }
        // updating the counts by setting the counts in each node on the search
        // path to be one plus the sum of the counts in its children
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }


    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     * largest key <= given key
     *
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        // case 1: k equals the key at root, the floor of k is k
        if (cmp == 0) return x;

        // case 2: k is less than the key at root, the floor of k is in the left subtree
        if (cmp < 0) return floor(x.left, key);

        // case 3: k is greater than the key at root, the floor of k is in the right subtree:
        Node t = floor(x.right, key);
        // - if there is any key <= k in the right subtree
        if (t != null) return t;
            // - otherwise, it is the key in the root
        else return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     * smallest key >= a given key.
     *
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     */
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        // case 1: k equals the key at root, the ceiling of k is k
        if (cmp == 0) return x;

        // case 2: k is less than the key at root, the ceiling of k is in the left subtree
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            // - if there is any key >= k in the left subtree
            if (t != null) return t;
                // - otherwise, it is the key in the root
            else return x;
        }

        // case 3: k is greater than the key at root, the ceiling of k is in the right subtree:
        return ceiling(x.right, key);
    }

    /**
     * Return the kth smallest key in the symbol table.
     *
     * @param k the order statistic
     * @return the kth smallest key in the symbol table.
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException();
        Node x = select(root, k);
        return x.key;
    }

    // Return key of rank k.
    private Node select(Node x, int k) {
        if (x == null) return null;
        // if t equal to k, return the key at the root.
        int t = size(x.left);
        // if the number of keys t in the left subtree is larger than k,
        // look (recursively) for the key of rank in the left subtree
        if (t > k) return select(x.left, k);
            // if t is smaller than k,
            // look (recursively) for the key of rank k-t-1 in the right subtree
            // (substract the current node, and all of the nodes on its left subtree)
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    /**
     * Return the number of keys in the symbol table strictly less than key
     *
     * @param key the key
     * @return the number of keys in the symbol table strictly less than key
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("arguement to rank() is null");
        return rank(key, root);
    }

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        // if the given key is less than the key at the root,
        // we return the rank of the key in the left subtree (recursively computed)
        if (cmp < 0) return rank(key, x.left);
            // if the given key is larger than the key at the root,
            // we return t plus one (to count the key at the root) plus the rank of the key in
            // the right subtree (recursively computed)
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
            // if the given key is equal to the key at the root,
            // we return the number of keys t in the left subtree
        else return size(x.left);
    }

    /**
     * Returns all keys in the symbol table in the given range as an iterable.
     * The implementation uses inorder traversal
     *
     * @param lo low bound
     * @param hi high bound
     * @return all keys in the symbol table between lo (inclusive) and hi (exclusive)
     */
    public Iterable<Key> rangeSearch(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    // inorder traversal
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        // (recursively) enqueue all the keys from the left subtree (if any of them could fall in the range)
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        // enqueue the node at the root (if it falls in the range)
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        // (recursively) enqueue all the keys from the right subtree (if any of them could fall in the range)
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

}
