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

class MarketClass {
    int quantity;
    double price;

    public MarketClass(int q, double p) {
        this.price = p;
        this.quantity = q;
    }
}

public class NaiveHedgeAlgo {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        String inputData = "100 1850.00 200 1850.25 300 1850.50\n" +
                "100 1849.75 200 1849.50 300 1849.25\n" +
                "+10 0.20\n" +
                "+15 -0.20\n" +
                "-40 0.50";
        DecimalFormat formatter = new DecimalFormat("0.00");

        List<String> lines = Arrays.asList(inputData.split("\n"));

        // buymarket parse
        String[] buyStr = lines.get(0).split("\\s+");
        List<MarketClass> buyMarket = new ArrayList<>();

        for (int i = 0; i < buyStr.length; i += 2) {
            int quantity = Integer.parseInt(buyStr[i]);
            double price = Double.parseDouble(buyStr[i + 1]);
            MarketClass marketClass = new MarketClass(quantity, price);
            buyMarket.add(marketClass);
        }
        // sellmarket parse
        String[] withinStr2 = lines.get(1).split("\\s+");
        List<MarketClass> sellMarket = new ArrayList<>();

        for (int i = 0; i < withinStr2.length; i += 2) {
            int quantity = Integer.parseInt(withinStr2[i]);
            double price = Double.parseDouble(withinStr2[i + 1]);
            MarketClass marketClass = new MarketClass(quantity, price);
            sellMarket.add(marketClass);
        }

        // all trades parse
        List<MarketClass> trades = new ArrayList<>();

        for (int i = 2; i < lines.size(); i++) {
            String[] withinStr3 = lines.get(i).split("\\s+");

            int quantity = Integer.parseInt(withinStr3[0]);
            double price = Double.parseDouble(withinStr3[1]);
            MarketClass marketClass = new MarketClass(quantity, price);
            trades.add(marketClass);
        }

        List<MarketClass> offsetTrade = getOffsetVal(buyMarket, sellMarket, trades);

        for (MarketClass m : offsetTrade) {
            System.out.println(m.quantity + " " + formatter.format(m.price));
        }
    }

    public static List<MarketClass> getOffsetVal(List<MarketClass> buyMarkets,
                                                 List<MarketClass> sellMarkets,
                                                 List<MarketClass> incomingTrades) {
        List<MarketClass> resultantTrades = new ArrayList<>();

        double totalRisk = 0;
        for (int i = 0; i < incomingTrades.size(); i++) {
            List<MarketClass> eachTrade = new ArrayList<>();
            MarketClass m = incomingTrades.get(i);
            totalRisk = totalRisk + (-1 * m.quantity * m.price);

            if (totalRisk > 0) {
                int iter = 0;
                while (Math.abs((int) totalRisk) > 0 && iter < buyMarkets.size()) {
                    MarketClass buyMarket = buyMarkets.get(iter);

                    if (buyMarket.quantity == 0) {
                        iter++;
                    } else {
                        if (totalRisk <= buyMarket.quantity) {
                            int absRisk = (int) totalRisk;
                            buyMarket.quantity += absRisk;
                            eachTrade.add(new MarketClass(absRisk, buyMarket.price));
                            totalRisk -= absRisk;
                        } else {
                            totalRisk += buyMarket.quantity;
                            eachTrade.add(new MarketClass(buyMarket.quantity, buyMarket.price));
                            buyMarket.quantity = 0;
                        }
                    }
                }
                double avgSum = 0;
                int q = 0;
                for (int j = 0; j < eachTrade.size(); j++) {
                    q = q + eachTrade.get(j).quantity;
                    avgSum += eachTrade.get(j).quantity * eachTrade.get(j).price;
                }
                resultantTrades.add(new MarketClass(q, avgSum / q));

            } else if (totalRisk < 0) {
                int iter = 0;
                while (Math.abs((int) totalRisk) > 0 && iter < sellMarkets.size()) {
                    MarketClass sellMarket = sellMarkets.get(iter);

                    if (sellMarket.quantity == 0) {
                        iter++;
                    } else {
                        if (totalRisk <= sellMarket.quantity) {
                            int absRisk = (int) totalRisk;
                            sellMarket.quantity += absRisk;
                            eachTrade.add(new MarketClass(absRisk, sellMarket.price));
                            totalRisk -= absRisk;
                        } else {
                            totalRisk += sellMarket.quantity;
                            eachTrade.add(new MarketClass(sellMarket.quantity, sellMarket.price));
                            sellMarket.quantity = 0;
                        }
                    }
                }
                double avgSum = 0;
                int q = 0;
                for (int j = 0; j < eachTrade.size(); j++) {
                    q = q + eachTrade.get(j).quantity;
                    avgSum = avgSum + eachTrade.get(j).quantity * eachTrade.get(j).price;
                }
                resultantTrades.add(new MarketClass(q, avgSum / q));
            }

        }


        return resultantTrades;
    }

}
