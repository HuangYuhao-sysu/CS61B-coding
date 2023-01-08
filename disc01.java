public class disc01 {
    public static int mystery(int[] inputArray, int k) {
        int x = inputArray[k];
        int answer = k;
        int index = k + 1;
        while (index < inputArray.length) {
            if (inputArray[index] < x) {
                x = inputArray[index];
                answer = index;
            }
            index = index + 1;
        }
        return answer;
    }

    public static void mystery2(int[] inputArray) {
        int index = 0;
        while (index < inputArray.length) {
            int targetIndex = mystery(inputArray, index);
            int temp = inputArray[targetIndex];
            inputArray[targetIndex] = inputArray[index];
            inputArray[index] = temp;
            index = index + 1;
        }
    }

    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else if (n < 0) {
            return 0;
        } else {
            return fib(n-1) + fib(n-2);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 0, 4, 6, 3};
        mystery(array, 2);
        mystery2(array);
        System.out.println(fib(3));
    }
}
