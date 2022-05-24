package Main;

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static void main(String args[]) {
        System.out.println("Welcome to my Selection Problem Program!\nEnter -1 to exit");
        int n;
        do {
            System.out.print("\nPlease enter n: ");
            n = input.nextInt();
            if (n > 0) {
                int[] originalArray = createArray(n);

                System.out.println("Mergesort\n~~~~~~~~~~~~~~~~~~~~~~~");
                mergeSortDrive(originalArray);

                System.out.println("\nQuicksort (Iteratively)\n~~~~~~~~~~~~~~~~~~~~~~~");
                quickSortItDrive(originalArray);

                System.out.println("\nQuicksort (Recursively)\n~~~~~~~~~~~~~~~~~~~~~~~");
                quickSortRecDrive(originalArray);

                System.out.println("\nQuicksort (MM Rule)\n~~~~~~~~~~~~~~~~~~~~~~~");
                quickSortMMDrive(originalArray);
            }
        } while (n >= 0);
        System.out.println("Thank you for using my program!\n");
    }

    public static int[] createArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            Random rand = new Random();
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }

    public static int[] copyArray(int[] n) {
        int[] arr = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            arr[i] = n[i];
        }
        return arr;
    }

    public static void mergeSortDrive(int [] array) {
        int k = 1;
        int[] tempArray = copyArray(array);
        long miliStart = System.currentTimeMillis();
        long nanoStart = System.nanoTime();
        System.out.println("K = 1: " + mergeSortK(tempArray, 0, tempArray.length - 1, k - 1));
        long nanoEnd = System.nanoTime();
        long miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length / 4;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n/4: " + mergeSortK(tempArray, 0, tempArray.length - 1, k - 1));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length / 2;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n/2: " + mergeSortK(tempArray, 0, tempArray.length - 1, k - 1));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = (3 * array.length) / 4;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = 3n/4: " + mergeSortK(tempArray, 0, tempArray.length - 1, k - 1));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n: " + mergeSortK(tempArray, 0, tempArray.length - 1, k - 1));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");
    }

    public static int mergeSortK(int array[], int left, int right, int k) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSortK(array, left, middle, k);
            mergeSortK(array, middle + 1, right, k);
            merge(array, left, middle, right);
        }
        return array[k];
    }

    public static void merge(int array[], int left, int middle, int right) {
        int sizeLeft = middle - left + 1;
        int sizeRight = right - middle;
        int leftArray[] = new int[sizeLeft];
        int rightArray[] = new int[sizeRight];
        for (int i = 0; i < sizeLeft; ++i)
            leftArray[i] = array[left + i];
        for (int j = 0; j < sizeRight; ++j)
            rightArray[j] = array[middle + j + 1];
        int i = 0, j = 0;
        int k = left;
        while (i < sizeLeft && j < sizeRight) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            }
            else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < sizeLeft) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < sizeRight) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void quickSortItDrive(int[] array) {
        int k = 1;
        int[] tempArray = copyArray(array);
        long miliStart = System.currentTimeMillis();
        long nanoStart = System.nanoTime();
        System.out.println("k = 1: " + quickSortIt(tempArray, 0, tempArray.length - 1, k));
        long nanoEnd = System.nanoTime();
        long miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length / 4;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n/4: " + quickSortIt(tempArray, 0, tempArray.length - 1, k));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length / 2;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n/2: " + quickSortIt(tempArray, 0, tempArray.length - 1, k));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = (3 * array.length) / 4;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = 3n/4: " + quickSortIt(tempArray, 0, tempArray.length - 1, k));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n: " + quickSortIt(tempArray, 0, tempArray.length - 1, k));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");
    }

    public static int partitionIt(int arr[], int low, int high) {
        int temp;
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return (i + 1);
    }

    public static int quickSortIt(int a[], int left, int right, int k) {
        while (left <= right) {
            int pivotIndex = partitionIt(a, left, right);
            if (pivotIndex == k - 1)
                return a[pivotIndex];

            else if (pivotIndex > k - 1)
                right = pivotIndex - 1;

            else
                left = pivotIndex + 1;
        }
        return 0;
    }

    public static void quickSortRecDrive(int[] array) {
        int k = 1;
        int[] tempArray = copyArray(array);
        long miliStart = System.currentTimeMillis();
        long nanoStart = System.nanoTime();
        System.out.println("k = 1: " + quickSortRec(tempArray, 0, tempArray.length - 1, k - 1));
        long nanoEnd = System.nanoTime();
        long miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length / 4;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n/4: " + quickSortRec(tempArray, 0, tempArray.length - 1, k - 1));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length / 2;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n/2: " + quickSortRec(tempArray, 0, tempArray.length - 1, k - 1));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = (3 * array.length) / 4;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = 3n/4: " + quickSortRec(tempArray, 0, tempArray.length - 1, k - 1));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n: " + quickSortRec(tempArray, 0, tempArray.length - 1, k - 1));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");
    }

    public static int partitionRec (int[] arr, int low, int high) {
        int pivot = arr[high], pivotIt = low;
        for (int i = low; i <= high; i++) {
            if(arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[pivotIt];
                arr[pivotIt] = temp;
                pivotIt++;
            }
        }

        int temp = arr[high];
        arr[high] = arr[pivotIt];
        arr[pivotIt] = temp;

        return pivotIt;
    }

    public static int quickSortRec(int[] arr, int low, int high, int k) {
        int partition = partitionRec(arr,low,high);

        if(partition == k)
            return arr[partition];

        else if(partition < k )
            return quickSortRec(arr, partition + 1, high, k );

        else
            return quickSortRec(arr, low, partition-1, k );
    }

    public static void quickSortMMDrive(int array[]) {
        int k = 1;
        int[] tempArray = copyArray(array);
        long miliStart = System.currentTimeMillis();
        long nanoStart = System.nanoTime();
        System.out.println("K = 1: " + quickSortMM(tempArray,0,tempArray.length-1, k));
        long nanoEnd = System.nanoTime();
        long miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length / 4;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n/4: " + quickSortMM(tempArray,0,tempArray.length-1, k));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        k = array.length / 2;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = n/2: " + quickSortMM(tempArray,0,tempArray.length-1, k));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms");

        /* k = (3 * array.length) / 4;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        System.out.println("k = 3n/4: " + quickSortMM(tempArray,0,tempArray.length-1, k));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms"); */

        /*k = array.length;
        tempArray = copyArray(array);
        miliStart = System.currentTimeMillis();
        nanoStart = System.nanoTime();
        //System.out.println("k = n: " + quickSortMM(tempArray,0,tempArray.length-1, k));
        nanoEnd = System.nanoTime();
        miliEnd = System.currentTimeMillis();
        System.out.println("Total execution time: " + (nanoEnd - nanoStart) + " nanoseconds, " + (miliEnd - miliStart) + "ms"); */
    }

    public static int quickSortMM(int arr[],int low,int high, int k) {
        if(low == high)
            return arr[low];

        int m = partitionMM(arr,low,high);
        int length = m - low + 1;

        if(length == k)
            return arr[m];
        else if(length > k)
            return quickSortMM(arr,low,m-1, k);
        else
            return quickSortMM(arr,m+1,high,k-length);
    }

    public static int partitionMM(int arr[],int low, int high) {
        int pivotValue = getPivotValue(arr, low, high);

        while(low < high) {
            while(arr[low] < pivotValue)
                low ++;

            while(arr[high] > pivotValue)
                high--;

            if(arr[low] == arr[high])
                low ++;

            else if(low < high) {
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }
        }
        return high;
    }

    public static int getPivotValue(int arr[],int low,int high) {
        if(high-low+1 <= 9) {
            Arrays.sort(arr);
            return arr[arr.length/2];
        }

        int temp[] = null;

        int medians[] = new int[(int)Math.ceil((double)(high-low+1)/5)];
        int medianIndex = 0;

        while(low <= high) {
            temp = new int[Math.min(5,high-low+1)];
            for(int j=0;j<temp.length && low <= high;j++) {
                temp[j] = arr[low];
                low++;
            }

            Arrays.sort(temp);

            medians[medianIndex] = temp[temp.length/2];
            medianIndex++;
        }
        return getPivotValue(medians,0,medians.length-1);
    }
}