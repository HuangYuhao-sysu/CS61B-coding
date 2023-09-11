package bearmaps;

import edu.princeton.cs.algs4.BST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    
    private ArrayList<Node> items;
    private HashMap<T, Integer> shadowItems;

    private class Node implements Comparable<Node> {
        T item;
        double priority;

        public Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        @Override
        public int compareTo(Node o) {
            if (o == null) return -1;
            return Double.compare(this.priority, o.priority);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) return false;
            return ((Node) o).item.equals(this.item);
        }

        @Override
        public int hashCode() {
            return this.item.hashCode();
        }
    }

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        shadowItems = new HashMap<>();
    }

    @Override
    public boolean contains(T item) {
        //return items.contains(new Node(item,0));
        return shadowItems.containsKey(item);
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) throw new IllegalArgumentException("Calls add with an existed item");
        Node add = new Node(item, priority);
        items.add(add);
        shadowItems.put(item, size()-1);
        swim(size()-1);
    }

    private int parent(int k) {
        return (k - 1) / 2;
    }

    private void swap(int index1, int index2) {
        Node Nodeidx1 = items.get(index1);
        Node Nodeidx2 = items.get(index2);
        items.set(index2, Nodeidx1);
        items.set(index1, Nodeidx2);
        shadowItems.put(Nodeidx1.item, index2);
        shadowItems.put(Nodeidx2.item, index1);
    }

    private void swim(int k) {
        if (items.get(parent(k)).compareTo(items.get(k)) > 0) {
            swap(k, parent(k));
            swim(parent(k));
        }
    }

    private int childLeft(int k) {
        return k * 2 + 1;
    }

    private int childRight(int k) {
        return k * 2 + 2;
    }

    private void sink(int k) {
        int leftIndex = childLeft(k) < size() ? childLeft(k) : k;
        int rightIndex = childRight(k) < size() ? childRight(k) : k;
        boolean mostDepth = leftIndex == k && rightIndex == k;

        if (mostDepth) return;

        int chooseIndex = items.get(leftIndex).compareTo(items.get(rightIndex)) > 0 ? rightIndex : leftIndex;
        if (items.get(k).compareTo(items.get(chooseIndex)) > 0) {
            swap(k, chooseIndex);
            sink(chooseIndex);
        }
    }

    @Override
    public T getSmallest() {
        return items.get(0).item;
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) throw new NoSuchElementException("PQ is empty");
        T returnItem = getSmallest();
        Node last = items.remove(size()-1);
        shadowItems.remove(returnItem);
        if (size() == 0) return returnItem;
        items.get(0).item = last.item;
        items.get(0).priority = last.priority;
        sink(0);

        return returnItem;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) throw new NoSuchElementException("Calls changePriority with no existed item");
        int index = indOf(item);
        items.get(index).priority = priority;
        swim(index);
        sink(index);
    }

    public int indOf(T elem) {
        return shadowItems.get(elem);
        //return items.indexOf(new Node(elem, 0));
    }
}
