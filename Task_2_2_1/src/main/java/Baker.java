public class Baker extends Thread {
    private int id;

    private final Warehouse<Order> orderQueue;
    private final Warehouse<Order> warehouse;
    private boolean isWorking;


    public Baker(int id, Warehouse<Order> orderQueue, Warehouse<Order> warehouse, boolean isWorking){
        this.isWorking = isWorking;
        this.id = id;
        this.orderQueue = orderQueue;
        this.warehouse = warehouse;
    }

    public void setWorking(boolean isWorking){
        this.isWorking = isWorking;
    }


    public void startJob() throws InterruptedException {

        while(true) {
            Order order;
            synchronized (orderQueue) {
                while (orderQueue.isEmpty() && isWorking){
                    orderQueue.wait();
                }
                if(!isWorking){
                    orderQueue.notifyAll();
                    break;
                }
                if(orderQueue.getFreeSpace() == 0){
                    orderQueue.notifyAll();
                }
                order = orderQueue.popItem();
            }
            System.out.format("Baker-%d started to cook order: %d\n",id, order.id);
            //Thread.sleep(order.cookingDuration);

            synchronized (warehouse) {
                while (warehouse.getFreeSpace() == 0 && isWorking){
                    warehouse.wait();
                }
                if(!isWorking){
                    warehouse.notifyAll();
                    break;
                }
                if(warehouse.isEmpty()){
                    warehouse.notifyAll();
                }
                System.out.format("Baker-%d done order: %d\n", id, order.id);
                warehouse.pushItem(new Order(order.id, order.cookingDuration, order.deliveryDuration));
            }
        }
        System.out.format("Bakery-%d finished job\n", id);
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
