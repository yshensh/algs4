package fundamentals;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Queue} class represents a first-in-first-out (FIFO) queue of generic items.
 * <p>
 * This implementation uses a singly linked list with a static nested class for linked-list nodes.
 */
public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;   // beginning of queue
    private Node<Item> last;    // end of queue
    private int n;              // number of elements on queue

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty queue
     */
    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue.
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this queue.
     * (Add a new to the end of a linked list)
     *
     * @param item the item to add
     */
    public void enqueue(Item item) {
        // save a link to the last node
        Node<Item> oldlast = last;
        // create a new node for the end of linked list
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        // link the new node to the end of the list
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return first.item;
    }

    /**
     * Removes and returns the item on the queue that was least recently added.
     * (Remove the first node from a linked list)
     *
     * @return the item on this queue that was least recently added
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        // save item to return
        Item item = first.item;
        // delete the first node
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}