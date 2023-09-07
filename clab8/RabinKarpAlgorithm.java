public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int inputL = input.length();
        int patternL = pattern.length();
        if (patternL > inputL) return -1;
        RollingString rPattern = new RollingString(pattern, patternL);
        RollingString rInput = new RollingString(pattern, patternL);
        for (int i = 0; i < patternL; i += 1) {
            rInput.addChar(input.charAt(i));
        }
        int hPattern = rPattern.hashCode();
        for (int i = 0; i < inputL - patternL + 1; i += 1) {
            int hInput = rInput.hashCode();
            if (hInput == hPattern) {
                if (rInput.equals(rPattern)) return i;
            }
            rInput.addChar(input.charAt(i+patternL));
        }
        return -1;
    }

}
