import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Products_test {
    String productName;
    Double price;
    int productSize;
    List<String> rawMaterials;

    public Products_test(String productName, Double price, int productSize, List<String> rawMaterials) {
        this.price = price;
        this.productName = productName;
        this.productSize = productSize;
        this.rawMaterials = rawMaterials;
    }

    public static void main(String[] args) {
        String inputString = "painted glass eyeball, 10.5, 2, glass; paint \n" +
                " glass, 5, 0,\n" +
                " paint, 4, 0, \n" +
                "teddy bear, null, 4, painted glass eyeball; tiny shirt; faux bear fur fabric; sewing thread \n" +
                "faux bear fur fabric, 15, 2, bear;yarn \n" +
                "bear, 100, 0, \n" +
                "yarn, 2, 0, \n" +
                "sewing thread, 13, 0,\n" +
                "tiny shirt, 24, 0";

        List<Products_test> productsList = new ArrayList<>();
        String[] lines = inputString.split("\n");

        for (String line : lines) {
            String[] parts = line.trim().split(",");

            if (parts.length < 3) {
                continue; // Skip invalid entries
            }

            String productName = parts[0].trim();
            Double price = parts[1].trim().equalsIgnoreCase("null") ?
                    null : Double.parseDouble(parts[1].trim());
            int productSize = Integer.parseInt(parts[2].trim());

            List<String> rawMaterials = new ArrayList<>();
            for (int i = 3; i < parts.length; i++) {
                rawMaterials.addAll(Arrays.asList(parts[i].trim().split(";")));
            }

            productsList.add(new Products_test(productName, price, productSize, rawMaterials));
        }

        // Now you have a list of Products instances
        // You can access and use them as needed
        for (Products_test product : productsList) {
            System.out.println("Product: " + product.productName);
            System.out.println("Price: " + product.price);
            System.out.println("Size: " + product.productSize);
            System.out.println("Raw Materials: " + product.rawMaterials);
            System.out.println();
        }
    }
}
