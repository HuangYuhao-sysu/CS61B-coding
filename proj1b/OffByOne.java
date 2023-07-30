public class OffByOne implements CharacterComparator {
    /**
     * Return true if x and y are different by exactly one.
     * For example, 'a' and 'b', 'r' and 'q'.
     * Don't allow non-alphabetical characters.
     * */
    @Override
    public boolean equalChars(char x, char y) {
        if (((97 <= x ) & (x <= 122)) || ((65 <= x ) & (x <= 90)) ||
                ((97 <= y ) & (y <= 122)) || ((65 <= y ) & (y <= 90))) {
            return y - x == 1 || x - y == 1;
        }
        throw new IllegalArgumentException("Non-alphabetical characters don't allow");
    }
}
