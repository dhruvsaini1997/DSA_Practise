import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Arrays;
import java.util.*;


public class ManufacturingProblem
{
    private static DecimalFormat formatter = new DecimalFormat("0.00");

    public static class Product
    {
        public Product(String productName, Double priceToPurchase, HashSet<String> inputProducts)
        {
            ProductName = productName;
            PriceToPurchase = priceToPurchase;
            InputProducts = inputProducts;
        }

        public String ProductName;
        public Double PriceToPurchase;
        public HashSet<String> InputProducts;

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
                return true;

            if(obj == null || obj.getClass()!= this.getClass())
                return false;

            Product product = (Product) obj;

            return (product.ProductName == this.ProductName);
        }

        @Override
        public int hashCode()
        {
            return this.ProductName.hashCode();
        }
    }

    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        String inputData = "car\n" +
                "car,30000,5,seat;steering wheel;carpet;windshield;radio\n" +
                "radio,200,0,\n" +
                "steering wheel,null,3,leather;plastic;foam\n" +
                "seat,null,3,leather;foam;plastic\n" +
                "plastic,1300,0,\n" +
                "foam,7000,0,\n" +
                "leather,4000,0,\n" +
                "carpet,1000,1,plastic\n" +
                "windshield,5000,1,glass\n" +
                "glass,2000,1,sand\n" +
                "sand,0,0,";

        String[] lines = inputData.split("\n");
        String targetProductName = lines[0]; // First line is the target product
        HashSet<Product> allProducts = new HashSet<Product>();

        for (int i = 1; i < lines.length; i++) {
            String[] lineSplit = lines[i].split(",");
            allProducts.add(new Product(lineSplit[0], lineSplit[1].equals("null") ? null : Double.parseDouble(lineSplit[1]),
                    lineSplit.length == 3 ? new HashSet<String>() : new HashSet<String>(Arrays.asList(lineSplit[3].split(";")))));
        }

        System.out.println(formatter.format(mySolution(targetProductName, allProducts)));
    }

    static Double mySolution(String targetProductName, HashSet<Product> allProducts)
    {
        // ADD IMPLEMENTATION HERE
        double minCost = Double.MAX_VALUE;

        Map<String, Double> productCostMap = new HashMap<>();
        Map<String,HashSet<String>> productRawMaterialMap = new HashMap<>();

        for( Product p :  allProducts) {
            productCostMap.put(p.ProductName, p.PriceToPurchase);
            productRawMaterialMap.put(p.ProductName, p.InputProducts);
        }

        // if target cost is not null, make it default minCost

        if(productCostMap.containsKey(targetProductName)
                && productCostMap.get(targetProductName)!=null){
            minCost = productCostMap.get(targetProductName);
        }

        //Taking raw material required for target
        HashSet<String> target_raw_material = productRawMaterialMap.get(targetProductName);
        double rawMaterialSum =0;

        for(String raw : target_raw_material){
            rawMaterialSum = rawMaterialSum + getMinCost(productCostMap,productRawMaterialMap,raw);
        }

        if(minCost>rawMaterialSum){
            return rawMaterialSum;
        }

        return minCost;

    }

    public static double getMinCost(Map<String,Double> productCostMap, Map<String,HashSet<String>> productRawMaterialMap, String targetProduct){

        double min = Double.MAX_VALUE;
        Double targetCost = productCostMap.get(targetProduct);
        if(targetCost!=null) {
            min = targetCost;
        }

        Set<String> targetRawMaterials = productRawMaterialMap.get(targetProduct);

        if(targetRawMaterials!=null && !targetRawMaterials.isEmpty()){

            double cost = 0;
            for(String raw : targetRawMaterials){

                cost += getMinCost(productCostMap,productRawMaterialMap,raw);
            }
            min = Math.min(min,cost);
        }
        return min;
    }





}
