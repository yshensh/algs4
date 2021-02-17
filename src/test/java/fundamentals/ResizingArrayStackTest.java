package fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResizingArrayStackTest {
    private ResizingArrayStack<String> stack;

    @BeforeEach
    public void init() {
        stack = new ResizingArrayStack<>();
    }

    @Test
    public void testPush() {
        stack.push("a");
        assertEquals(stack.peek(), "a");
    }

    @Test
    public void testPop() {
        stack.push("a");
        assertEquals("a", stack.pop());
    }

    @Test
    public void testOrdering() {
        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertEquals("c", stack.pop());
        assertEquals("b", stack.pop());
        assertEquals("a", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testUnderflowStack() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        });
    }
}