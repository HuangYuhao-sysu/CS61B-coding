import org.junit.Test;

import java.util.*;

/** Iterates over every Kth element of the IntList given to the constructor.
 * For example, if L is an IntList containing elements
 * [0, 1, 2, 3, 4, 5, 6, 7] with K = 2, then
 * for (Iterator<Integer> p = new KthIntList(L, 2); p.hasNext(); ) {
 * System.out.println(p.next());
 * }
 * would print get 0, 2, 4, 6. */
public class KthIntList implements Iterator<Integer> {
    public int k;
    private IntList curList;
    private boolean hasNext;

    public KthIntList(IntList I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }

    /** Returns true iff there is a next Kth element. Do not modify. */
    public boolean hasNext() {
        return this.hasNext;
    }

    /** Returns the next Kth element of the IntList given in the constructor.
    * Returns the 0th element first. Throws a NoSuchElementException if
    * there are no Integers available to return. */
    public Integer next() {
        if (curList == null) {
            throw new NoSuchElementException();
        }
        Integer toReturn = curList.head;
        for (int i = 0; i < k && curList != null; i++) {
            curList = curList.tail;
        }
        hasNext = (curList != null);
        return toReturn;
    }

    @Test
    public void main(String[] args) {
        Integer[] i = {0,1,2,3,4,5,6,7};
        List<Integer> L = new LinkedList<Integer>(Arrays.asList(i));
        for (int item : L) {
            System.out.println(item);
        }

        for (Iterator<Integer> p = new KthIntList(L, 2); p.hasNext(); ) {
            System.out.println(p.next());
        }
    }
}