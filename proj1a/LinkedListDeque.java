public class LinkedListDeque<T> {
    public class StuffNode {
        public StuffNode front;
        public T item;
        public StuffNode next;

        public StuffNode(StuffNode f, T i, StuffNode n) {
            front = f;
            item = i;
            next = n;
        }
    }

    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.front = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = new StuffNode(sentinel, x, sentinel);
        sentinel.front = sentinel.next;
        size = 1;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new StuffNode(null, null, null);
        sentinel.front = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }
    }

    public void addFirst(T item) {
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.next.front = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.front = new StuffNode(sentinel.front, item, sentinel);
        sentinel.front.front.next = sentinel.front;
        size += 1;
    }

    public boolean isEmpty() {
        if (sentinel.front == sentinel && sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item);
            System.out.print(' ');
            ptr = ptr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.front = sentinel;
        size -= 1;
        return item;
    }

    public T removeLast() {
        T item = sentinel.front.item;
        sentinel.front = sentinel.front.front;
        sentinel.front.next = sentinel;
        size -= 1;
        return item;
    }

    public T get(int index) {
        if (index + 1 > size || index < 0) {
            return null;
        }

        StuffNode getNode = sentinel.next;

        for (int i = 0; i < index; i++) {
            getNode = getNode.next;
        }
        return getNode.item;
    }

    private T getRecursiveHelper(StuffNode curr, int index) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursiveHelper(curr.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index + 1 > size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
}
