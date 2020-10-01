public class Fibonacci {
    static long calculFibonachiRecursive(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else {
            return calculFibonachiRecursive(n - 1) + calculFibonachiRecursive(n - 2);
        }
    }

    static long calculFibonacciIteratif(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else {
            long a = 0;
            long b = 1;
            long temp = 0;
            for (int i = 0; i < n - 1; i++) {
                temp = a + b;
                a = b;
                b = temp;
            }
            return b;
        }
    }

    static long[][] multipleMatrix (long matrix1[][], long matrix2[][], int n){
        long[][] result = new long[n][n];
        for(int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = 0;
            }
        }
        for(int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++){
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    static long[][] identityMatrix = {{1, 0}, {0, 1}};

    static long[][] powerMatrix(long matrix[][], int n){
        long[][] result;
        int length = matrix[0].length;
        if (n == 0) {
            return identityMatrix;
        }

        result = powerMatrix(matrix, n /2);
        if (n % 2 == 0) return multipleMatrix(result, result, length);
        else {
            result = multipleMatrix(result, result, length);
            result = multipleMatrix(result, matrix, length);
            return result;
        }

    }

    public static void main(String[] args) {
        final long debut = System.currentTimeMillis();
        //System.out.println(calculFibonachi(50));
        final long fin = System.currentTimeMillis();
        //System.out.println("time to run recusif: " + (fin - debut) + " ms");
        final long debut2 = System.nanoTime();
        System.out.println(calculFibonacciIteratif(50));
        final long fi2 = System.nanoTime();
        System.out.println("time to run iteratif: " + (fi2 - debut2) + " ns");

        long[][] fibonacci = {{0, 1}, {1, 1}};
        fibonacci = powerMatrix(fibonacci, 10);
        System.out.println(fibonacci[0][1]);
    }
}
