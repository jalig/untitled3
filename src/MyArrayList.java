import java.util.Arrays;

public class MyArrayList implements StringList {
    private final int DEFAULT_CAPACITY = 10;
    private String[] list;
    private int size;

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity < 0");
        } else if (capacity == 0) {
            list = new String[DEFAULT_CAPACITY];
        } else {
            list = new String[capacity];
        }
    }

    public MyArrayList() {
        list = new String[DEFAULT_CAPACITY];
    }

    private void getIncreaseArray() {
        if (list[list.length - 1] != null) {
            list = Arrays.copyOf(list, list.length * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Индекс " + index + " не существует в листе");
        }
    }

    @Override
    public String add(String item) {
        getIncreaseArray();
        list[size++] = item;
        return item;
    }


    @Override
    public String add(int index, String item) {
        checkIndex(index);
        getIncreaseArray();
        String[] temp = Arrays.copyOf(list, size);
        System.arraycopy(temp, 0, list, 0, index);
        list[index] = item;
        System.arraycopy(temp, index, list, index + 1, temp.length - index);
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkIndex(index);
        return list[index] = item;
    }

    @Override
    public String remove(String item) {
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
    public String remove(int index) {
        checkIndex(index);

        String string = list[index];
        System.arraycopy(list, index + 1, list, index, list.length - index - 1);
        size--;
        return string;
    }

    @Override
    public boolean contains(String item) {
        for (String s : list) {
            if (s != null && s.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i] != null && list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
        list = new String[size];
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, size);
    }


    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }
}
