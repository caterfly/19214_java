import java.util.ArrayList;

public class Bakery {

    ArrayList<Courier> couriers;
    ArrayList<Baker> bakers;
    Warehouse<Order> warehouse;
    Warehouse<Order> orders;
    Producer producer;

    public Bakery(ArrayList<Courier> couriers, ArrayList<Baker> bakers, Warehouse<Order> warehouse, Warehouse<Order> orders) {
        this.couriers = couriers;
        this.bakers = bakers;
        this.warehouse = warehouse;
        this.orders = orders;
    }

    public void startDay(){
        for(Courier courier: couriers){
            courier.start();
        }
        for(Baker baker: bakers){
            baker.start();
        }
        producer = new Producer(orders, true);
        producer.start();
    }

    public void finishDay() throws InterruptedException {
        for(Courier courier: couriers){
            courier.setWorking(false);
        }
        for(Baker baker: bakers){
            baker.setWorking(false);
        }

        producer.setWorking(false);
        for(Courier courier: couriers){
            courier.join();
        }

        for(Baker baker: bakers){
            baker.join();
        }
        //producer.join();
    }
}