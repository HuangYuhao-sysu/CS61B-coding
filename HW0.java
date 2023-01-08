public class ClassNameHere {
   public static void main(String[] args) {
    DrawTriangle(10);
   }

   public static void DrawTriangle (int N) {
    int lines = N;
    int size;
    while (lines > 0) {
      size = N + 1 - lines;
      while (size > 0) {
          System.out.print("*");
          size--;
      }
      System.out.print("\n");
      lines--;
    }
   }
}

public class ClassNameHere {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int length = m.length;
        int which = length - 1;
        int max = m[which];
        while (which >= 0 ) {
            if (m[which] > max) {
                max = m[which];
            }
            which--;
        }
        return max;
    }

    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       System.out.print(max(numbers));
    }
}

public class ClassNameHere {
    /** Returns the maximum value from m using a for loop. */
    public static int forMax(int[] m) {
        int length = m.length;
        int which;
        int max = m[length-1];
        for (which = 0; which < length; which++) {
            if (m[which] > max) {
                max = m[which];
            }
        }
        return max;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       System.out.print(forMax(numbers));
    }
}

public class BreakContinue {
    public static void windowPosSum(int[] a, int n) {
        /** your code here */
        int outside_loop;
        int inside_loop;
        int local_sum;
        for (outside_loop = 0; outside_loop < a.length; outside_loop++) {
            if (a[outside_loop] < 0) {
                continue;
            }
            local_sum = a[outside_loop];
            for (inside_loop = 1; inside_loop < n + 1; inside_loop++) {
                if (outside_loop + inside_loop > a.length - 1) {
                    break;
                }
                local_sum = local_sum + a[outside_loop+inside_loop];
            }
            a[outside_loop] = local_sum;
        }
    }
  
    public static void main(String[] args) {
      //int[] a = {1, 2, -3, 4, 5, 4};
      //int n = 3;
      int[] a = {1, -1, -1, 10, 5, -1};
      int n = 2;
      windowPosSum(a, n);
  
      // Should print 4, 8, -3, 13, 9, 4
      System.out.println(java.util.Arrays.toString(a));
    }
  }
  