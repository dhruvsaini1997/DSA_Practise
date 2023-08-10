import java.util.*;

class Products{
    String productName;
    Double price;
    int productSize;

    List<String> rawMaterials;

    public Products(String productName, Double price, int productSize, List<String> rawMaterials){
        this.price=price;
        this.productName=productName;
        this.productSize=productSize;
        this.rawMaterials=rawMaterials;
    }

}
 class InputClass{
    String target_product;
    List<Products> productsList;
    public InputClass(String target_product, List<Products> productsList){
        this.productsList = productsList;
        this.target_product = target_product;
    }

}
public class ManufacturingProblem {
    double getMinCost(Map<String,Double> product_cost, Map<String,List<String>> raw_material_map,String target){
        double min = Double.MAX_VALUE;
        if(product_cost.get(target)!=null) {
            min = product_cost.get(target);
        }
        if(raw_material_map.get(target)!=null){

            double cost =0;
            for(String raw : raw_material_map.get(target)){

            cost=cost+ getMinCost(product_cost,raw_material_map,raw);
            }
            min = Math.min(min,cost);
        }
        return min;
    }
public double getMinCost(InputClass inputClass){
    String target = inputClass.target_product;
    Map<String, Double> product_cost_map = new HashMap<>();
    Map<String,List<String>> raw_material_map = new HashMap<>();
    for( Products product :  inputClass.productsList){
        product_cost_map.put(product.productName,product.price);
        raw_material_map.put(product.productName,product.rawMaterials);
    }
    double min = Double.MAX_VALUE;

    if(product_cost_map.containsKey(target) && product_cost_map.get(target)!=null){
        min = product_cost_map.get(target);
    }
    List<String> target_raw_material = raw_material_map.get(target);
    List<String> final_raw =new ArrayList<>();
    double raw_sum =0;


   for(String raw : target_raw_material){
       raw_sum = raw_sum + getMinCost(product_cost_map,raw_material_map,raw);
   }


    return min>raw_sum?raw_sum:min;
}

public static void main(String[] args){
        List<String> raw_mat = new ArrayList<>();

        Products prod1 = new Products("a",30000.,5,new ArrayList<>(Arrays.asList("b", "c","d","e","f")));
        Products prod2 = new Products("f",200.0,0,null);
        Products prod3 = new Products("c",null,2,new ArrayList<>(Arrays.asList("g","h","i")));
        Products prod4 = new Products("b", null, 4, new ArrayList<>(Arrays.asList("g","i","h")));
    Products prod5 = new Products("h",1300.0,2,null);
    Products prod6 = new Products("i",7000.0,0,null);
    Products prod7 = new Products("g",4000.,0,null);
    Products prod8 = new Products("d", 1000., 0, new ArrayList<>(Arrays.asList("h")));
    Products prod9 = new Products("e", 5000., 1, new ArrayList<>(Arrays.asList("j")));;
    Products prod10 = new Products("j", 2000., 0, new ArrayList<>(Arrays.asList("k")));
    Products prod11 = new Products("k", 0.0, 0,null);;



    List<Products> prodList = new ArrayList<>();
prodList.add(prod1);prodList.add(prod2);prodList.add(prod3);prodList.add(prod4);prodList.add(prod5);
prodList.add(prod6);prodList.add(prod7);prodList.add(prod8);prodList.add(prod9);prodList.add(prod10);prodList.add(prod11);

        InputClass in = new InputClass(
                "a",
                prodList

        );
    ManufacturingProblem problem = new ManufacturingProblem();
    double minCost = problem.getMinCost(in);

    System.out.println("Minimum Cost: " + minCost);
}
}
