package collection.my_list;

import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {
    private final static int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
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
    public boolean contains(T object) {
        for (Object element : array) {
            if (Objects.equals(element, object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(T object) {
        resize();
        array[size] = object;
        size++;
    }

    @Override
    public void add(int index, T object) {
        if (index >= size) {
            add(object);
            return;
        }

        Object[] buffer = new Object[size - index];
        System.arraycopy(array, index, buffer, 0, buffer.length);

        for (int i = 0; i < size; i++) {
            if (i == index) {
                array[i] = object;
                break;
            }
        }
        size++;
        resize();
        System.arraycopy(buffer, 0, array, index + 1, buffer.length);
    }

    @Override
    public void remove(T object) {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], object)) {
                if (i == size - 1) {
                    array[i] = null;
                } else {
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                }

                size--;
                return;
            }
        }
    }

    @Override
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) array[index];
    }

    @Override
    public int indexOf(T object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], object)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int lastIndexOf(T object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(array[i], object)) {
                return i;
            }
        }
        return 0;
    }

    private void resize() {
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
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
}