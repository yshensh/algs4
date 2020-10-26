package fundamentals;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Bag} class represents a bag (or multiset) of generic items. It supports insertion and iterating over the items in arbitrary order.
 *
 * This implementation uses a singly linked list with a static nested class for linked-list nodes.
 *
 * (under the root directory)
 * Compilation:  javac-algs4 ./src/main/java/fundamentals/Bag.java
 * Execution:    java-algs4 -cp /usr/local/lift/lib/algs4.jar:./src/main/java/ fundamentals.Bag < input.txt
 *
 * Sample input file:
 * - alphabets.txt
 *
 * @param <Item> the generic type of an item in this bag
 */
public class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;   // beginning of queue
    private int n;              // number of elements in bag

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty bag
     */
    public Bag() {
        first = null;
        n = 0;
    }

    /**
     * Returns true if this bag is empty
     *
     * @return {@code true} if this bag is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /** Returns the number of items in this queue.
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
    public void add(Item item) {
        // save a link to the last node
        Node<Item> oldfirst = first;
        // create a new node for the end of linked list
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
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

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Unit tests the {@code Bag} data type.
     *
     * @param args
     */
    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            System.out.println("Reading input [" + item + "]");
            System.out.println("> add item: " + item);
            bag.add(item);
        }
        System.out.println("Current bag contains " + bag.size() + " items:");
        Iterator<String> iterator = bag.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.print(element + " ");
        }
    }

}