import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LargestMinDistanceFromMonsters {
    public static int findLargestMinDistance(int[][] matrix, int[] start, int[] end) {
        List<List<int[]>> allPaths = findAllPaths(matrix, start, end);
        int largestMinDistance = Integer.MIN_VALUE;

        for (List<int[]> path : allPaths) {
            int minDistance = findMinDistanceFromMonsters(matrix, path);
            largestMinDistance = Math.max(largestMinDistance, minDistance);
        }

        return largestMinDistance;
    }

    public static List<List<int[]>> findAllPaths(int[][] matrix, int[] start, int[] end) {
        List<List<int[]>> allPaths = new ArrayList<>();
        List<int[]> currentPath = new ArrayList<>();
        dfs(matrix, start, end, currentPath, allPaths);
        return allPaths;
    }

    public static void dfs(int[][] matrix, int[] current, int[] end, List<int[]> currentPath, List<List<int[]>> allPaths) {
        int x = current[0];
        int y = current[1];

        if (x == end[0] && y == end[1]) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (isValid(newX, newY, matrix) && matrix[newX][newY] == 0) {
                currentPath.add(new int[]{newX, newY});
                dfs(matrix, new int[]{newX, newY}, end, currentPath, allPaths);
                currentPath.remove(currentPath.size() - 1); // Backtrack
            }
        }
    }

    public static boolean isValid(int x, int y, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static int findMinDistanceFromMonsters(int[][] matrix, List<int[]> path) {
        int minDistance = Integer.MAX_VALUE;

        for (int[] cell : path) {
            int x = cell[0];
            int y = cell[1];
            int distance = findDistanceFromMonsters(matrix, x, y);
            minDistance = Math.min(minDistance, distance);
        }

        return minDistance;
    }

    public static int findDistanceFromMonsters(int[][] matrix, int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int newX = current[0] + dx[j];
                    int newY = current[1] + dy[j];

                    if (isValid(newX, newY, matrix) && !visited[newX][newY] && matrix[newX][newY] == 1) {
                        return distance;
                    }

                    if (isValid(newX, newY, matrix) && !visited[newX][newY] && matrix[newX][newY] == 0) {
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }

            distance++;
        }

        return -1; // No monsters found
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        int[] start = {0, 0};
        int[] end = {3, 3};
        int result = findLargestMinDistance(matrix, start, end);
        System.out.println("Largest minimum distance from monsters: " + result);
    }
}
