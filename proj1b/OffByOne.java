public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return ((int) x + 1) == (int) y || ((int) x - 1) == (int) y;
    }
}