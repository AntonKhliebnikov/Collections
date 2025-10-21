package collection.my_queue;

public interface MyQueue {
    boolean offer(Integer object);
    Integer poll();
    Integer peek();
}
