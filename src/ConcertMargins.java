import java.net.Inet4Address;
import java.util.*;

class Order {
    int timestamp;
    int concertId;
    boolean buy;
    int quantity;

    public Order(int timestamp, int concertId, boolean buy, int quantity) {
        this.timestamp = timestamp;
        this.concertId = concertId;
        this.buy = buy;
        this.quantity = quantity;
    }
}

class PriceUpdate {
    int concertId;
    int timestamp;
    int delta;

    public PriceUpdate(int timestamp, int concertId, int delta) {
        this.concertId = concertId;
        this.timestamp = timestamp;
        this.delta = delta;
    }
}

public class ConcertMargins {
    public static Map<Integer, Integer> ticketsMargin(int initialPrice, List<Order> orders, List<PriceUpdate> feed) {
        HashMap<Integer,Integer> finalSales = new HashMap<>();
        //fetch unique concertIDS
        Set<Integer> uniqueIDs = new HashSet<>();
        for (PriceUpdate update : feed) {
            uniqueIDs.add(update.concertId);
        }

        for (Order o : orders) {
            uniqueIDs.add(o.concertId);
        }
        //Initial price
        Map<Integer, Integer> concertPrices = new HashMap<>();
        for(Integer i : uniqueIDs){
            concertPrices.put(i,initialPrice);
        }
        for( PriceUpdate change : feed){
            Iterator<Order> orderIterator = orders.iterator();

            while (orderIterator.hasNext()){
                Order o = orderIterator.next();
                if(o.timestamp < change.timestamp) {
                    if (o.buy) {
                        o.quantity *= -1;
                    }
                    int concertId = o.concertId;
                    int oldSales = finalSales.getOrDefault(concertId, 0);
                    int updatedSales = oldSales + o.quantity * concertPrices.getOrDefault(concertId, 0);
                    finalSales.put(concertId, updatedSales);
                    orderIterator.remove();
                }
            }
            int updatedPrice = concertPrices.getOrDefault(change.concertId, 0) + change.delta;
            concertPrices.put(change.concertId, updatedPrice);
        }
        // place remaining orders
        for(Order o : orders){
            finalSales.put(o.concertId, finalSales.getOrDefault(o.concertId,0)+concertPrices.get(o.concertId)*o.quantity);
        }

        return finalSales;



    }

    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        List<PriceUpdate> feed = new ArrayList<>();

        orders.add(new Order(0, 2, true, 15));
        orders.add(new Order(4, 1, false, 30));
        orders.add(new Order(6, 1, true, 10));
        orders.add(new Order(10, 2, true, 5));
        orders.add(new Order(15, 4, false, 100));

        feed.add(new PriceUpdate(3, 1, +4));
        feed.add(new PriceUpdate(5, 1, -1));
        feed.add(new PriceUpdate(8, 2, +2));
        feed.add(new PriceUpdate(12, 3, -5));


        Map<Integer, Integer> results = ticketsMargin(10, orders, feed);

        for (Integer entry : results.keySet()) {
                System.out.println("(" + entry + ", " + results.get(entry) + ")");

        }
    }
}
