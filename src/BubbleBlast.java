import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BubbleBlast {
    public static List<List<Integer>> makeBlast(List<List<Integer>> bubbles){
        int rowCount = bubbles.get(0).size();
        int colCount = bubbles.size();
        List<Pair> final_explosion = new ArrayList<>();
        for(int i =0;i<rowCount;i++){
            for(int j =0;j<colCount;j++){
                int count =0;
                List<Pair> neighbour = new ArrayList<>();

                int currentValue = bubbles.get(i).get(j);

                // Check left neighbor
                if (j > 0 && currentValue == bubbles.get(i).get(j - 1)) {
                    count++;
                    neighbour.add(new Pair(i,j-1));
                }

                // Check right neighbor
                if (j < bubbles.get(i).size() - 1 && currentValue == bubbles.get(i).get(j + 1)) {
                    count++;
                    neighbour.add(new Pair(i,j+1));
                }

                // Check above neighbor
                if (i > 0 && currentValue == bubbles.get(i - 1).get(j)) {
                    count++;
                    neighbour.add(new Pair(i-1,j));
                }

                // Check below neighbor
                if (i < bubbles.size() - 1 && currentValue == bubbles.get(i + 1).get(j)) {
                    count++;
                    neighbour.add(new Pair(i+1,j));
                }
                if(count>1){

                    final_explosion.add(new Pair(i,j));

                    for(Pair p : neighbour){
                        final_explosion.add(p);
                    }
                }

            }
        }
        for(Pair p : final_explosion){
            bubbles.get(p.x).set(p.y,0);
        }
        return bubbles;
    }

    public static List<List<Integer>> makeDrops(List<List<Integer>> bubbles) {
        int rowCount = bubbles.size();
        int colCount = bubbles.get(0).size();

        for (int j = 0; j < colCount; j++) {
            int emptyRow = rowCount - 1;

            for (int i = rowCount - 1; i >= 0; i--) {
                int currentValue = bubbles.get(i).get(j);

                if (currentValue != 0) {

                    bubbles.get(emptyRow).set(j, currentValue);

                    if (i != emptyRow) {
                        bubbles.get(i).set(j, 0);
                    }

                    emptyRow--;
                }
            }
        }

        return bubbles;
    }



        public static void main(String[] args) {
        List<List<Integer>> bubbles = new ArrayList<>();

        // Adding the nested lists to the main list
        bubbles.add(new ArrayList<>(List.of(3, 1, 2, 1)));
        bubbles.add(new ArrayList<>(List.of(1, 1, 1, 4)));
        bubbles.add(new ArrayList<>(List.of(3, 1, 2, 2)));
        bubbles.add(new ArrayList<>(List.of(3, 3, 3, 4)));

        // Printing the initial contents of the nested lists
        System.out.println("Initial grid:");
        for (List<Integer> bubble : bubbles) {
            System.out.println(bubble);
        }

        // Call the makeBlast method to perform the bubble blasting
        bubbles = makeBlast(bubbles);
        bubbles = makeDrops(bubbles);
        // Printing the contents of the nested lists after blasting
        System.out.println("Grid after blasting:");
        for (List<Integer> bubble : bubbles) {
            System.out.println(bubble);
        }
    }

}
