package fundamentals;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class StackTest {
    @Test
    void stackTest() {
        In in = new In("src/test/resources/alphabets.txt");
        Stack<String> stack = new Stack<>();

        while (!in.isEmpty()) {
            String item = in.readString();
            StdOut.println("Reading input [" + item + "]");
            if (!item.equals("-")) {
                StdOut.println("> push item: " + item);
                stack.push(item);
            } else {
                StdOut.println("> pop item: " + stack.pop());
            }

            System.out.println("Current stack contains:");
            Iterator<String> iterator = stack.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                StdOut.print(element + " ");
            }
            StdOut.println("\n");
        }
    }
}