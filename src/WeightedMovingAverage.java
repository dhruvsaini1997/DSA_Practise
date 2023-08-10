import java.util.*;

class Trade {
    int key;
    double value;
    int quantity;
    int sequence;

    public Trade(int key, double value, int quantity, int sequence) {
        this.key = key;
        this.quantity = quantity;
        this.sequence = sequence;
        this.value = value;
    }
}

public class WeightedMovingAverage {
    Map<Integer, List<Double>> getWeightedAvg(List<Trade> trades) {
        Map<Integer, List<Double>> finalList = new HashMap<>();

        Map<Integer, Double> weightMap = new HashMap<>();
        Map<Integer, Integer> seqMap = new HashMap<>();
        Map<Integer, Integer> quantityMap = new HashMap<>();
        Integer maxSeq = null;

        for (Trade t : trades) {
            if (weightMap.containsKey(t.key) && maxSeq != null && maxSeq < t.sequence) {
                double weightedAvg = ((weightMap.get(t.key) * quantityMap.get(t.key))
                        + (t.value * t.quantity)) / (t.quantity + quantityMap.get(t.key));
                int quantity = t.quantity + quantityMap.get(t.key);

                // Updating results
                finalList.get(t.key).add(weightedAvg);

                // Updating maps
                weightMap.put(t.key, weightedAvg);
                quantityMap.put(t.key, quantity);
                maxSeq = t.sequence;
            } else if (!weightMap.containsKey(t.key) && (maxSeq == null || t.sequence > maxSeq)) {
                weightMap.put(t.key, t.value);
                seqMap.put(t.key, t.sequence);
                quantityMap.put(t.key, t.quantity);

                // Final list
                maxSeq = t.sequence;
                finalList.put(t.key, new ArrayList<>(Arrays.asList(t.value)));
            }
        }

        return finalList;
    }

    public static void main(String[] args) {
        WeightedMovingAverage wma = new WeightedMovingAverage();

        // Test 1
        List<Trade> trades = new ArrayList<>();
        System.out.println("Test 1:");
        trades.add(new Trade(1, 2000, 5, 1));
        trades.add(new Trade(1, 2050, 5, 2));
        trades.add(new Trade(2, 3000, 10, 3));
        Map<Integer, List<Double>> weightedAvgMap = wma.getWeightedAvg(trades);
        System.out.println(weightedAvgMap);

        // Test 2
        List<Trade> trades2 = new ArrayList<>();
        System.out.println("Test 2:");
        trades2.add(new Trade(1, 2000, 5, 3));
        trades2.add(new Trade(2, 2040, 5, 2));
        System.out.println(wma.getWeightedAvg(trades2));

        // Test 3
        List<Trade> trades3 = new ArrayList<>();
        System.out.println("Test 3:");
        trades3.add(new Trade(1, 2000, 5, 2));
        trades3.add(new Trade(1, 2050, 5, 4));
        System.out.println(wma.getWeightedAvg(trades3));

        // Test 4
        List<Trade> trades4 = new ArrayList<>();
        System.out.println("Test 4:");
        trades4.add(new Trade(1, 2000, 5, 1));
        trades4.add(new Trade(2, 2050, 15, 2));
        System.out.println(wma.getWeightedAvg(trades4));
    }
}
