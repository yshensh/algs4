package fundamentals;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueueTest {
    private Queue<String> queue;

    @BeforeEach
    public void init() {
        queue = new Queue<>();
    }

    @Test
    public void testEnqueue() {
        queue.enqueue("a");
        assertEquals(queue.peek(), "a");
    }

    @Test
    public void testDequeue() {
        queue.enqueue("a");
        assertEquals("a", queue.dequeue());
    }

    @Test
    public void testOrdering() {
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        assertEquals("a", queue.dequeue());
        assertEquals("b", queue.dequeue());
        assertEquals("c", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testUnderflowQueue() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    void queueTest() {
        In in = new In("src/test/resources/alphabets.txt");
        Queue<String> queue = new Queue<>();

        while (!in.isEmpty()) {
            String item = in.readString();
            StdOut.println("Reading input [" + item + "]");
            if (!item.equals("-")) {
                StdOut.println("> push item: " + item);
                queue.enqueue(item);
            } else {
                StdOut.println("> pop item: " + queue.dequeue());
            }

            System.out.println("Current stack contains:");
            Iterator<String> iterator = queue.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                StdOut.print(element + " ");
            }
            StdOut.println("\n");
        }
    }
}