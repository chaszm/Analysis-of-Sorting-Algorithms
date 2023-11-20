import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HeapSort {

    public static int[] generateArr(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }
        return arr;
    }

    public static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != root) {
            int swap = array[root];
            array[root] = array[largest];
            array[largest] = swap;
            heapify(array, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] sizes = {10, 50, 100, 500, 1000, 2000, 3000, 5000, 7500, 10000, 15000, 25000};
        String filename = "HeapSort_Runtime.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            for (int size : sizes) {
                int[] randomArr = generateArr(size);

                long startTime = System.nanoTime();
                heapSort(randomArr);
                long endTime = System.nanoTime();

                long duration = (endTime - startTime);

                bw.write("HeapSort," + size + "," + duration + "\n");
                System.out.println("Size: " + size + " Time: " + duration + " ns");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
