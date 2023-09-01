package hw2;

import static org.junit.Assert.*;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int number;
    private int oceanIndexHead;
    private int oceanIndexTail;
    private int floorIndexHead;
    private int floorIndexTail;
    private boolean[] openIndex;
    private WeightedQuickUnionUF world;
    /**
    * Constructor, create N-by-N grid, with all sites initially blocked.
    * */
    public Percolation(int N) {
        // Initial a world N*N. And another 2 N blocks created to represented ocean and floor, on the top and bottom,
        // respectively.
        // o o o o o -> ocean union (0 - N-1)
        // x x x x x -> no union    (N - 2N-1)
        // x x x x x
        // x x x x x
        // x x x x x
        // x x x x x ... no union   (N*(N-1) - N*N-1)
        // f f f f f -> floor union (N*N - N*N+N-1)
        number = N;
        oceanIndexHead = 0;
        oceanIndexTail = oceanIndexHead + number - 1;
        floorIndexHead = number*(number + 1);
        floorIndexTail = floorIndexHead + number - 1;
        openIndex = new boolean[number*(number+2)];
        for (int i = 0; i < number*(number+2); i += 1) {
            openIndex[i] = false; // Close site
        }
        for (int i = 0; i < number; i += 1) {
            openIndex[i] = true; // Open ocean site
        }
        for (int i = number*(number + 1); i < number*(number+2); i += 1) {
            openIndex[i] = true; // Open floor site
        }
        world = new WeightedQuickUnionUF(number*(number+2));
        // ocean union
        for (int i = oceanIndexHead; i < oceanIndexTail; i += 1) {
            world.union(i,i+1);
        }
        // floor union
        for (int i = floorIndexHead; i < floorIndexTail; i += 1) {
            world.union(i, i+1);
        }
    }

    /**
     * Open the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        // Open it and connected to open neighbor.
        int index = number*(row + 1) + col;
        if (!checkIndex(index)) throw new IllegalArgumentException();
        int upIndex = index - number;
        int downIndex = index + number;
        int leftIndex = index - 1;
        int rightIndex = index + 1;
        openIndex[index] = true;
        if (openIndex[upIndex]) world.union(index, upIndex);
        if (openIndex[downIndex]) world.union(index, downIndex);
        if (openIndex[leftIndex]) world.union(index, leftIndex);
        if (openIndex[rightIndex]) world.union(index, rightIndex);
    }

    /**
     * Is the site (row, col) open?
     * */
    public boolean isOpen(int row, int col) {
        int index = number*(row + 1) + col;
        if (!checkIndex(index)) throw new IllegalArgumentException();
        return openIndex[index];
    }

    /**
     * is the site (row, col) full?
     * */
    public boolean isFull(int row, int col) {
        int index = number*(row + 1) + col;
        if (!checkIndex(index)) throw new IllegalArgumentException();
        return world.connected(index, oceanIndexHead);
    }


    /**
     * Return true if index is valid.
     * */
    private boolean checkIndex(int index) {
        return (oceanIndexTail < index) && (index < floorIndexHead);
    }

    /**
     * number of open sites。
     */
    public int numberOfOpenSites() {
        int returnNumber = 0;
        for (int i = oceanIndexTail + 1; i < floorIndexHead; i += 1) {
            returnNumber = openIndex[i] ? returnNumber + 1 : returnNumber;
        }
        return returnNumber;
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return world.connected(oceanIndexHead, floorIndexHead);
    }

    /**
     * Use for unit testing
     */
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(1, 2);
        p.open(2,2);
        assertEquals(2,p.numberOfOpenSites());
        assertEquals(false, p.isOpen(3,2));
        assertEquals(true, p.isOpen(2,2));
        assertEquals(false, p.isFull(1,2));
        p.open(0,2);
        assertEquals(true, p.isFull(0,2));
        assertEquals(true, p.isFull(1,2));
        assertEquals(true, p.isFull(2,2));
        assertEquals(3,p.numberOfOpenSites());
        assertEquals(false, p.isOpen(0,1));
        p.open(4,2);
        assertEquals(false,p.percolates());
        p.open(3,2);
        assertEquals(true,p.percolates());
    }
}
