package fundamentals;

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code ResizingArrayQueue} class represents a first-in-first-out (FIFO) queue of generic items.
 *
 * This implementation uses a resizing array, which double the underlying array when it is full and halves the underlying array when it is one-quarter full.
 *
 * (under the root directory)
 * Compilation:  javac-algs4 ./src/main/java/fundamentals/ResizingArrayQueue.java
 * Execution:    java-algs4 -cp /usr/local/lift/lib/algs4.jar:./src/main/java/ fundamentals.ResizingArrayQueue < input.txt
 * Sample input:
 * % echo "a b c d d - f - - g - - - h"  >  alphabet.txt
 *
 * @param <Item> the generic type of an item in this queue
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {

    private Item[] q;   // array of items
    private int n;      // number of elements on queue
    private int first;  // index of first element of queue
    private int last;   // index of next available slot

    public ResizingArrayQueue() {
        q = (Item[]) new Object[1];
        n = 0;
        first = 0;
        last = 0;
    }

    /**
     * Check wether the queue is empty
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in the queue.
     * @return the number of items in the queue
     */
    public int size() {
        return n;
    }

    /**
     * Resize the underlying array holding the elements
     * @param capacity
     */
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            // copy elements to resized wrap (including element in wrapped around index)
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last = n;
    }

    /**
     * Adds the item to this queue.
     * @param item
     */
    public void enqueue(Item item) {
        // double the underlying array when it is full
        if (n == q.length) {
            resize(2*q.length);
        }
        q[last++] = item;
        // wrap around to utilize existing array before resize
        if(last == q.length) {
            last = 0;
        }
        n++;
    }

    /**
     * Removes and returns the item most recently added to this queue.
     * @return the item most recently added
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = q[first];
        q[first] = null;    // avoid loitering
        n--;
        first++;
        // wrap around to utilize existing array before resize
        if(first == q.length) {
            first = 0;
        }
        // halves the underlying array when it is one-quarter full
        if (n > 0 && n == q.length/4) {
            resize(q.length/2);
        }
        return item;
    }

    /**
     * Returns an iterator to this queue that iterates through the items in FIFO order.
     * @return an iterator to this queue that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i;

        public ArrayIterator() {
            i = 0;
        }

        public boolean hasNext() {
            return i < n;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = q[(first + i) % q.length];
            i++;
            return item;
        }
    }

    /**
     * Unit tests the {@code Queue} data type.
     * @param  args the command-line arguments
     */
    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            System.out.println("Reading input [" + item + "]");
            if(!item.equals("-")) {
                System.out.println("> enqueue item: " + item);
                queue.enqueue(item);
            } else {
                System.out.println("> dequeue item: " + queue.dequeue());
            }

            System.out.println("Current queue contains:");
            Iterator<String> iterator = queue.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                System.out.print(element + " ");
            }
            System.out.println("\n");
        }
    }

}