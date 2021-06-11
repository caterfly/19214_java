import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {
    final Warehouse<Order> orders;

    boolean isWorking;

    public Producer(Warehouse<Order> orders, boolean isWorking) {
        this.orders = orders;
        this.isWorking = isWorking;
    }

    public void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public void startOrderGeneration() throws InterruptedException {
        int id = 0;
        while (true) {
            synchronized (orders) {
                while (orders.getFreeSpace() == 0 && isWorking) {
                    orders.wait();
                }
                if (!isWorking) {
                    orders.notifyAll();
                    break;
                }
                if(orders.isEmpty()){
                    orders.notifyAll();
                }
                int cooking_duration = ThreadLocalRandom.current().nextInt(100, 1000);
                int delivery_duration = ThreadLocalRandom.current().nextInt(100, 1000);
                Order new_ord = new Order(id, cooking_duration, delivery_duration);
                orders.pushItem(new_ord);
                id++;

            }
        }
        System.out.println("Producer done all deliveries");
    }

    @Override
    public void run() {
        try {
            startOrderGeneration();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}