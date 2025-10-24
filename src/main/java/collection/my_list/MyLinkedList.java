package collection.my_list;

import collection.my_queue.MyQueue;

import java.util.Objects;

public class MyLinkedList<T> implements MyList<T>, MyQueue<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public MyLinkedList() {
        this.first = null;
        this.last = null;
        size = 0;
    }

    private static class Node<T> {
        T element;
        Node<T> prev;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null && last == null && size == 0;
    }

    @Override
    public boolean contains(T object) {
        Node<T> currentNode = first;
        while (currentNode != null) {
            if (Objects.equals(currentNode.element, object)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public void add(T object) {
        linkLast(object);
        size++;
    }

    @Override
    public void add(int index, T object) {
        if (index == 0) {
            linkFirst(object);
        } else if (index >= size) {
            linkLast(object);
        } else {
            Node<T> node = nodeAt(index);
            linkBefore(object, node);
        }
        size++;
    }

    @Override
    public void remove(T object) {
        Node<T> currentNode = first;
        while (currentNode != null) {
            if (Objects.equals(currentNode.element, object)) {
                unlink(currentNode);
                size--;
                return;
            }
            currentNode = currentNode.next;
        }
    }

    @Override
    public void clear() {
        while (first != null) {
            Node<T> currentNode = first;
            first = currentNode.next;
            currentNode.element = null;
            currentNode.prev = null;
            currentNode.next = null;
        }
        last = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return nodeAt(index).element;
    }

    @Override
    public int indexOf(T object) {
        Node<T> currentNode = first;
        int index = 0;
        while (currentNode != null) {
            if (Objects.equals(currentNode.element, object)) {
                return index;
            }
            currentNode = currentNode.next;
            index++;
        }
        return 0;
    }

    @Override
    public int lastIndexOf(T object) {
        Node<T> currentNode = last;
        int index = size - 1;
        while (currentNode != null) {
            if (Objects.equals(currentNode.element, object)) {
                return index;
            }
            currentNode = currentNode.prev;
            index--;
        }
        return 0;
    }

    @Override
    public boolean offer(T object) {
        linkLast(object);
        size++;
        return true;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }

        T firstElement = get(0);
        remove(firstElement);
        return firstElement;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }

        return get(0);
    }

    private Node<T> nodeAt(int index) {
        Node<T> currentNode = first;
        if (index < size / 2) {
            for (int i = 0; i <= index; i++) {
                if (i == index) {
                    break;
                }
                currentNode = currentNode.next;
            }
        } else {
            currentNode = last;
            for (int i = size - 1; i >= size - 1 - index; i--) {
                if (i == index) {
                    break;
                }
                currentNode = currentNode.prev;
            }
        }
        return currentNode;
    }

    private void linkFirst(T object) {
        Node<T> newNode = new Node<>(object);
        if (size == 0) {
            first = last = newNode;
        } else {
            first.prev = newNode;
        }
        newNode.next = first;
        first = newNode;
    }

    private void linkLast(T object) {
        Node<T> newNode = new Node<>(object);
        if (size == 0) {
            first = last = newNode;
        } else {
            last.next = newNode;
        }
        newNode.prev = last;
        last = newNode;
    }

    private void linkBefore(T object, Node<T> node) {
        Node<T> prev = node.prev;
        Node<T> newNode = new Node<>(object);
        newNode.prev = prev;
        newNode.next = node;
        prev.next = newNode;
        node.prev = newNode;
    }

    private void unlink(Node<T> node) {
        Node<T> prev = node.prev;
        Node<T> next = node.next;

        if (prev != null) {
            prev.next = node.next;
        } else {
            first = next;
        }

        if (next != null) {
            next.prev = prev;
        } else {
            last = prev;
        }

        node.element = null;
        node.prev = null;
        node.next = null;
    }

    @Override
    public String toString() {
        Node<T> currentNode = first;
        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (currentNode != null) {
            if (index > 0) {
                builder.append(", ");
            }
            builder.append(currentNode.element);
            index++;
            currentNode = currentNode.next;
        }
        return "MyLinkedList: [" + builder.append("]");
    }
}