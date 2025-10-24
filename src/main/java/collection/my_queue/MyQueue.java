package collection.my_queue;

public interface MyQueue<T> {
    boolean offer(T object);
    T poll();
    T peek();
}
