import java.util.HashMap;
import java.util.Map;

public class LampsCoordinates {
    public int solution(int[][] lamps) {
        int[][] resultantLight = new int[lamps.length][lamps[0].length];
        for (int i = 0; i < lamps.length; i++) {
            resultantLight[i][0] = lamps[i][0] - lamps[i][1];
            resultantLight[i][1] = lamps[i][0] + lamps[i][1];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < resultantLight.length; i++) {
            for (int j = resultantLight[i][0]; j <= resultantLight[i][1]; j++) {
                map.put(j, map.getOrDefault(j, 0) + 1);
            }
        }
        int count = 0;

        for (int value : map.values()) {
            if (value == 1) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        LampsCoordinates obj = new LampsCoordinates();
        int[][] lamps = {{-2, 3}, {2, 3}, {2, 1}};
        System.out.println(obj.solution(lamps));
    }
}
