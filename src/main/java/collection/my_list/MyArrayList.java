package collection.my_list;

import java.util.Objects;

public class MyArrayList implements MyList {
    private final static int DEFAULT_CAPACITY = 10;
    private Integer[] array;
    private int size;

    public MyArrayList() {
        this.array = new Integer[DEFAULT_CAPACITY];
        this.size = 0;
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
    public boolean contains(Integer object) {
        for (Integer integer : array) {
            if (Objects.equals(integer, object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Integer object) {
        resizeIfNeeded();
        array[size] = object;
        size++;
    }

    @Override
    public void add(int index, Integer object) {
        if (index >= size) {
            add(object);
            return;
        }

        Integer[] buffer = new Integer[size - index];
        System.arraycopy(array, index, buffer, 0, buffer.length);

        for (int i = 0; i < size; i++) {
            if (i == index) {
                array[i] = object;
                break;
            }
        }
        size++;
        resizeIfNeeded();
        System.arraycopy(buffer, 0, array, index + 1, buffer.length);
    }

    @Override
    public boolean remove(Integer object) {
        if (size == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], object)) {
                if (i == size - 1) {
                    array[i] = null;
                } else {
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                }

                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        array = new Integer[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public Integer get(int index) {
        Objects.checkIndex(index, size);
        return array[index];
    }

    @Override
    public int indexOf(Integer object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], object)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int lastIndexOf(Integer object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(array[i], object)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(array[i]);
        }
        return "MyArrayList: [" + builder.append("]");
    }

    private void resizeIfNeeded() {
        if (size == array.length) {
            Integer[] newArray = new Integer[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}