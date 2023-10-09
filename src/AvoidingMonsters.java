import java.util.*;

public class AvoidingMonsters {
    public static int findLargestMinDistance(int n, int m, int targetRow, int targetColumn,
                                             List<Integer> monsterRow, List<Integer> monsterColumn) {

        int[][] matrix = new int[n][m];
        int monsterCount = monsterColumn.size();
        for (int i = 0; i < monsterCount; i++) {
            matrix[monsterRow.get(i)][monsterColumn.get(i)] = 1;
        }

        int largestMinDistance = calculateMinDistance(targetRow, targetColumn, matrix,0);

        if (largestMinDistance == -1) {
            return -1; // Target is unreachable
        }

        return largestMinDistance;
    }
    public  static int calculateMinDistance(int x, int y, int[][] matrix, int minDist) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] == 1) {
            return minDist; // Minimum distance to a monster
        }
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};


        matrix[x][y] = 1; // Mark cell as visited

        int largestMinDist = minDist;

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            int nextMinDist = calculateMinDistance(newX, newY, matrix,minDist + 1);
            largestMinDist = Math.max(largestMinDist, nextMinDist);
        }

        matrix[x][y] = 0; // Unmark cell to allow backtracking
        return largestMinDist;
    }

    public static void main(String[] args) {
        List<Integer> monsterRow = new ArrayList<>(Arrays.asList(0, 2));
        List<Integer> monsterCol = new ArrayList<>(Arrays.asList(2, 2));

        int largestMinDistance = findLargestMinDistance(5, 3, 4, 2, monsterRow, monsterCol);
        System.out.println(largestMinDistance);
    }
}
