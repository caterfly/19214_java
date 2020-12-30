import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class MyTest {

    @Test
    public void test_1() {
        MyPriorityQueue<Integer, String> Q = new MyPriorityQueue<>(6);
        MyPriorityQueue.PriorityQueue result = null;

        Q.insert(200, "Собака");
        Q.insert(10, "Человек");
        result = Q.extractMax();

        MyPriorityQueue.PriorityQueue<Integer, String> expected = new MyPriorityQueue.PriorityQueue<>(200, "Собака");
        Assert.assertEquals(expected.toString(), result.toString());

        Q.insert(5, "Пингвин");
        Q.insert(500, "Попугай");
        result = Q.extractMax();

        expected = new MyPriorityQueue.PriorityQueue<>(500, "Попугай");
        Assert.assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void test_2() {
        MyPriorityQueue<Integer, String> Q = new MyPriorityQueue<>(0);
        try {
            Q.insert(5, "Пингвин");
        } catch (QueueSizeLimitException e) {
            return;
        }
        Assert.fail("\nExpected: queue is full. Have: queue is not full.");
    }

    @Test
    public void test_3() {
        MyPriorityQueue<Integer, String> Q = new MyPriorityQueue<>(3);
        try {
            Q.extractMax();
        } catch (QueueSizeLimitException e) {
            return;
        }
        Assert.fail("\nExpected: queue is empty. Have: queue is not empty.");
    }

    @Test
    public void test_4() {
        MyPriorityQueue<Character, Integer> Q = new MyPriorityQueue<>(4);
        MyPriorityQueue.PriorityQueue result = null;
        MyPriorityQueue.PriorityQueue<Character, Integer> expected = null;

        Q.insert('C', 3);
        Q.insert('A', 1);
        Q.insert('B', 2);

        result = Q.extractMax();
        expected = new MyPriorityQueue.PriorityQueue<>('C', 3);
        Assert.assertEquals(expected.toString(), result.toString());

        result = Q.extractMax();
        expected = new MyPriorityQueue.PriorityQueue<>('B', 2);
        Assert.assertEquals(expected.toString(), result.toString());

        result = Q.extractMax();
        expected = new MyPriorityQueue.PriorityQueue<>('A', 1);
        Assert.assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void test_5() {
        MyPriorityQueue<Integer, Integer> Q = new MyPriorityQueue<>(4);
        Object expected = null;

        Q.insert(2, 3);
        Q.insert(1, 1);
        Q.insert(3, 2);

        Iterator Qit = Q.iterator();
        for (Object e: Q) {
            expected = e;
        }

        Assert.assertEquals("Value: 1, Priority: 1", expected.toString());
    }

    @Test
    public void test_6() {
        MyPriorityQueue<Integer, Integer> Q = new MyPriorityQueue<>(4);

        Q.insert(2, 3);
        Q.insert(1, 1);
        Q.insert(3, 2);

        boolean full = Q.isFull();
        boolean empty = Q.isEmpty();
        int size = Q.size();

        Assert.assertEquals(true, full);
        Assert.assertEquals(false, empty);
        Assert.assertEquals(3, size);
    }

    @Test
    public void test_7() {
        MyPriorityQueue<Integer, Integer> Q = new MyPriorityQueue<>(4);

        Q.insert(2, 3);
        Q.insert(1, 1);
        Q.insert(3, 2);

        Q.clear();
        Assert.assertEquals(true, Q.isEmpty());
    }
}