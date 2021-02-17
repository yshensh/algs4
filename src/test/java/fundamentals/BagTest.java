package fundamentals;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class BagTest {
    @Test
    void bagTest() {
        In in = new In("src/test/resources/alphabets.txt");
        Bag<String> bag = new Bag<>();
        while (!in.isEmpty()) {
            String item = in.readString();
            StdOut.println("Reading input [" + item + "]");
            StdOut.println("> add item: " + item);
            bag.add(item);

            StdOut.println("Current bag contains " + bag.size() + " items:");
            Iterator<String> iterator = bag.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                StdOut.print(element + " ");
            }
            StdOut.println("\n");
        }
    }
}