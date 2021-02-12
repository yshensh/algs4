package strings;

import fundamentals.Queue;

/**
 * implementation of a string symbol table using a multiway trie
 */
public class TrieST<Value> {
    private static final int R = 256;   // radix (of extended ASCII)

    private Node root;  // root of trie
    private int n;      // number of keys in trie

    /**
     * R-way trie node
     * Each node is pointed to by just one other node, which is called its parent, and each node has R links,
     * where R is the alphabet size.
     * Although links point to nodes, we can view each link as pointing to a trie, the trie whose root is the
     * reference node.
     * Each link corresponds to a character value - since each link points to exactly one node, we label each
     * node with the character value corresponding to the link that points to it.
     */
    private static class Node {
        // "value" may be null or the value associated with one of the string keys in the symbol table
        private Object val;
        // "links" are either null or references to other nodes
        private Node[] next = new Node[R];
    }

    // create a symbol table
    public TrieST() {
    }

    // put key-value pair into the table (remove key if value is null)
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    /**
     * Use the characters of the key to guide us down the trie until reaching the last character of the key or a null
     * link.
     * 1) encounter a null link before reaching the last character of the key
     * - create nodes for each of the characters in the key not yet encountered and set the value in the last one ot
     * value ot be associated with the key
     * <p>
     * 2) encounter the last character of the key before reaching a null link.
     * - set the node's value to the value ot be associated with the key (whether of not that value is null)
     */
    private Node put(Node x, String key, Value val, int d) {
        // change value associated with key if in sutrie rooted at x
        if (x == null) x = new Node();
        if (d == key.length()) {
            n++;
            x.val = val;
            return x;
        }
        char c = key.charAt(d); // use dth key char to identify subtrie
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    // value paired with key (null if key is absent)
    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    /**
     * Each node in the trie has a link corresponding to each possible string character.
     * We start at the root, then follow the link associated with the first character in the key; from that node we
     * follow the link associated with the second character in the key and so forth, until reaching the last character
     * of the kye or a null link. One of the following three conditions holds.
     * 1) The value at the node corresponding to the last character in the key is not null.
     * search hit - the value associated with the key is the value in the node corresponding to its last character.
     * 2) The value at the node corresponding to the last character in the key is null.
     * search miss - the key is not in the table
     * 3) The search terminated with a null link.
     * search miss
     */
    private Node get(Node x, String key, int d) {
        // return value associated with key in the subtrie rooted at x
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d); // use dth key char to identify subtrie
        return get(x.next[c], key, d + 1);
    }


    // is the table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of key-value pairs
    public int size() {
        return n;
    }

    // all the keys having s as a prefix
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> q = new Queue<String>();
        collect(get(root, prefix, 0), prefix, q);
        return q;
    }

    /**
     * the second argument (prefix) is the string associated with the node.
     * To visit a node, we add its associated string to the queue if its value is not null,
     * then visit (recursively) all the nodes in its array of links, one for each possible character.
     */
    private void collect(Node x, String prefix, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(prefix);
        for (char c = 0; c < R; c++)
            collect(x.next[c], prefix + c, q);
    }

    // all the keys in the table
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    // all the keys that match s (where . matches any character)
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> q = new Queue<String>();
        collect(root, "", pattern, q);
        return q;
    }

    private void collect(Node x, String prefix, String pattern, Queue<String> q) {
        int d = prefix.length();
        if (x == null) return;
        if (d == pattern.length() && x.val != null) q.enqueue(prefix);
        if (d == pattern.length()) return;

        char next = pattern.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c)
                collect(x.next[c], prefix + c, pattern, q);
        }
    }

    // the longest key that is a prefix of s
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    /**
     * use a recursive method like get() that keeps track of the length of the longest key found on the search path (by
     * passing it as a parameter to the recursive method, updating the value whenever a node with non-null value is
     * encountered). The search ends when the end of the string or a null link is encountered, which comes first.
     */
    private int search(Node x, String s, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == s.length()) return length;
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    // remove key (and its value)
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    /**
     * recursive calls for a node x,
     * 1) remove node (return null link)
     * return null if value and all of the links in a node are null
     * 2) do not remove node (return link to node)
     * non-null value
     */
    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        if (x.val != null) return x;

        for (char c = 0; c < R; c++) {
            if (x.next[c] != null)
                return x;
        }
        return null;
    }

    // is there a value paired with key?
    public boolean contains(String key) {
        return get(key) != null;
    }
}
