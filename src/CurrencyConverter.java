import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CurrencyConverter {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] conversion = reader.readLine().split(";");
        String currency1 = reader.readLine();
        String currency2 = reader.readLine();

        if (currency2.equals(currency1)) {
            System.out.println(0);
            return;
        }

        Map<String, Map<String, Double>> conversionMap = new HashMap<>();
        for (String temp : conversion) {
            String[] conversionData = temp.split(",");
            String fromCurrency = conversionData[0];
            String toCurrency = conversionData[1];
            double rate = Double.parseDouble(conversionData[2]);

            conversionMap.computeIfAbsent(fromCurrency, k -> new HashMap<>()).put(toCurrency, rate);
            conversionMap.computeIfAbsent(toCurrency, k -> new HashMap<>()).put(fromCurrency, 1.0 / rate);
        }

        double maxConversionRate = bfsMaxConversionRate(currency1, currency2, conversionMap);
        System.out.println(maxConversionRate);
    }

    private static double bfsMaxConversionRate(String startCurrency, String targetCurrency,
                                               Map<String, Map<String, Double>> conversionMap) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(startCurrency);

        Map<String, Double> maxRateMap = new HashMap<>();
        maxRateMap.put(startCurrency, 1.0);

        while (!queue.isEmpty()) {
            String currentCurrency = queue.poll();

            if (currentCurrency.equals(targetCurrency)) {
                return maxRateMap.get(currentCurrency);
            }

            for (Map.Entry<String, Double> conversion : conversionMap.get(currentCurrency).entrySet()) {
                String nextCurrency = conversion.getKey();
                double rate = conversion.getValue() * maxRateMap.get(currentCurrency);

                if (!maxRateMap.containsKey(nextCurrency) || rate > maxRateMap.get(nextCurrency)) {
                    maxRateMap.put(nextCurrency, rate);
                    queue.offer(nextCurrency);
                }
            }
        }

        return -1.0; // No conversion path found
    }
}
