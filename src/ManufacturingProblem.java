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


        Map<String, Double> product_cost_map = new HashMap<>();
        Map<String,List<String>> raw_material_map = new HashMap<>();

        for( Product product :  allProducts){
            product_cost_map.put(product.ProductName,product.PriceToPurchase);
            raw_material_map.put(product.ProductName,new ArrayList<>(product.InputProducts));
        }

        double min = Double.MAX_VALUE;

        if(product_cost_map.containsKey(targetProductName) && product_cost_map.get(targetProductName)!=null){
            min = product_cost_map.get(targetProductName);
        }
        List<String> target_raw_material = raw_material_map.get(targetProductName);
        List<String> final_raw =new ArrayList<>();
        double raw_sum =0;


        for(String raw : target_raw_material){
            raw_sum = raw_sum + getMinCost(product_cost_map,raw_material_map,raw);
        }

        return min>raw_sum?raw_sum:min;

    }

    public static double getMinCost(Map<String,Double> product_cost, Map<String,List<String>> raw_material_map, String target){
        double min = Double.MAX_VALUE;
        if(product_cost.get(target)!=null) {
            min = product_cost.get(target);
        }
        if(raw_material_map.get(target).size()!=0){

            double cost =0;
            for(String raw : raw_material_map.get(target)){

                cost=cost+ getMinCost(product_cost,raw_material_map,raw);
            }
            min = Math.min(min,cost);
        }
        return min;
    }





}
