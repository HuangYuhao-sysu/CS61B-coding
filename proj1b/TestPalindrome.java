import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String[] trueWords = {"noon", "recacer"};
        String[] falseWords = {"hello", "excellent"};
        String[] specialWords = {"", "a"};
        assertTrue(palindrome.isPalindrome(trueWords[0]));
        assertTrue(palindrome.isPalindrome(trueWords[1]));
        assertFalse(palindrome.isPalindrome(falseWords[0]));
        assertFalse(palindrome.isPalindrome(falseWords[1]));
        assertTrue(palindrome.isPalindrome(specialWords[0]));
        assertTrue(palindrome.isPalindrome(specialWords[1]));
    }
    @Test
    public void testIsPalindrome2() {
        CharacterComparator cc = new OffByOne();
        String[] trueWords = {"flake", "lak"};
        String[] falseWords = {"hello", "excellent"};
        String[] specialWords = {"", "a"};
        assertTrue(palindrome.isPalindrome(trueWords[0],cc));
        assertTrue(palindrome.isPalindrome(trueWords[1],cc));
        assertFalse(palindrome.isPalindrome(falseWords[0],cc));
        assertFalse(palindrome.isPalindrome(falseWords[1],cc));
        assertTrue(palindrome.isPalindrome(specialWords[0],cc));
        assertTrue(palindrome.isPalindrome(specialWords[1],cc));
    }
}