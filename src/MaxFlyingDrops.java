import java.util.HashMap;
import java.util.Map;

public class MaxFlyingDrops {
    public static int getMaxDrops(int[] X, int[] Y) {
        Map<Integer, Integer> xCounts = new HashMap<>();
        Map<Integer, Integer> yCounts = new HashMap<>();

        for (int i = 0; i < X.length; i++) {
            xCounts.put(X[i], xCounts.getOrDefault(X[i], 0) + 1);
            yCounts.put(Y[i], yCounts.getOrDefault(Y[i], 0) + 1);
        }

        int maxCount = 0;

        for (int count : xCounts.values()) {
            maxCount = Math.max(maxCount, count);
        }

        for (int count : yCounts.values()) {
            maxCount = Math.max(maxCount, count);
        }

        return maxCount==1?-1:maxCount;
    }

    public static void main(String[] args) {
        int[] x = {2, 3, 1, 4, 0};
        int[] y = {2, 9, 6, 5, 8};
        System.out.println(getMaxDrops(x, y)); // Output should be 3
    }
}
