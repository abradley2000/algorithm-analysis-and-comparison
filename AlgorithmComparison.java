import java.util.Arrays;
import java.util.Random;

public class AlgorithmComparison {
    public static void main(String[] args) {
        final int ITERATIONS = 10;
        final int SAMPLE_SIZE = 100000;
        final int SEARCH_INT = 101; // Change to whatever the search algorithms should find
        long[] times = new long[ITERATIONS];
        Random rand = new Random();
        for (int i = 0; i < ITERATIONS; i++) {
            int[] sampleArray = rand.ints(SAMPLE_SIZE, 0, 100).toArray();
            // For the search algorithms I'll insert a single '101' int for them to find at a random index
            sampleArray[rand.nextInt(sampleArray.length)] = SEARCH_INT;
            mergeSort(sampleArray);
            long startTime = System.nanoTime();
            // To test a different algorithm, change the method call to a different one below
            binarySearch(sampleArray, SEARCH_INT);
            times[i] = System.nanoTime() - startTime;
        }
        long minTime = times[0];
        long maxTime = times[0];
        long totalTime = times[0];
        for (int i = 1; i < times.length; i++) {
            if (times[i] < minTime) minTime = times[i];
            if (times[i] > maxTime) maxTime = times[i];
            totalTime += times[i];
        }
        System.out.println("Min time: " + minTime);
        System.out.println("Max time: " + maxTime);
        System.out.println("Avg time: " + totalTime / times.length);
    }
    // Sort algorithm methods
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - (i + 1); j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && key < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) min = j;
            }
            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }
    public static void mergeSort(int[] array) {
        if (array.length < 2) return;
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];
        for (int i = 0; i < mid; i++) left[i] = array[i];
        for (int i = mid; i < array.length; i++) right[i - mid] = array[i];
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }
    // Second portion of merge sort
    public static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) array[k++] = left[i++];
            else array[k++] = right[j++];
        }
        while (i < left.length) array[k++] = left[i++];
        while (j < right.length) array[k++] = right[j++];
    }
    // Search algorithm methods
    public static void linearSearch(int[] array, int e) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == e) System.out.println("Element found at index " + i);
        }
    }
    public static void binarySearch(int[] array, int e) {
        int min = 0, max = array.length -1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (array[mid] == e) {
                System.out.println("Element found at index " + mid);
                break;
            }
            if (array[mid] < e) min = mid + 1;
            else max = mid - 1;
        }
    }
}