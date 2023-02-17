import list.IntegerArrayList;


import java.util.Arrays;
import java.util.Random;
import java.util.function.IntUnaryOperator;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[100000];
        Random random = new Random();
        IntUnaryOperator randomInt = i -> random.nextInt();
        Arrays.setAll(array, randomInt);
        int[] array1 = array.clone();
        int[] array2 = array.clone();

        IntegerArrayList integerArrayList = new IntegerArrayList(100000);
        for (int i = 0; i < 100000; i++) {
            integerArrayList.add(random.nextInt(1000));
        }
        //оставил самый быстрый метод
        long start2 = System.currentTimeMillis();
        sortInsertion(array2);
        System.out.println(System.currentTimeMillis() - start2);

    }
    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

}