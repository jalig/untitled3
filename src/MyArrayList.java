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

    @Override
    public String add(String item) {
        if (list[list.length - 1] != null) {
            String[] newList = new String[list.length * 2];
            System.arraycopy(list, 0, newList, 0, list.length);
            list = newList;
        }

        return list[size++] = item;
    }

    @Override
    public String add(int index, String item) {
        if (list[list.length - 1] != null) {
            String[] newList = new String[list.length * 2];
            System.arraycopy(list, 0, newList, 0, index);
            newList[index] = item;
            System.arraycopy(list, index, newList, index + 1, list.length - index);
            list = newList;
            size++;
        } else {
            String[] newList = new String[list.length];
            System.arraycopy(list, 0, newList, 0, index);
            newList[index] = item;
            System.arraycopy(list, index, newList, index + 1, list.length - (index + 1));
            list = newList;
            size++;
        }
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkExp(index);
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
        checkExp(index);

        if (list[index] == null) {
            throw new IndexOutOfBoundsException("Элемента нет :(");
        }

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
        checkExp(index);
        if (list[index] == null) {
            throw new IndexOutOfBoundsException("Элемента нет :(");
        }
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("Нельзя передавать null");
        }
        if (size != otherList.size()) return false;
        for (int i = 0; i < size; i++) {
            if (list[i] != otherList.get(i)) return false;
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

    private void checkExp(int index) {
        if (index < 0 || index >= list.length) {
            throw new ArrayIndexOutOfBoundsException("Неверный индекс");
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }
}
