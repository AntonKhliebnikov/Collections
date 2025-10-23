package collection.my_list;

import collection.my_queue.MyQueue;

import java.util.Objects;

public class MyLinkedList implements MyList, MyQueue {
    private Node first;
    private Node last;
    private int size;

    public MyLinkedList() {
        this.first = null;
        this.last = null;
        size = 0;
    }

    private static class Node {
        Integer element;
        Node prev;
        Node next;

        public Node(Integer element) {
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
    public boolean contains(Integer object) {
        Node currentNode = first;
        while (currentNode != null) {
            if (Objects.equals(currentNode.element, object)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public void add(Integer object) {
        linkLast(object);
        size++;
    }

    @Override
    public void add(int index, Integer object) {
        if (index == 0) {
            linkFirst(object);
        } else if (index >= size) {
            linkLast(object);
        } else {
            Node node = nodeAt(index);
            linkBefore(object, node);
        }
        size++;
    }

    @Override
    public boolean remove(Integer object) {
        Node currentNode = first;
        while (currentNode != null) {
            if (Objects.equals(currentNode.element, object)) {
                unlink(currentNode);
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public void clear() {
        while (first != null) {
            Node currentNode = first;
            first = currentNode.next;
            currentNode.element = null;
            currentNode.prev = null;
            currentNode.next = null;
        }
        last = null;
        size = 0;
    }

    @Override
    public Integer get(int index) {
        Objects.checkIndex(index, size);
        return nodeAt(index).element;
    }

    @Override
    public int indexOf(Integer object) {
        Node currentNode = first;
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
    public int lastIndexOf(Integer object) {
        Node currentNode = last;
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
    public boolean offer(Integer object) {
        linkLast(object);
        size++;
        return true;
    }

    @Override
    public Integer poll() {
        if (isEmpty()) {
            return null;
        }

        Integer firstElement = get(0);
        remove(firstElement);
        return firstElement;
    }

    @Override
    public Integer peek() {
        if (isEmpty()) {
            return null;
        }

        return get(0);
    }

    private Node nodeAt(int index) {
        Node currentNode = first;
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

    private void linkFirst(Integer object) {
        Node newNode = new Node(object);
        if (size == 0) {
            first = last = newNode;
        } else {
            first.prev = newNode;
        }
        newNode.next = first;
        first = newNode;
    }

    private void linkLast(Integer object) {
        Node newNode = new Node(object);
        if (size == 0) {
            first = last = newNode;
        } else {
            last.next = newNode;
        }
        newNode.prev = last;
        last = newNode;
    }

    private void linkBefore(Integer object, Node node) {
        Node prev = node.prev;
        Node newNode = new Node(object);
        newNode.prev = prev;
        newNode.next = node;
        prev.next = newNode;
        node.prev = newNode;
    }

    private void unlink(Node node) {
        Node prev = node.prev;
        Node next = node.next;

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
        Node currentNode = first;
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