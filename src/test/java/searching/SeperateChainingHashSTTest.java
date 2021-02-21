package searching;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

class SeperateChainingHashSTTest {

    @Test
    void SequentialSearchSTTest() {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
        In in = new In("src/test/resources/tinyST.txt");
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}