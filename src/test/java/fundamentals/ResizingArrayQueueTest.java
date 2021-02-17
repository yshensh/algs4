package fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResizingArrayQueueTest {
    private ResizingArrayQueue<String> queue;

    @BeforeEach
    public void init() {
        queue = new ResizingArrayQueue<>();
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
}