import edu.princeton.cs.algs4.Queue;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class TestSortAlgs {

    public static final int NUMBER = 100;

    @Test
    public void testQuickSort() {
        Queue<Integer> intQueue = new Queue<>();
        Random r = new Random();
        for (int i = 0; i < NUMBER; i += 1) {
            intQueue.enqueue(r.nextInt(NUMBER*2));
        }
        System.out.println(intQueue.toString());
        intQueue = QuickSort.quickSort(intQueue);
        System.out.println(intQueue.toString());
        assertEquals(true, isSorted(intQueue));
    }

    @Test
    public void testMergeSort() {
        Queue<Integer> intQueue = new Queue<>();
        Random r = new Random();
        for (int i = 0; i < NUMBER; i += 1) {
            intQueue.enqueue(r.nextInt(NUMBER*2));
        }
        System.out.println(intQueue.toString());
        intQueue = MergeSort.mergeSort(intQueue);
        System.out.println(intQueue.toString());
        assertEquals(true, isSorted(intQueue));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
