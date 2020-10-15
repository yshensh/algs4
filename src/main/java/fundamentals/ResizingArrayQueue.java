package fundamentals; /******************************************************************************
 *  Compilation:  javac ResizingArrayQueue.java
 *  Execution:    java ResizingArrayQueue < input.txt
 *
 *  Queue implementation with a resizing array.
 *
 *  % echo "a b c d d - f - - g - - - h"  >  alphabet.txt
 *
 *  % java ResizingArrayQueue < alphabet.txt
 *  to be not that or be (2 left on queue)
 *
 ******************************************************************************/

import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code ResizingArrayQueue} class represents a last-in-first-out (LIFO) queue
 * of generic items.
 *
 * This implementation uses a resizing array, which double the underlying array when it is full and halves the underlying array when it is one-quarter full.
 *
 * @param <Item> the generic type of an item in this queue
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {

    private Item[] a;   // array of items
    private int n;  // number of elements on queue

    public ResizingArrayQueue() {
        a = (Item[]) new Object[1];
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
            copy[i] = a[i];
        }
        a = copy;
    }

    /**
     * Adds the item to this queue.
     * @param item
     */
    public void enqueue(Item item) {
        // double the underlying array when it is full
        if (n == a.length) {
            resize(2*a.length);
        }
        a[n++] = item;
    }

    /**
     * Removes and returns the item most recently added to this queue.
     * @return the item most recently added
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = a[--n];
        a[n] = null; // to void loitering
        // halves the underlying array when it is one-quarter full
        if (n > 0 && n == a.length/4) {
            resize(a.length/2);
        }
        return item;
    }

    /**
     * Returns an iterator to this queue that iterates through the items in FIFO order.
     * @return an iterator to this queue that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = n-1;
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

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests the {@code Queue} data type.
     * @param  args the command-line arguments
     */
    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String item = scanner.next();
            System.out.println("Reading input [" + item + "]");
            if(!item.equals("-")) {
                System.out.println("> push item: " + item);
                queue.enqueue(item);
            } else {
                System.out.println("> pop item: " + queue.dequeue());
            }

            System.out.println("Current queue contains:");
            Iterator iterator = queue.iterator();
            while (iterator.hasNext()) {
                String element = (String) iterator.next();
                System.out.print(element + " ");
            }
            System.out.println("\n");
        }
    }

}