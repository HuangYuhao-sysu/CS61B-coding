public interface Deque<Item> {
    void addFirst(Item item);
    void addLast(Item item);
    Item get(int index);
    int size();
    Item removeFirst();
    Item removeLast();
    boolean isEmpty();
    void printDeque();
}
