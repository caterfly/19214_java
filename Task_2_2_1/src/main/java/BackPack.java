import java.util.ArrayList;

public class BackPack {

    private int capacity;
    private ArrayList<Order> orders;

    public int getFreeSpace(){
        return capacity - orders.size();
    }
    public BackPack(int capacity) {
        this.capacity = capacity;
        this.orders = new ArrayList<>(capacity);
    }

    public void addOrder(Order order) throws ArrayStoreException {
        if (getFreeSpace() != 0) {
            orders.add(order);
        } else {
            throw new ArrayStoreException("Array is full");
        }
    }


    public void freeBag(){
        orders.clear();
    }

}