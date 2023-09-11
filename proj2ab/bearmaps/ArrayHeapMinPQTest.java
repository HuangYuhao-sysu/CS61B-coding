package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    @Test
    public void naiveMinPQTest() {
        ExtrinsicMinPQ<Integer> pq = new NaiveMinPQ<Integer>();
        for (int i = 0; i < 100; i += 1) {
            pq.add(i, 99-i);
        }
        for (int i = 0; i < 100; i += 1) {
            assertEquals( (int) pq.removeSmallest(), 99-i);
        }
    }

    @Test
    public void MyMinPQTest1() {
        ExtrinsicMinPQ<Integer> pq = new ArrayHeapMinPQ<Integer>();
        for (int i = 0; i < 10; i += 1) {
            pq.add(i, i);
        }
        for (int i = 0; i < 10; i += 1) {
            assertEquals( (int) pq.removeSmallest(), i);
        }
    }

    @Test
    public void MyMinPQTest2() {
        ExtrinsicMinPQ<Integer> pq = new ArrayHeapMinPQ<Integer>();
        for (int i = 0; i < 100; i += 1) {
            pq.add(i, i);
        }
        for (int i = 0; i < 100; i += 1) {
            pq.changePriority(i, 99-i);
        }
        for (int i = 0; i < 100; i += 1) {
            assertEquals( (int) pq.removeSmallest(), 99-i);
        }
    }

    @Test
    public void MyMinPQTest3() {
        int BOUND = 100;
        ExtrinsicMinPQ<Integer> pq = new ArrayHeapMinPQ<Integer>();
        ArrayList<Integer> number = new ArrayList<>();
        for (int i = 0; i < BOUND; i += 1) {
            number.add(i);
        }
        Random random = new Random();
        for (int i = 0; i < BOUND; i += 1) {
            int r = random.nextInt(BOUND-i);
            int insert = number.remove(r);
            pq.add(insert, insert);
        }
        for (int i = 0; i < BOUND; i += 1) {
            assertEquals( (int) pq.removeSmallest(), i);
        }
    }

    @Test
    public void MyMinPQTest4() {
        int BOUND = 1000;
        ExtrinsicMinPQ<Integer> pq = new ArrayHeapMinPQ<Integer>();
        ArrayList<Integer> number = new ArrayList<>();
        for (int i = 0; i < BOUND; i += 1) {
            number.add(i);
        }
        Random random = new Random();
        for (int i = 0; i < BOUND; i += 1) {
            int r = random.nextInt(BOUND-i);
            int insert = number.remove(r);
            pq.add(insert, random.nextInt());
        }
        for (int i = 0; i < BOUND; i += 1) {
            pq.changePriority(i, i);
        }
        for (int i = 0; i < BOUND; i += 1) {
            assertEquals( (int) pq.removeSmallest(), i);
        }
    }

    @Test
    public void timeTest() {
        int BOUND = 500;
        Random random = new Random();

        ExtrinsicMinPQ<Integer> pq1 = new ArrayHeapMinPQ<Integer>();
        ExtrinsicMinPQ<Integer> pq2 = new NaiveMinPQ<>();
        ArrayList<Integer> number = new ArrayList<>();

        for (int i = 0; i < BOUND; i += 1) {
            number.add(i);
        }

        Stopwatch sw1 = new Stopwatch();
        for (int i = 0; i < BOUND; i += 1) {
            int r = random.nextInt(BOUND-i);
            int insert = number.remove(r);
            pq1.add(insert, random.nextInt());
        }
        System.out.println("ArrayHeapMinPQ total add time elapsed: " + sw1.elapsedTime() +  " seconds.");

        for (int i = 0; i < BOUND; i += 1) {
            number.add(i);
        }

        Stopwatch sw2 = new Stopwatch();
        for (int i = 0; i < BOUND; i += 1) {
            int r = random.nextInt(BOUND-i);
            int insert = number.remove(r);
            pq2.add(insert, random.nextInt());
        }
        System.out.println("NaiveMinPQ total time add elapsed: " + sw2.elapsedTime() +  " seconds.");

        for (int i = 0; i < BOUND; i += 1) {
            pq1.changePriority(i, i);
            pq2.changePriority(i, i);
        }

        Stopwatch sw3 = new Stopwatch();
        for (int i = 0; i < BOUND; i += 1) {
            assertEquals( (int) pq1.removeSmallest(), i);
        }
        System.out.println("ArrayHeapMinPQ total remove time elapsed: " + sw3.elapsedTime() +  " seconds.");

        Stopwatch sw4 = new Stopwatch();
        for (int i = 0; i < BOUND; i += 1) {
            assertEquals( (int) pq2.removeSmallest(), i);
        }
        System.out.println("NaiveMinPQ total remove time elapsed: " + sw4.elapsedTime() +  " seconds.");
    }

    @Test
    public void timeTest2() {
        int BOUND = 500000;
        Random random = new Random();

        ExtrinsicMinPQ<Integer> pq1 = new ArrayHeapMinPQ<Integer>();
        ArrayList<Integer> number1 = new ArrayList<>();
        ArrayList<Integer> number2 = new ArrayList<>();

        for (int i = 0; i < BOUND; i += 1) {
            number1.add(i);
            number2.add(i);
        }

        Stopwatch sw1 = new Stopwatch();
        for (int i = 0; i < BOUND; i += 1) {
            int r = random.nextInt(BOUND-i);
            int insert = number1.remove(r);
            pq1.add(insert, random.nextInt());
        }
        System.out.println("ArrayHeapMinPQ total add time elapsed: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        for (int i = 0; i < BOUND; i += 1) {
            pq1.changePriority(i, i);
        }
        System.out.println("ArrayHeapMinPQ total changePriority time elapsed: " + sw2.elapsedTime() +  " seconds.");

        Stopwatch sw3 = new Stopwatch();
        for (int i = 0; i < BOUND; i += 1) {
            pq1.removeSmallest();
        }
        System.out.println("ArrayHeapMinPQ total remove time elapsed: " + sw3.elapsedTime() +  " seconds.");

        Stopwatch sw4 = new Stopwatch();
        for (int i = 0; i < BOUND; i += 1) {
            int r = random.nextInt(BOUND-i);
            int insert = number2.remove(r);
            pq1.add(insert, random.nextInt());
        }
        System.out.println("ArrayHeapMinPQ total add again time elapsed: " + sw4.elapsedTime() +  " seconds.");
    }
}
