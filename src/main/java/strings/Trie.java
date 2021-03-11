package strings;

import java.util.HashSet;
import java.util.Set;

/**
 * implementation of a multiway trie
 */
public class Trie {

    private static final int R = 256;

    private Node root;

    private static class Node {
        // "value" may be null or true with one of the string keys in the symbol table
        private Boolean value;
        // "links" are either null or references to other nodes
        private Node[] next = new Node[R];
    }

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        Node root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        root = put(root, word, 0);
    }

    private Node put(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.value = true;
            return x;
        }
        char c = key.charAt(d);
        // set to false if it is null
        x.value = x.value != null && x.value;
        x.next[c] = put(x.next[c], key, d + 1);
        return x;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node x = get(root, word, 0);
        if (x == null) return false;
        return x.value;
    }

    private Node get(Node x, String key, int d) {
        // return value associated with key in the subtrie rooted at x
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Set<String> s = new HashSet<String>();
        collect(get(root, prefix, 0), prefix, s);
        return s.contains(prefix);
    }

    private void collect(Node x, String prefix, Set<String> s) {
        if (x == null) return;
        if (x.value != null) s.add(prefix);
        for (char c = 0; c < R; c++)
            collect(x.next[c], prefix + c, s);
    }

}
