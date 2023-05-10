import java.util.ArrayList;
import java.util.List;

public class DP_MaxCurrencyExchangeValue {

    public static double findMaxValue(double[][] rates, int src, int trgt){
       int len = rates.length;
       double[][] C = new double[len][len];

       for(int i = 0;i<len;i++){
           for(int j = 0 ;j<len;j++){
            C[i][j] = rates[i][j];
           }
       }
       for(int l = 2;l<len;l++){
           for(int i = 0 ;i<len-l;i++){
               int j = i+l;
              for(int k =i+1; k<j;k++){

                  C[i][j] = Math.max(C[i][j],C[i][k]*C[k][j]);
                  System.out.println("i ="+i+" j="+j+"  k = " +k+"   C="+C[i][j] +" rates = "+rates[i][k]*rates[k][j]);
              }

           }
       }
       return C[src-1][trgt-1];
    }

    public static void main(String[] args){
        double[][] exchangeRates = {
                {1,83,1.48,0.91},
                {.01,1,0.018,0.02},
                {.68,55.56,1,0.62},
                {1.1,89.98,1.62,1}
        };
        int src =1;
        int trgt = 4;
        System.out.println("Best exchange value from currency "+src+" to currency "+trgt+" ="+findMaxValue(exchangeRates,src,trgt));
    }
}
