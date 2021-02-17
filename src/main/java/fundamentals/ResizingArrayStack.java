package fundamentals;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code ResizingArrayStack} class represents a last-in-first-out (LIFO) stack of generic items.
 * <p>
 * This implementation uses a resizing array, which double the underlying array when it is full and halves the underlying array when it is one-quarter full.
 * <p>
 * (under the root directory)
 * Compilation:  javac-algs4 ./src/main/java/fundamentals/ResizingArrayStack.java
 * Execution:    java-algs4 -cp /usr/local/lift/lib/algs4.jar:./src/main/java/ fundamentals.ResizingArrayStack < input.txt
 * <p>
 * Sample input file:
 * - alphabets.txt
 *
 * @param <Item> the generic type of an item in this stack
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] a;   // array of items
    private int n;  // number of elements on stack

    public ResizingArrayStack() {
        a = (Item[]) new Object[1];
    }

    /**
     * Check wether the stack is empty
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    public int size() {
        return n;
    }

    /**
     * Resize the underlying array holding the elements
     *
     * @param capacity
     */
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }

    /**
     * Adds the item to this stack.
     *
     * @param item
     */
    public void push(Item item) {
        // double the underlying array when it is full
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    /**
     * Returns the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return a[n - 1];
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = a[--n];
        a[n] = null; // to void loitering
        // halves the underlying array when it is one-quarter full
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = n - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[i--];
        }
    }

    /**
     * Unit tests the {@code Stack} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            System.out.println("Reading input [" + item + "]");
            if (!item.equals("-")) {
                System.out.println("> push item: " + item);
                stack.push(item);
            } else {
                System.out.println("> pop item: " + stack.pop());
            }

            System.out.println("Current stack contains:");
            Iterator<String> iterator = stack.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                System.out.print(element + " ");
            }
            System.out.println("\n");
        }
    }

}