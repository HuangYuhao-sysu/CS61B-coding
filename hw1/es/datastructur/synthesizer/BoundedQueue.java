package es.datastructur.synthesizer;

public interface BoundedQueue<T> {
    // Return size of the buffer.
    public int capacity();

    // Return number of items currently in the buffer.
    public int fillCount();

    // Add item x to the end.
    void enqueue(T x);

    // Delete and return item from the front.
    T dequeue();

    // Return but don't delete item from the front.
    T peek();

    // Is the buffer empty.
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    // Is the buffer full.
    default boolean isFull() {
        return fillCount() == capacity();
    }

    public void clean();
}
