## Overview
Fundamental data types involve collections of objects: the bag, the queue and the stack.

## APIs for bags, queues, and stacks

## Bag

## Queue

## Stack

## Steque

## Deque
A double-ended queue (which is like a stack or a queue but supports adding and removing items at both ends) of generic items.

API for a generic double-ended queue:
```
public class Deque<Item> implements Iterable <Item>

    public Deque() // create an empty deque

    public boolean isEmpty() // is the deque empty?

    public int size() // number of items in the deque

    public void pushLeft(Item item) // add an item to the left end

    public void pushRight(Item item) // // add an item to the right end

    public Item popleft() // remove an item from the left end

    public Item popRight() // // remove an item from the right end
```