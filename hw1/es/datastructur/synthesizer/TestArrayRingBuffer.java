package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test (expected = RuntimeException.class)
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertEquals(arb.fillCount(),0);
        for (int i = 0; i < 10; i += 1) {
            arb.enqueue(i);
        }
        assertEquals(arb.fillCount(),10);
        arb.enqueue(11);
        for (int i = 0; i < 10; i += 1) {
            assertEquals(arb.dequeue(),i);
        }
        assertEquals(arb.fillCount(),0);
        arb.dequeue();
        arb.peek();

        assertEquals(arb.fillCount(),0);
        for (int i = 0; i < 5; i += 1) {
            arb.enqueue(i);
        }
        assertEquals(arb.fillCount(),5);
        for (int i = 0; i < 5; i += 1) {
            assertEquals(arb.dequeue(),i);
        }
        assertEquals(arb.fillCount(),0);
        for (int i = 0; i < 10; i += 1) {
            arb.enqueue(i);
        }
        assertEquals(arb.fillCount(),10);
        for (int i = 0; i < 10; i += 1) {
            assertEquals(arb.dequeue(),i);
        }
    }
}
