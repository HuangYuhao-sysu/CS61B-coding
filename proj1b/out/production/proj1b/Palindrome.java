public class Palindrome {
    /**
     * Convert a word to deque.
     * */
    public Deque<Character> wordToDeque(String word) {
        Deque wordDeque = new Deque();
        int l = word.length();
        for (int i = 0; i < l; i++) {
            wordDeque.addLast(Character.toString(word.charAt(i)));
        }
        return wordDeque;
    }
}