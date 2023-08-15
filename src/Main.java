import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.text.DecimalFormat;
/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */

class Trade_struct {
    int key;
    double value;
    int quantity;
    int sequence_number;

    public Trade_struct(int key, double value, int quantity, int sequence_number) {
        this.key = key;
        this.value = value;
        this.quantity = quantity;
        this.sequence_number = sequence_number;
    }
}



public class Main {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line = "1,2000,5,1;1,2050,5,2;2,3000,10,3";

        List<Trade_struct> trades = new ArrayList<>();

        String[] strArr = line.split(";");

        for (String t : strArr) {
            String[] str_com = t.split(",");
            if (str_com.length == 4) {
                Trade_struct trade = new Trade_struct(Integer.parseInt(str_com[0]),Double.parseDouble(str_com[1]),
                        Integer.parseInt(str_com[2]),Integer.parseInt(str_com[3]));

                trades.add(trade);
            }
        }
        getWeightedAvg(trades);

    }

    public static void getWeightedAvg(List<Trade_struct> tradesList) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//        Map<Integer, List<Double>> finalList = new HashMap<>();

        Map<Integer, Double> weightMap = new HashMap<>();
        Map<Integer, Integer> seqMap = new HashMap<>();
        Map<Integer, Integer> quantityMap = new HashMap<>();

        Integer maxSeq = null;

        for (Trade_struct t : tradesList) {
            if (weightMap.containsKey(t.key) && maxSeq != null && maxSeq < t.sequence_number) {
                double weightedAvg = ((weightMap.get(t.key) * quantityMap.get(t.key))
                        + (t.value * t.quantity)) / (t.quantity + quantityMap.get(t.key));
                int quantity = t.quantity + quantityMap.get(t.key);


//                finalList.get(t.key).add(weightedAvg);
                System.out.println(t.key+": "+decimalFormat.format(weightedAvg));

                weightMap.put(t.key, weightedAvg);
                quantityMap.put(t.key, quantity);
                maxSeq = t.sequence_number;
            }
            else if (!weightMap.containsKey(t.key) && (maxSeq == null || t.sequence_number > maxSeq)) {
                weightMap.put(t.key, t.value);
                seqMap.put(t.key, t.sequence_number);
                quantityMap.put(t.key, t.quantity);


                maxSeq = t.sequence_number;
                System.out.println(t.key+": "+decimalFormat.format(t.value));
//                finalList.put(t.key, new ArrayList<>(Arrays.asList(t.value)));
            }
        }

    }



}
