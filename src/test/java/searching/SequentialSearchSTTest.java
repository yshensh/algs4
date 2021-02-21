package searching;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

class SequentialSearchSTTest {

    @Test
    void SequentialSearchSTTest() {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        In in = new In("src/test/resources/tinyST.txt");
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}