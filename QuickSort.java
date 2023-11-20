import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static int[] generateArr(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        }
        return arr;
    }

    private static void swap(int[] array, int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    public static void quickSort(int[] array, int min, int max) {
        if (min >= max) {
            return;
        }

        int pivot = array[max];
        int left = min;
        int right = max;

        while (left < right) {
            while (array[left] <= pivot && left < right) {
                left++;
            }
            while (array[right] >= pivot && left < right) {
                right--;
            }
            swap(array, left, right);
        }

        swap(array, left, max);

        quickSort(array, min, left - 1);
        quickSort(array, left + 1, max);
    }

    public static void main(String[] args) {
        int[] sizes = {10, 50, 100, 500, 1000, 2000, 3000, 5000, 7500, 10000, 15000, 25000};
        String filename = "QuickSort_Runtime.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            for (int size : sizes) {
                int[] randomArr = generateArr(size);

                long startTime = System.nanoTime();
                quickSort(randomArr, 0, randomArr.length - 1);
                long endTime = System.nanoTime();

                long duration = (endTime - startTime);

                bw.write("QuickSort," + size + "," + duration + "\n");
                System.out.println("Size: " + size + " Time: " + duration + " ns");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
