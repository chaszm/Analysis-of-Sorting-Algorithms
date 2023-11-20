import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InsertionSort {

    public static int[] generateArr(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }
        return arr;
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] sizes = {10, 50, 100, 500, 1000, 2000, 3000, 5000, 7500, 10000, 15000, 25000};
        String filename = "InsertionSort_Runtime.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            for (int size : sizes) {
                int[] randomArr = generateArr(size);

                long startTime = System.nanoTime();
                insertionSort(randomArr);
                long endTime = System.nanoTime();

                long duration = (endTime - startTime);

                bw.write("InsertionSort," + size + "," + duration + "\n");
                System.out.println("Size: " + size + " Time: " + duration + " ns");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
