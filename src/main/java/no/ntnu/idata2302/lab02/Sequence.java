/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab02;

import java.util.Arrays;

/**
 * Implement the Sequence ADT from Lecture 2.2
 *
 * The items in the sequence are indexed from 1 (as opposed to Java arrays that
 * are indexed from 0)
 */
public class Sequence {

    private static final int INITIAL_CAPACITY = 100;

    private int capacity;
    private int length;
    private int[] items;

    public Sequence() {
        this(INITIAL_CAPACITY, new int[]{});
    }

    public Sequence(int capacity, int[] items) {
        if (capacity < items.length) {
            throw new IllegalArgumentException("Capacity must be greater than item count");
        }
        this.capacity = capacity;
        this.length = items.length;
        this.items = new int[capacity];
        for (int i=0 ; i<items.length ; i++) {
            this.items[i] = items[i];
        }
    }

    /**
     * @return The number of items in the sequence
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @return the number of "buckets" currently allocated
     */
    int getCapacity() {
        return this.capacity;
    }

    /**
     * Return the item stored at the given index
     *
     * @param index the index of the desired item, starting at 1
     * @return the item at the given index.
     */
    public int get(int index) {
        if (index < 1 || index > length) {
            throw new IllegalArgumentException("Invalid index!");
        }
        return this.items[index - 1];
    }

    /**
     * Append the given item at the end of the sequence
     *
     * @param item the item that must be inserted
     */
    public void insert(int item, int index) {
        if (length >= capacity) {
            capacity *= 2;
            items = Arrays.copyOf(items, capacity);
        }
        for (int i = length; i >= index; i--) {
            items[i] = get(i);
        }
        items[index-1] = item;
        length = length+1;
    }

    /**
     * Remove the index at the given index
     *
     * @param index the index that must be removed.
     */
    public void remove(int index) {
        if (length < 1) {
            throw new IllegalArgumentException("Sequence is empty!");
        }
        for (int i = index-1; i < length-1; i++) {
            items[i] = get(i+2);
        }
        items[length-1] = 0;
        length -= 1;
        if (length <= capacity/4) {
            capacity /= 2;
        }
    }

    /**
     * Find a index where the given item can be found. Returns 0 if that item cannot
     * be found.
     *
     * @param item the item whose index must be found
     * @return an
     */
    public int search(int item) {
        for (int i = 0; i <= length; i++) {
            if (item == items[i]) {
                return i+1;
            }
        }
        return 0;
    }

    /**
     * Find both the smallest and the largest items in the sequence.
     *
     * @return an array of length two where the first entry is the minimum and the
     *         second the maximum
     */
    public int[] extrema() {
        int smallest = items[0];
        int max = items[0];
        for (int i = 1; i < length; i++) {
            if (get(i+1) > max) {
                max = get(i+1);
            }
            if (get(i+1) < smallest) {
                smallest = get(i+1);
            }
        }
        return new int[]{smallest, max};
    }

    /**
     * Check whether the given sequence contains any duplicate item
     *
     * @return true if the sequence has the the same items at multiple indices
     */
    public boolean hasDuplicate() {
        // TODO: Implement
        throw new RuntimeException("Not yet implemented.");
    }


    /**
     * Convert the sequence into an Java array
     */
    public int[] toArray() {
        return items;
    }

}
