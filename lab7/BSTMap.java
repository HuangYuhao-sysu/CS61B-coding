import javax.management.MBeanAttributeInfo;
import java.util.*;

public class BSTMap<Key extends Comparable<Key>, Value> implements Map61B<Key, Value> {
    private Node root; // The root of BST.

    /**
     * Node for constructing binary search tree.
     * */
    private class Node {
        private Key key; // Contains Key.
        private Value value; // Contains Value.
        private Node left; // Left Node.
        private Node right; // Right Node.
        private int size; // Number of Nodes in subtree.

        private Node parent;

        public Node(Key key, Value value, int size, Node parent) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.parent = parent;
        }
    }

    /**
     * Initialize an empty symbol table.
     * */
    public BSTMap() {};

    /**
     * Return true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Removes all the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("Calls put() with a null key.");
        if (val == null) {
            delete(key); // TODO
            return;
        }
        root = put(root, key, val, null);
    }

    private Node put(Node x, Key key, Value val, Node p) {
        if (x == null) {
            return new Node(key, val, 1, p);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val, x);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val, x);
        } else {
            x.value = val;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Call get() with a null key.");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x.value;
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return get(x.right, key);
        }
    }

    private Node find(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else if (cmp < 0) {
            return find(x.left, key);
        } else {
            return find(x.right, key);
        }
    }

    @Override
    public boolean containsKey(Key key) {
        return key != null && find(root, key) != null;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    @Override
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * */
    @Override
    public Value remove(Key key) {
        Node currNode = find(root, key);
        Value returnValue;
        if (currNode == null) {
            return null;
        } else {
            returnValue = currNode.value;
            int childrenNumber = howManyChildren(currNode);
            switch (childrenNumber) {
                // No children. Just delete the parent's pointer.
                case 0: {
                    if (currNode == root) {
                        root = null;
                    } else if (currNode.parent.left == currNode) {
                        currNode.parent.left = null;
                        updateParentsSize(currNode.parent);
                    } else if (currNode.parent.right == currNode) {
                        currNode.parent.right = null;
                        updateParentsSize(currNode.parent);
                    }
                    break;
                }
                // One child. Move parent's pointer to the child.
                case 1: {
                    if (currNode == root) {
                        root = currNode.left != null ? currNode.left : currNode.right;
                    } else if (currNode.left != null) {
                        if (currNode.parent.left == currNode) {
                            currNode.parent.left = currNode.left;
                        } else if (currNode.parent.right == currNode) {
                            currNode.parent.right = currNode.left;
                        }
                        updateParentsSize(currNode.parent);
                    } else if (currNode.right != null) {
                        if (currNode.parent.left == currNode) {
                            currNode.parent.left = currNode.right;
                        } else if (currNode.parent.right == currNode) {
                            currNode.parent.right = currNode.right;
                        }
                        updateParentsSize(currNode.parent);
                    }
                    break;
                }
                // Two children. Find the predecessor, move it to the deleted position.
                case 2: {
                    Node predecessor = findPredecessor(currNode);
                    Key newKey = predecessor.key;
                    Value newValue = predecessor.value;
                    remove(predecessor.key);
                    currNode.key = newKey;
                    currNode.value = newValue;
                    break;
                }
            }
            return returnValue;
        }
    }

    private void updateParentsSize(Node x) {
        if (x == root) {
            x.size -= 1;
        } else {
            x.size -= 1;
            updateParentsSize(x.parent);
        }
    }

    private int howManyChildren(Node x) {
        if (x == null) throw new IllegalArgumentException("Call howManyChildren() with a null Node.");
        int number = 0;
        if (x.left != null) number += 1;
        if (x.right != null) number += 1;
        return number;
    }

    private Node findPredecessor(Node x) {
        Node currNode;
        if (x == null || x.left == null) {
            return null;
        }
        currNode = x.left;
        while (currNode.right != null) {
            currNode = currNode.right;
        }
        return currNode;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value.
     * */
    @Override
    public Value remove(Key key, Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Key> iterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a Set view of the keys contained in this map.
     * @return
     */
    @Override
    public Set<Key> keySet() {
        return keySet(root);
    }

    private Set<Key> keySet(Node x) {
        Set<Key> returnSet = new HashSet<>();
        if (x == null) {
            return returnSet;
        } else {
            returnSet.add(x.key);
            returnSet.addAll(keySet(x.left));
            returnSet.addAll(keySet(x.right));
            return returnSet;
        }
    }

    private void delete(Key key) {
        return;
    }

    public void printInOrder() {
        System.out.println(printInOrder(root));
    }

    private ArrayList<Key> printInOrder(Node x) {
        if (x == null) {
            return new ArrayList<>();
        } else {
            ArrayList<Key> leftList = printInOrder(x.left);
            ArrayList<Key> rightList = printInOrder(x.right);
            ArrayList<Key> returnList = new ArrayList<>();
            returnList.addAll(leftList);
            returnList.add(x.key);
            returnList.addAll(rightList);
            return returnList;
        }
    }
}
