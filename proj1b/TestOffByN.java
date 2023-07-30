import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator[] OffByN = new OffByN[10];

    public void initialTest() {
        for (int i = 0; i < 10; i += 1) {
            OffByN[i] = new OffByN(i+1);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEqualChars() {
        initialTest();
        String a = "abcd";
        String b = "bcde";
        String c = "cdef";
        for (int i = 0; i < a.length(); i += 1) {
            assertTrue(OffByN[0].equalChars(a.charAt(i), b.charAt(i)));
            assertTrue(OffByN[0].equalChars(b.charAt(i), a.charAt(i)));
            assertFalse(OffByN[1].equalChars(a.charAt(i), b.charAt(i)));
            assertFalse(OffByN[1].equalChars(b.charAt(i), a.charAt(i)));
        }
        for (int i = 0; i < a.length(); i += 1) {
            assertTrue(OffByN[1].equalChars(a.charAt(i), c.charAt(i)));
            assertTrue(OffByN[1].equalChars(a.charAt(i), c.charAt(i)));
            assertFalse(OffByN[2].equalChars(a.charAt(i), c.charAt(i)));
            assertFalse(OffByN[2].equalChars(b.charAt(i), c.charAt(i)));
        }
        OffByN[0].equalChars('%', '&');
    }
}
