package Main;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome to my Matrix Multiplication Program!\nEnter -1 to exit");
        int n;
        do {
            System.out.print("\nPlease enter n for n x n matrices: ");
            n = input.nextInt();
            if (n > 0) {
                int[][] matrix1 = createMatrix(n), matrix2 = createMatrix(n);

                System.out.println("Classical Matrix Multiplication");
                long miliStart = System.currentTimeMillis();
                long nanoStart = System.nanoTime();
                classicalMatrixMultiplication(matrix1, matrix2);
                long nanoEnd = System.nanoTime();
                long miliEnd = System.currentTimeMillis();
                System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms\n");

                System.out.println("Divide & Conquer Matrix Multiplication");
                miliStart = System.currentTimeMillis();
                nanoStart = System.nanoTime();
                divideConquerMatrixMultiplication(matrix1, matrix2);
                nanoEnd = System.nanoTime();
                miliEnd = System.currentTimeMillis();
                System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms\n");

                System.out.println("Strassen's Matrix Multiplication");
                miliStart = System.currentTimeMillis();
                nanoStart = System.nanoTime();
                strassenMatrixMultiplication(matrix1, matrix2);
                nanoEnd = System.nanoTime();
                miliEnd = System.currentTimeMillis();
                System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms\n");
            }
        } while (n >= 0);
        System.out.println("Thank you for using my program!\n");
    }

    public static int [][]createMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Random rand = new Random();
                matrix[i][j] = rand.nextInt(100);
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print (matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void classicalMatrixMultiplication(int[][] matrix1, int[][] matrix2){
        int[][] result = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++){
            for (int j = 0; j < matrix2[0].length; j++){
                int sum = 0;
                for (int k = 0; k < matrix2.length; k++){
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                result[i][j] = sum;
            }
        }

        //printMatrix(result);
    }

    public static int i = 0, j = 0, k = 0;

    public static void divideConquerMatrixMultiplication(int[][] matrix1, int[][] matrix2) {
        int row1 = matrix1.length, column1 = matrix1[0].length, column2 = matrix2[0].length;
        int[][] result = new int[matrix1.length][matrix2[0].length];
        divideConquerRecursion(matrix1, row1, column1, matrix2, column2, result);
        i = 0; j = 0; k = 0;

        //printMatrix(result);
    }

    public static void divideConquerRecursion(int[][] matrix1, int row1, int column1, int[][] matrix2, int column2, int[][] result){
        if (i >= row1)
            return;
        if (j < column2) {
            if (k < column1) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
                k++;

                divideConquerRecursion(matrix1, row1, column1, matrix2, column2, result);
            }

            k = 0;
            j++;
            divideConquerRecursion(matrix1, row1, column1, matrix2, column2, result);
        }

        j = 0;
        i++;
        divideConquerRecursion(matrix1, row1, column1, matrix2, column2, result);
    }

    public static void strassenMatrixMultiplication(int[][] matrix1, int[][] matrix2){
        int[][] result = strassenMultiply(matrix1, matrix2);

        //printMatrix(result);
    }

    public static int[][] strassenMultiply(int[][] matrix1, int[][] matrix2) {
        int n = matrix1.length;
        int[][] result = new int[n][n];

        if (n == 1)
            result[0][0] = matrix1[0][0] * matrix2[0][0];
        else {
            int[][] matrix1_11 = new int[n / 2][n / 2];
            int[][] matrix1_12 = new int[n / 2][n / 2];
            int[][] matrix1_21 = new int[n / 2][n / 2];
            int[][] matrix1_22 = new int[n / 2][n / 2];

            int[][] matrix2_11 = new int[n / 2][n / 2];
            int[][] matrix2_12 = new int[n / 2][n / 2];
            int[][] matrix2_21 = new int[n / 2][n / 2];
            int[][] matrix2_22 = new int[n / 2][n / 2];

            strassenSplit(matrix1, matrix1_11, 0, 0);
            strassenSplit(matrix1, matrix1_12, 0, n / 2);
            strassenSplit(matrix1, matrix1_21, n / 2, 0);
            strassenSplit(matrix1, matrix1_22, n / 2, n / 2);

            strassenSplit(matrix2, matrix2_11, 0, 0);
            strassenSplit(matrix2, matrix2_12, 0, n / 2);
            strassenSplit(matrix2, matrix2_21, n / 2, 0);
            strassenSplit(matrix2, matrix2_22, n / 2, n / 2);

            int[][] M1 = strassenMultiply(strassenAdd(matrix1_11, matrix1_22), strassenAdd(matrix2_11, matrix2_22));
            int[][] M2 = strassenMultiply(strassenAdd(matrix1_21, matrix1_22), matrix2_11);
            int[][] M3 = strassenMultiply(matrix1_11, strassenSub(matrix2_12, matrix2_22));
            int[][] M4 = strassenMultiply(matrix1_22, strassenSub(matrix2_21, matrix2_11));
            int[][] M5 = strassenMultiply(strassenAdd(matrix1_11, matrix1_12), matrix2_22);
            int[][] M6 = strassenMultiply(strassenSub(matrix1_21, matrix1_11), strassenAdd(matrix2_11, matrix2_12));
            int[][] M7 = strassenMultiply(strassenSub(matrix1_12, matrix1_22), strassenAdd(matrix2_21, matrix2_22));

            int[][] C11 = strassenAdd(strassenSub(strassenAdd(M1, M4), M5), M7);
            int[][] C12 = strassenAdd(M3, M5);
            int[][] C21 = strassenAdd(M2, M4);
            int[][] C22 = strassenAdd(strassenSub(strassenAdd(M1, M3), M2), M6);

            strassenJoin(C11, result, 0, 0);
            strassenJoin(C12, result, 0, n / 2);
            strassenJoin(C21, result, n / 2, 0);
            strassenJoin(C22, result, n / 2, n / 2);
        }

        return result;
    }

    public static int[][] strassenSub(int[][] matrix1, int[][] matrix2) {
        int n = matrix1.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = matrix1[i][j] - matrix2[i][j];
        return result;
    }

    public static int[][] strassenAdd(int[][] matrix1, int[][] matrix2) {
        int n = matrix1.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = matrix1[i][j] + matrix2[i][j];
        return result;
    }

    public static void strassenSplit(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    public static void strassenJoin(int[][] C, int[][] P, int iB, int jB)
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }
}