public class Courier extends Thread {
    private int id;
    private BackPack bag;
    private final Warehouse<Order> warehouse;
    volatile private boolean isWorking;

    public Courier(int id, int capacity, Warehouse<Order> warehouse, boolean isWorking){
        this.isWorking = isWorking;
        this.id = id;
        this.bag = new BackPack(capacity);
        this.warehouse = warehouse;
    }
    public void setWorking(boolean isWorking){
        this.isWorking = isWorking;
    }
    public void startJob() throws InterruptedException {
        int delivery_time = 0;
        while (true) {
            synchronized (warehouse){
                while (warehouse.isEmpty() && isWorking){
                    warehouse.wait();
                }
                if(!isWorking){
                    warehouse.notifyAll();
                    break;
                }
                if(warehouse.getFreeSpace() == 0){
                    warehouse.notifyAll();
                }
                while (bag.getFreeSpace() != 0 && !warehouse.isEmpty()){
                    Order item = warehouse.popItem();
                    bag.addOrder(item);
                    delivery_time += item.deliveryDuration;
                }
            }
            System.out.format("Courier-%d is delivering orders\n", id);
            //Thread.sleep(delivery_time);
            System.out.format("Courier-%d delivered orders\n", id);
            bag.freeBag();
        }
        System.out.format("Courier-%d finished job\n", id);
    }

    @Override
    public void run() {
        try {
            startJob();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}