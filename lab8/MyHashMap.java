
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.

 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private ArrayList<Node>[] buckets;
    // You should probably define some more!
    private static final int INIT_SIZE = 4;
    private static final double INIT_LF = 0.75;
    private int tableSize;      // Number of buckets.
    private int number;         // Total number of key-value pairs.
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        this(INIT_SIZE, INIT_LF);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, INIT_LF);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.tableSize = initialSize;
        this.number = 0;
        this.loadFactor = maxLoad;
        buckets = createTable(tableSize);
        for (int i = 0; i < initialSize; i += 1) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected ArrayList<Node> createBucket() {
        return new ArrayList<Node>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private ArrayList<Node>[] createTable(int tableSize) {
        return new ArrayList[tableSize];
    }

    // Resize hash table to have the given number of chains
    private void resize(int chains) {
        MyHashMap<K, V> tmp = new MyHashMap<K, V>(chains, loadFactor);
        for (ArrayList<Node> bucket : buckets) {
            for (Node n : bucket) {
                tmp.put(n.key, n.value);
            }
        }
        this.buckets = tmp.buckets;
        this.tableSize = tmp.tableSize;
        this.number = tmp.number;
        this.loadFactor = tmp.loadFactor;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    @Override
    public void clear() {
        MyHashMap<K, V> tmp = new MyHashMap<K, V>(this.tableSize, this.loadFactor);
        this.buckets = tmp.buckets;
        this.number = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        return getNode(key) == null ? null : getNode(key).value;
    }

    @Override
    public int size() {
        return number;
    }

    @Override
    public void put(K key, V value) {
        Node n = getNode(key);
        if (n == null) {
            n = new Node(key, value);
            int index = hash(key);
            buckets[index].add(n);
            number += 1;
            if ((double) number / tableSize >= loadFactor) resize(tableSize*2);
        } else {
            n.value = value;
        }
    }

    private Node getNode(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null.");
        int index = hash(key);
        for (Node n : buckets[index]) {
            if (n.key.equals(key)) return n;
        }
        return null;
    }

    @Override
    public HashSet<K> keySet() {
        HashSet<K> keySet = new HashSet<K>();
        for (ArrayList<Node> bucket : buckets) {
            for (Node n : bucket) {
                keySet.add(n.key);
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        return remove(key, null, true);
    }

    @Override
    public V remove(K key, V value) {
        return remove(key, value, false);
    }

    private V remove(K key, V value, boolean force) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null.");
        int index = hash(key);
        Node n = getNode(key);
        V returnValue = null;
        if (n != null) {
            returnValue = n.value;
            if ((force == true) || (force == false && returnValue == value)) {
                buckets[index].remove(n);
                number -= 1;
            }
        }
        return returnValue;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator<K>();
    }

    public class MyHashMapIterator<K> implements Iterator<K> {

        private int bucketPos;
        private int NodePos;

        public boolean hasNext() {
            return bucketPos < buckets.length && NodePos < buckets[bucketPos].size();
        }

        public K next() {
            K returnKey;
            if (NodePos < buckets[bucketPos].size()) {
                returnKey = (K) buckets[bucketPos].get(NodePos).key;
                NodePos += 1;
            } else {
                bucketPos += 1;
                NodePos = 0;
                returnKey = next();
            }
            return returnKey;
        }
    }
}
