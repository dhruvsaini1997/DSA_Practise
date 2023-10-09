import java.util.Arrays;
import java.util.Stack;

public class LongestPathMatrix {
    public static int findLongestPath(int[][] matrix, int[] start, int[] end) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        Stack<int[]> stack = new Stack<>();
        stack.push(start);
        dp[start[0]][start[1]] = 0;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];

            if (x == end[0] && y == end[1]) {
                return dp[x][y];
            }

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (isValid(newX, newY, matrix)
                        && matrix[newX][newY] == 0
                        && dp[newX][newY] == -1) {
                    dp[newX][newY] = dp[x][y] + 1;
                    stack.push(new int[]{newX, newY});
                }
            }
        }

        return -1; // No path found
    }

    public static boolean isValid(int x, int y, int[][] matrix) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }

    public static void main(String[] args) {
        int[] start = {0, 0};
        int[] end = {3, 3};
        int[][] grid = {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        int result = findLongestPath(grid, start, end);
        if (result != -1) {
            System.out.println("Longest path length: " + result);
        } else {
            System.out.println("No path found.");
        }
    }
}
