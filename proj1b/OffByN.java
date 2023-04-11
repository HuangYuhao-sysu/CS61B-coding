public class OffByN implements CharacterComparator {
    public int N;
    public OffByN(int x) {
       N = x;
    }
    @Override
    public boolean equalChars(char x, char y) {
        return ((int) x + this.N) == (int) y || ((int) x - this.N) == (int) y;
    }
}