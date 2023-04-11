import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator offBy6 = new OffByN(6);

    // Your tests go here.
    @Test
    public void equalCharTest() {
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('r','q'));
        assertFalse(offByOne.equalChars('a','e'));
        assertFalse(offByOne.equalChars('a','z'));
        assertFalse(offByOne.equalChars('a','a'));
        assertTrue(offByOne.equalChars('&','%'));
    }

    @Test
    public void equalCharTestN() {
        assertTrue(offBy6.equalChars('a','g'));
        assertTrue(offBy6.equalChars('g','a'));
        assertFalse(offBy6.equalChars('a','a'));
    }
}