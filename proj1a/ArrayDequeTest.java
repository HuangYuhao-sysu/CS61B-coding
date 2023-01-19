public class ArrayDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed. 
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static void myTest() {
        System.out.println("Running class test.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        lld1.addFirst(5);
        lld1.addFirst(10);
        lld1.addLast(0);
        lld1.printDeque();

        /** Test copy. */
        ArrayDeque<Integer> lld2 = new ArrayDeque<Integer>(lld1);
        lld2.addFirst(15);
        lld2.addLast(-5);
        lld2.printDeque();
        lld1.printDeque();
        printTestStatus(true);

        /** Test get. */
        for (int i = -2; i < lld2.size()+2; i++) {
            System.out.println(lld2.get(i));
        }
        printTestStatus(true);

        /** Test removing. */
        lld2.removeFirst();
        lld2.removeLast();
        lld2.printDeque();
        ArrayDeque<Integer> lld3 = new ArrayDeque<Integer>();
        lld3.removeFirst();
        lld3.printDeque();
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        myTest();
    }
}
