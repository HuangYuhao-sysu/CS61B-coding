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
        String[] palindromeWord = new String[]{"a","","z","noon","racecar"};
        String[] notPalindromeWord = new String[]{"fuck","hello","word"};
        for (String w : palindromeWord) {
            assertTrue(palindrome.isPalindrome(w));
        }
        for (String w : notPalindromeWord) {
            assertFalse(palindrome.isPalindrome(w));
        }

        /*
        * Test overload Palindrome.
        * */
        //CharacterComparator cc1 = new OffByOne();
        CharacterComparator cc1 = new OffByN(1);

        String[] palindromeOffByOne = new String[]{"a","","z","flake"};
        String[] notPalindromeOffByOne = new String[]{"fuck","hello","word"};
        for (String w : palindromeOffByOne) {
            assertTrue(palindrome.isPalindrome(w,cc1));
        }
        for (String w : notPalindromeOffByOne) {
            assertFalse(palindrome.isPalindrome(w,cc1));
        }
    }


}