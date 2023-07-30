public class OffByN implements CharacterComparator {
    /**
     * Return true if x and y are different by exactly N.
     * For example, 'a' and 'b', 'r' and 'q' are OffByOne.
     * Don't allow non-alphabetical characters.
     * */
    public int n = 0;
    public OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (((97 <= x ) & (x <= 122)) || ((65 <= x ) & (x <= 90)) ||
                ((97 <= y ) & (y <= 122)) || ((65 <= y ) & (y <= 90))) {
            return y - x == n || x - y == n;
        }
        throw new IllegalArgumentException("Excepted an alphabetical character.");
    }
}
