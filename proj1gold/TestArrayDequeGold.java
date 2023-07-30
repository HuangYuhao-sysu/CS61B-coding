import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
public class TestArrayDequeGold {
    static StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
    static ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

    public static void initialDeque() {
        for (int j = 0; j < 10; j += 1) {
            int addFirstItem = StdRandom.uniform(10);
            int addLastItem = StdRandom.uniform(10);
            sad.addFirst(addFirstItem);
            ads.addFirst(addFirstItem);
            sad.addLast(addLastItem);
            ads.addLast(addLastItem);
        }
    }

    @Test
    public static void main(String[] args) {
        initialDeque();
        for (int i = 0; i < 9; i += 1) {
            sad.removeFirst();
            ads.removeFirst();
            sad.removeLast();
            ads.removeLast();
        }
        sad.printDeque();
        ads.printDeque();
    }

}
