import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShipAttach {
    public static String[] solution(String[][] grid, int[][] shots){

        int col = shots.length;

        String[] res = new String[col];

        Map<String,Integer> map = new HashMap<>();

        for(int i =0;i<grid.length;i++){
            for(int j =0;j<grid[0].length;j++){
                if(grid[i][j]=="."){
                    continue;
                }
                if(map.containsKey(grid[i][j])){
                    map.put(grid[i][j],map.get(grid[i][j])+1);
                }
                else{
                    map.put(grid[i][j],1);
                }
            }
        }

        for(int i =0;i<col;i++){
            String str = grid[shots[i][0]][shots[i][1]];
           if(str.equals(".")){
               res[i]="Missed";
           } else if (map.containsKey(str) && map.get(str)>1) {
               grid[shots[i][0]][shots[i][1]]="!";
               res[i]="Attacked ship "+str;
               map.put(str,map.get(str)-1);
           } else if (map.containsKey(str) && map.get(str)==1) {
               grid[shots[i][0]][shots[i][1]]="!";
               res[i]="Ship "+str+" sunk";
               map.put(str,map.get(str)-1);
           } else if (!map.containsKey(str) && str=="!" ) {
               res[i]="Already attacked";
           }
        }
        return res;
    }
    public static void main(String[] args){
        String[][] grid = {
                { ".", "A", "B", "B", "B" },
                { ".", "A", ".", ".", "C" },
                { ".", ".", ".", ".", "." },
                { "D", "D", ".", ".", "." },
                { ".", "E", "E", "E", "E" }
        };
        int[][] shots = {
                {0, 0}, {0, 1}, {0, 2}, {1, 1}, {0, 1},
                {1, 4}, {2, 2}, {2, 4}, {0, 3}, {0, 0}, {0,4}
        };
        ShipAttach shipAttach = new ShipAttach(); // Create an instance of the class
        String[] result = shipAttach.solution(grid, shots);

        // Print the results
        for (String r : result) {
            System.out.println(r);
        }
    }
}
