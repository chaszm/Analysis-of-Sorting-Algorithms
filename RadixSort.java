import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class RadixSort {

    public static int[] generateArr(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }
        return arr;
    }

    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        int[] sizes = {10, 50, 100, 500, 1000, 2000, 3000, 5000, 7500, 10000, 15000, 25000};
        String filename = "RadixSort_Runtime.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            for (int size : sizes) {
                int[] randomArr = generateArr(size);

                long startTime = System.nanoTime();
                radixSort(randomArr);
                long endTime = System.nanoTime();

                long duration = (endTime - startTime);

                bw.write("RadixSort," + size + "," + duration + "\n");
                System.out.println("Size: " + size + " Time: " + duration + " ns");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
