package strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TrieTest {
    @Test
    public void testTrie() {
        // build Trie
        Trie t = new Trie();
        t.insert("app");
        t.insert("apple");
        t.insert("beer");
        t.insert("add");
        t.insert("jam");
        t.insert("rental");

        assertTrue(t.search("apple"));
        assertFalse(t.search("apps"));
        assertTrue(t.search("app"));
        assertFalse(t.search("ad"));
        assertFalse(t.search("applepie"));
        assertFalse(t.search("rest"));
        assertFalse(t.search("jan"));
        assertFalse(t.search("rent"));
        assertTrue(t.search("beer"));
        assertTrue(t.search("jam"));

        assertFalse(t.startsWith("apps"));
        assertTrue(t.startsWith("app"));
        assertTrue(t.startsWith("ad"));
        assertFalse(t.startsWith("applepie"));
        assertFalse(t.startsWith("rest"));
        assertFalse(t.startsWith("jan"));
        assertTrue(t.startsWith("rent"));
        assertTrue(t.startsWith("beer"));
        assertTrue(t.startsWith("jam"));
    }
}