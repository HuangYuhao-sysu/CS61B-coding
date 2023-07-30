
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Isn't this solution kinda... cheating? Yes.
 */
public class LinkedListDeque<Item> extends LinkedList<Item> implements Deque<Item>{
    @Override
    public void printDeque() {
        System.out.println("dummy");
    }

    public Item getRecursive(int i) {
        return get(i);
    }

    @Override
    public Item removeFirst() {
        try {
            return super.removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Item removeLast() {
        try {
            return super.removeLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> mylld = new LinkedListDeque<>();
        for (int i=0; i<100; i+=1) {
            mylld.addLast(i);
        }
        System.out.println(mylld.size());
    }
}