public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int front;
    private int next;

    /** Create an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        front = 3;
        next = 4;
    }

    /** Deep copy a new ArrayDeque. */
    public ArrayDeque(ArrayDeque<Item> other) {
        items = (Item[]) new Object[other.items.length];
        System.arraycopy(other.items, 0 , items, 0, other.items.length);
        size = other.size;
        front = other.front;
        next = other.next;
    }

    /** Grows the underlying array to the target capacity towards left.
     *  Also arrange the new front and next pointer.
     */
    private void resizeLeftGrow(int capacity, int front, int next) {
        Item[] newItems = (Item[]) new Object[capacity];
        System.arraycopy(items, 0 , newItems, capacity - size, size);
        front = front + capacity - size;
        next += capacity - size;
        items = newItems;
    }

    /** Grows the underlying array to the target capacity towards right.
     *  Also arrange the new front and next pointer.
     */
    private void resizeRightGrow(int capacity, int front, int next) {
        Item[] newItems = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    /** Shrink the underlying array to the target capacity.
     * Also arrange the new front and next pinter.
     */
    public void resizeShrink(int capacity, int front, int next) {
        Item[] newItems = (Item[]) new Object[capacity];
        System.arraycopy(items, front + 1, newItems, (capacity - size) / 2, size);
        front = (capacity - size) / 2 - 1;
        next = front + size + 1;
        items = newItems;
    }
    
    /** Insert x to the head of list. */
    public void addFirst(Item x) {
        if (front == -1) {
            this.resizeLeftGrow(2 * size, front, next);
        }
        items[front] = x;
        size += 1;
        front -= 1;
    }

    /** Insert x to the back of list. */
    public void addLast(Item x) {
        if (next == items.length + 1) {
            this.resizeRightGrow(2 * size, front, next);
        }
        items[next] = x;
        size += 1;
        next += 1;
    }

    /** Get item front the head. */
    public Item getFirst() {
        if (size == 0) {
            return null;
        }
        return items[front + 1];
    }

    /** Get item from the back. */
    public Item getLast() {
        if (size == 0) {
            return null;
        }
        return items[next - 1];
    }

    /** Get item according to the index. */
    public Item get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            return null;
        }
        return items[front + index + 1];
    }

    /** Return size of list. */
    public int size() {
        return size;
    }

    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size < items.length / 4) {
            resizeShrink(2 * size, front, next);
        }
        Item temp = items[front + 1];
        items[front + 1] = null;
        front += 1;
        size -= 1;
        return temp;
    }

    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        if (size < items.length / 4) {
            resizeShrink(2 * size, front, next);
        }
        Item temp = items[next - 1];
        items[next - 1] = null;
        next -= 1;
        size -= 1;
        return temp;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        for (int i = front + 1; i < next; i++) {
            System.out.print(items[i]);
            System.out.print(' ');
        }
        System.out.println();
    }
}
