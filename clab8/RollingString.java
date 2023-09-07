import java.util.ArrayList;

/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;


    private ArrayList<Character> s; // string
    private int l;                  // length

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        /* FIX ME */
        this.s = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i += 1) {
            this.s.add(s.charAt(i));
        }
        this.l = length;
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        /* FIX ME */
        s.add(c);
        s.remove(0);
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        /* FIX ME */
        for (Character c : s) {
            strb.append(c);
        }
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        /* FIX ME */
        return l;
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        /* FIX ME */
        String otherString = ((RollingString) o).toString();
        if (otherString.length() != this.l) return false;
        if (otherString.length() == this.l) {
            for (int i = 0; i < this.l; i += 1) {
                if (s.get(i) != otherString.charAt(i)) return false;
            }
        }
        return true;
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        /* FIX ME */
        if (l == 1) return (int) s.get(0);
        int result = (int) s.get(0);
        for (int i = 1; i < s.size(); i += 1) {
            result = cal(result, s.get(i));
        }
        return result;
    }

    private int cal(int number1, int number2) {
        return ((number1*UNIQUECHARS)%PRIMEBASE + number2)%PRIMEBASE;
    }
}
