import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Project for CS 4306 Algorithm Analysis
public class MergeSort {

    public static int[] generateArr(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }
        return arr;
    }

    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        mergeSort(left);
        mergeSort(right);

        mergeArr(arr, left, right);
    }

    private static void mergeArr(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int[] sizes = {10, 50, 100, 500, 1000, 2000, 3000, 5000, 7500, 10000, 15000, 25000};
        String filename = "MergeSort_Runtime.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            for (int size : sizes) {
                int[] randomArr = generateArr(size);

                long startTime = System.nanoTime();
                mergeSort(randomArr);
                long endTime = System.nanoTime();

                long duration = (endTime - startTime);

                bw.write("MergeSort," + size + "," + duration + "\n");
                System.out.println("Size: " + size + " Time: " + duration + " ns");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
