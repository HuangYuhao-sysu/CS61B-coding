public class Palindrome {
    /**
     * Convert a word to deque.
     * */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        int l = word.length();
        for (int i = 0; i < l; i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        Palindrome palindrome = new Palindrome();
        Deque d = palindrome.wordToDeque(word);
        return dequePalindrome(d);
    }

    public boolean dequePalindrome(Deque d) {
        if (d.size() <= 1) {
            return true;
        } else if (d.removeFirst() == d.removeLast()) {
            return dequePalindrome(d);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {

        if (word.length() <= 1) {
            return true;
        } else {
            int l = word.length();
            char head = word.charAt(0);
            char tail = word.charAt(l-1);
            if (cc.equalChars(head,tail)) {
                return isPalindrome(word.substring(1,l-1),cc);
            }
            return false;
        }
    }
}