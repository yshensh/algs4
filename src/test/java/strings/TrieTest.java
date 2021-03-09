package strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TrieTest {
    @Test
    public void testTrie() {
        // build Trie
        Trie t = new Trie();
        t.insert("apple");
        assertTrue(t.search("apple"));
        assertFalse(t.search("app"));
        assertTrue(t.startsWith("app"));
        t.insert("app");
        assertTrue(t.search("app"));
    }
}