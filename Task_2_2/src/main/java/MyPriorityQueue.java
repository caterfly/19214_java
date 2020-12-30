import java.util.Iterator;

/**
 * This class implements custom priority queue
 * @pre Functions should be used after priority queue initialization (use myPriorityQueue(size))
 * @post insert and extractMax functions could throw QueueSizeLimitException
 * @param <K> Type of key
 * @param <V> Type of value
 */
public class MyPriorityQueue<K extends Comparable<K>,V> implements Iterable {

    static class PriorityQueue<T1 extends Comparable<T1>, T2> implements Comparable<PriorityQueue>
    {
        private T1 key;
        private T2 value;

        PriorityQueue(T1 key, T2 value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Value: " + value + ", Priority: " + key;
        }

        @SuppressWarnings("unchecked")
        public int compareTo(PriorityQueue o) {
            return this.key.compareTo((T1)o.key);
        }
    }

    private PriorityQueue<K, V>[] array;
    private int size, capacity;

    @SuppressWarnings("unchecked")
    public MyPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.array = new PriorityQueue[this.capacity];
    }

    /**
     * Check if priority queue has no elements in it
     * @return returns true if queue is empty, otherwise false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check if priority queue size equals to number of elements in it
     * @return returns true if queue is full, otherwise false
     */
    public boolean isFull() {
        return size == capacity - 1;
    }

    /**
     * This function returns current number of elements in priority queue
     * @return integer value of current number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Delete all elements from current priority queue
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        array = new PriorityQueue[capacity];
        size = 0;
    }

    /**
     * This function inserts a pair (key, value) into priority queue
     * @pre should be used after queue initialization
     * @param key key value
     * @param value information to store
     * @throws QueueSizeLimitException if queue is full
     */
    @SuppressWarnings("unchecked")
    public void insert(K key, V value) {

        if(isFull() || capacity == 0)
            throw new QueueSizeLimitException("Queue is full!");

        PriorityQueue newElem = new PriorityQueue(key, value);
        array[++size] = newElem;
        int position = size;

        while (position != 1 && newElem.compareTo(array[position / 2]) > 0) {
            array[position] = array[position / 2];
            position /= 2;
        }

        array[position] = newElem;
    }

    /**
     * This function returns pair with highest possible key-value from user
     * priority queue
     * @pre should be used after queue initialization
     * @return returns pair (key, value) if queue is not empty
     * @throws QueueSizeLimitException if queue is empty
     */
    public PriorityQueue extractMax() {
        int parent, child;
        PriorityQueue<K, V> item, tmp;

        if(isEmpty()) {
            throw new QueueSizeLimitException("Queue is empty!");
        }

        item = array[1];
        tmp = array[size--];

        parent = 1; child = 2;
        while(child <= size) {
            if(child < size && array[child].compareTo(array[child + 1]) < 0)
                child++;
            if(tmp.compareTo(array[child]) > 0)
                break;

            array[parent] = array[child];
            parent = child;
            child *= 2;
        }
        array[parent] = tmp;

        return item;
    }

    class QueueIterator implements Iterator {
        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        public PriorityQueue next() {
            if(hasNext())
                return extractMax();
            else
                return null;
        }
    }

    public Iterator iterator() { return new QueueIterator();}

}

class QueueSizeLimitException extends RuntimeException {
    QueueSizeLimitException(String errorMessage) {
        super(errorMessage);
    }
}