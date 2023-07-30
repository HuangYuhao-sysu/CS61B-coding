import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test(expected = IllegalArgumentException.class)
    public void testEqualChars() {
        String a = "abcd";
        String b = "bcde";
        String c = "cdef";
        for (int i = 0; i < a.length(); i += 1) {
            assertTrue(offByOne.equalChars(a.charAt(i), b.charAt(i)));
            assertTrue(offByOne.equalChars(b.charAt(i), a.charAt(i)));
        }
        for (int i = 0; i < a.length(); i += 1) {
            assertFalse(offByOne.equalChars(a.charAt(i), c.charAt(i)));
        }
        offByOne.equalChars('%', '&');
    }

    @Test
    public void testIsPalindrome() {

    }
}