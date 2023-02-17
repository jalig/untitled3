package list;

import service.MyList;

import java.util.Arrays;

public class IntegerArrayList implements MyList<Integer> {
    private final int DEFAULT_CAPACITY = 10;
    private Integer[] list;
    private int size;

    public IntegerArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity < 0");
        } else if (capacity == 0) {
            list = new Integer[DEFAULT_CAPACITY];
        } else {
            list = new Integer[capacity];
        }
    }

    public IntegerArrayList() {
        list = new Integer[DEFAULT_CAPACITY];
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private void grow() {
        if (list[list.length - 1] != null) {
            list = Arrays.copyOf(list, list.length / 2 * 3);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Индекс " + index + " не существует в листе");
        }
    }

    private void sortInsertion() {
        for (int i = 1; i < list.length; i++) {
            int temp = list[i];
            int j = i;
            while (j > 0 && list[j - 1] >= temp) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = temp;
        }
    }

    @Override
    public Integer add(Integer item) {
        grow();
        list[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkIndex(index);
        grow();
        Integer[] temp = Arrays.copyOf(list, size);
        System.arraycopy(temp, 0, list, 0, index);
        list[index] = item;
        System.arraycopy(temp, index, list, index + 1, temp.length - index);
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIndex(index);
        return list[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        if (!contains(item)) {
            throw new IllegalArgumentException("Ошибка удаления");
        }
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(item)) {
                System.arraycopy(list, i + 1, list, i, list.length - i - 1);
                size--;
            }
        }
        return item;
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);

        Integer integer = list[index];
        System.arraycopy(list, index + 1, list, index, list.length - index - 1);
        size--;
        return integer;
    }

    @Override
    public boolean contains(Integer item) {
        quickSort(list, 0, size - 1);
        return binarySort(item);
    }

    private boolean binarySort(int element) {
        int min = 0;
        int max = list.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == list[mid]) {
                return true;
            }

            if (element < list[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i] != null && list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return list[index];
    }

    @Override
    public boolean equals(MyList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("Нельзя передавать null");
        }
        if (size != otherList.size()) return false;
        for (int i = 0; i < size; i++) {
            if (!list[i].equals(otherList.get(i))) return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        list = new Integer[size];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(list, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }
}
