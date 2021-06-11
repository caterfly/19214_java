import org.junit.Test;

import java.util.ArrayList;

public class TestBakery {

    @Test

    public void test_1() throws InterruptedException {
        init();
        Bakery bakery = new Bakery(couriers, bakers, warehouse, orders);
        bakery.startDay();
        Thread.sleep(100);
        bakery.finishDay();
    }



    ArrayList<Courier> couriers;
    ArrayList<Baker> bakers;
    Warehouse<Order> warehouse;
    Warehouse<Order> orders;


    public void init() {
        int courNum = 5;
        int bakerNum = 5;

        warehouse = new Warehouse<>(15);
        orders = new Warehouse<>(25);
        couriers = new ArrayList<>();
        bakers = new ArrayList<>();

        for(int i = 0; i < courNum; i++){
            couriers.add(new Courier(i, (i % 4) + 1, warehouse,true));
        }
        for(int i = 0; i < bakerNum; i++){
            bakers.add(new Baker(i, orders, warehouse, true));
        }
    }
}