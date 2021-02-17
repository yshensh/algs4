package fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The CircularQueue class represents a first-in-first-out (FIFO) queue of generic items.
 * <p>
 * This implementation uses a ring buffer that uses a fixed-size that can be pre-allocated
 * upfront and allows an efficient memory access pattern.
 * <p>
 * When the write position has not wrapped around, the used space is between the write
 * position and the read position. All the rest is free space.
 */
public class CircularQueue<Item> implements Iterable<Item> {
    private Item[] buffer;  // ring buffer
    private int reader;     // reader pointer (beginning of queue)
    private int writer;     // writer pointer (end of queue)
    private int capacity;   // capacity of the ring buffer
    private int n;          // number of elements

    // constructor
    public CircularQueue(int capacity) {
        this.buffer = (Item[]) new Object[capacity];
        this.capacity = capacity;
        this.reader = 0;
        this.writer = 0;
    }

    // Returns true if this ring buffer is empty
    public boolean isEmpty() {
        return n == 0;
    }

    // Returns true if this ring buffer is full
    public boolean isFull() {
        return n == capacity;
    }

    // Returns the number of items in this queue.
    public int size() {
        return n;
    }

    // Adds the item to this queue.
    public void enqueue(Item item) {
        if (isFull()) {
            throw new NoSuchElementException("Queue overflow");
        }
        buffer[writer] = item;
        writer = (writer + 1) % capacity;   // increment writer pointer and wrap around if applicable
        n++;
    }

    // Returns the item least recently added to this queue.
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return buffer[reader];
    }

    // Removes and returns the item on the queue that was least recently added.
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        // save item to return
        Item item = buffer[reader];
        // delete the item on the buffer
        buffer[reader] = null;
        reader = (reader + 1) % capacity;   // increment reader pointer and wrap around if applicable
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new CircularQueueIterator();
    }

    public class CircularQueueIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < capacity;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return buffer[i++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}