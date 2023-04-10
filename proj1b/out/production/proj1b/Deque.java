public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    T getFirst();
    T getLast();
    T get(int index);
    int size();
    T removeFirst();
    T removeLast();
    boolean isEmpty();
    void printDeque()
}
