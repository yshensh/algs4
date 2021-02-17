package fundamentals;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code ResizingArrayStack} class represents a last-in-first-out (LIFO) stack of generic items.
 * <p>
 * This implementation uses a singly linked list with a static nested class for linked-list nodes.
 */
public class Stack<Item> implements Iterable<Item> {

    private Node<Item> first;   // top of stack
    private int n;              // number of elements on stack

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty stack.
     */
    public Stack() {
        first = null;
        n = 0;
    }

    /**
     * Check wether the stack is empty
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
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
     * Adds the item to this stack.
     * (Add a new to the end of a linked list)
     *
     * @param item
     */
    public void push(Item item) {
        // save a link to the list
        Node<Item> oldfirst = first;
        // create a new node for the beginning
        first = new Node<Item>();
        // set the instance variables in the new node
        first.item = item;
        first.next = oldfirst;
        // increase stack size
        n++;
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
        return first.item;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     * (Remove the first node from a linked list)
     *
     * @return the item most recently added
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        // save item to return
        Item item = first.item;
        // delete the first node
        first = first.next;
        n--;
        return item;
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
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