public class Palindrome {
    public void Palindrome() {
    }
    /**
     * Return a Deque where the characters appear in the same order as in the String.
     * For example, the word is "persiflage", then the return Deque should have the 'p' at the front, followed by 'r'.
     * */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wtd = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            wtd.addLast(word.charAt(i));
        }
        return wtd;
    }

    /**
     * Should return true is a word is a Palindrome, otherwise false.
     * A Palindrome is defined as a word that is the same whether it is read forwards or backwards.
     * For example, "noon", "racecar" are Palindromes, "horse" is not. Any word of length 0 or 1 is a Palindrome.
     * */
    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque d) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        } else if (d.removeFirst() == d.removeLast()) {
            return isPalindromeHelper(d);
        }
        return false;
    }

    /**
     * Return true is the word is a Palindrome according to the character comparison test provided by the
     * CharacterComparator passed in as argument cc.
     * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque d = wordToDeque(word);
        return isPalindromeHelper(d, cc);
    }

    private boolean isPalindromeHelper(Deque d, CharacterComparator cc) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        } else if (cc.equalChars((char) d.removeFirst(), (char) d.removeLast())) {
            return isPalindromeHelper(d, cc);
        }
        return false;
    }
}
