import java.util.ArrayList;
import java.util.List;

class MarketClass{
    int quantity;
    double price;
    public MarketClass(int q,double p){
        this.price=p;
        this.quantity=q;
    }
}
public class NaiveHedgeAlgo {

    public static  List<MarketClass> getOffsetVal(List<MarketClass> buyMarketClass,List<MarketClass> sellMarketClass,List<MarketClass> incTrade){
            List<MarketClass> res = new ArrayList<>();

            double totalRisk=0;
            for(int i =0;i<incTrade.size();i++){
                List<MarketClass> eachTrade = new ArrayList<>();
                MarketClass m = incTrade.get(i);
                totalRisk = totalRisk+(-1* m.quantity*m.price);

                if(totalRisk<0){
                    int iter =0;
                    while(Math.abs((int)totalRisk) > 0 && iter<sellMarketClass.size()){
                        MarketClass sellMarket  = sellMarketClass.get(iter);

                        if(sellMarket.quantity==0){
                            iter++;
                        }
                        else{
                            if(totalRisk<=sellMarket.quantity){
                                int absRisk = (int)totalRisk;
                                sellMarket.quantity +=absRisk;
                                eachTrade.add(new MarketClass(absRisk,sellMarket.price));
                                totalRisk-=absRisk;
                            }
                            else{
                            totalRisk = totalRisk+sellMarket.quantity;
                            eachTrade.add(new MarketClass(sellMarket.quantity,sellMarket.price));
                            sellMarket.quantity=0;
                            }
                        }
                    }
                    double avgSum=0;
                    int q=0;
                    for(int j =0;j<eachTrade.size();j++){
                        q=q+eachTrade.get(j).quantity;
                        avgSum = avgSum + eachTrade.get(j).quantity*eachTrade.get(j).price;
                    }
                    res.add(new MarketClass(q,avgSum/q));
                } else if (totalRisk>0) {
                    int iter =0;
                    while(Math.abs((int)totalRisk) > 0 && iter<buyMarketClass.size()){
                        MarketClass buyMarket  = buyMarketClass.get(iter);

                        if(buyMarket.quantity==0){
                            iter++;
                        }
                        else{
                            if(totalRisk<=buyMarket.quantity){
                                int absRisk = (int)totalRisk;
                                buyMarket.quantity +=absRisk;
                                eachTrade.add(new MarketClass(absRisk,buyMarket.price));
                                totalRisk-=absRisk;
                            }
                            else{
                                totalRisk = totalRisk+buyMarket.quantity;
                                eachTrade.add(new MarketClass(buyMarket.quantity,buyMarket.price));
                                buyMarket.quantity=0;
                            }
                        }
                    }
                    double avgSum=0;
                    int q=0;
                    for(int j =0;j<eachTrade.size();j++){
                        q=q+eachTrade.get(j).quantity;
                        avgSum = avgSum + eachTrade.get(j).quantity*eachTrade.get(j).price;
                    }
                    res.add(new MarketClass(q,avgSum/q));

                }

            }


            return res;
    }
    public static void main(String[] args) {
        System.out.println("Test 1: ");
        List<MarketClass> buyMarketClasses = new ArrayList<>();
        buyMarketClasses.add(new MarketClass(100, 1850));
        buyMarketClasses.add(new MarketClass(200, 1850.25));
        buyMarketClasses.add(new MarketClass(300, 1850.5));

        List<MarketClass> sellMarketClasses = new ArrayList<>();
        sellMarketClasses.add(new MarketClass(100, 1849.75));
        sellMarketClasses.add(new MarketClass(200, 1849.5));
        sellMarketClasses.add(new MarketClass(300, 1849.25));

        List<MarketClass> trades = new ArrayList<>();
        trades.add(new MarketClass(10, .2));
        trades.add(new MarketClass(15, -.2));
        trades.add(new MarketClass(-40, .5));

        List<MarketClass> finalList1 = getOffsetVal(buyMarketClasses, sellMarketClasses, trades);

        for (int i = 0; i < finalList1.size(); i++) {
            System.out.println(finalList1.get(i).quantity + " " + finalList1.get(i).price);
        }
    }
}
