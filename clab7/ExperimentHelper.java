import org.junit.Test;

import java.security.Key;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        if (N <= 1) {
            return 0;
        } else {
            int power = (int) Math.floor(Math.log(N+1)/Math.log(2));
            int nearestNumber = (int) Math.pow(2, power) - 1;
            int diffNumber = N - nearestNumber;
            if (diffNumber == 0) {
                return (int) (optimalIPL((int) Math.pow(2, power-1) - 1) + Math.pow(2, power - 1)*(power - 1));
            }
            return optimalIPL(nearestNumber) + diffNumber*power;
        }
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return optimalIPL(N)/(double) N;
    }

    @Test
    public void testOptimalIPL() {
        assertEquals(0, optimalIPL(1));
        assertEquals(1, optimalIPL(2));
        assertEquals(2, optimalIPL(3));
        assertEquals(4, optimalIPL(4));
        assertEquals(6, optimalIPL(5));
        assertEquals(8, optimalIPL(6));
        assertEquals(10, optimalIPL(7));
        assertEquals(13, optimalIPL(8));
    }

    @Test
    public void testOptimalAverageDepth() {
        assertEquals(optimalAverageDepth(1), 0, 0.001);
        assertEquals(optimalAverageDepth(5), 1.2, 0.001);
        assertEquals(optimalAverageDepth(8), 1.625, 0.001);
    }

    public static void randomOp(BST<Integer> tree, Set<Integer> keySet, Boolean useRamdon) {
        Random r = new Random();
        if (useRamdon) {
            int getKey = tree.getRandomKey();
            tree.deleteTakingRandom(getKey);
            keySet.remove(getKey);
        } else {
            int getKey = tree.getRandomKey();
            tree.deleteTakingSuccessor(getKey);
            keySet.remove(getKey);
        }
        int newKey = r.nextInt(10000);
        tree.add(newKey);
        keySet.add(newKey);
    }
}
