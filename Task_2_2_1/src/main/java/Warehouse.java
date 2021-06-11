import java.util.ArrayDeque;
import java.util.Queue;


public class Warehouse<T> {
    int capacity;
    Queue<T> queue;

    public Warehouse(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDeque<>(capacity);
    }

    synchronized public T popItem() {
        return queue.remove();
    }

    synchronized public void pushItem(T order) {
        if(getFreeSpace() != 0) {
            queue.add(order);
        } else {
            throw new ArrayStoreException("Storage is full");
        }
    }
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getFreeSpace() {
        return capacity - queue.size();
    }
}