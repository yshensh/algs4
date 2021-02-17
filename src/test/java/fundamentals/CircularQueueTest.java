package fundamentals;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class CircularQueueTest {
    @Test
    void queueTest() {
        In in = new In("src/test/resources/alphabets.txt");
        CircularQueue<String> queue = new CircularQueue<>(5);

        while (!in.isEmpty()) {
            String item = in.readString();
            StdOut.println("Reading input [" + item + "]");
            if (!item.equals("-")) {
                StdOut.println("> enqueue item: " + item);
                queue.enqueue(item);
            } else {
                StdOut.println("> dequeue item: " + queue.dequeue());
            }

            System.out.println("Current queue contains:");
            Iterator<String> iterator = queue.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                StdOut.print(element + " ");
            }
            StdOut.println("\n");
        }
    }
}