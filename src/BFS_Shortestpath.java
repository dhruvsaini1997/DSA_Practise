import java.util.*;

public class BFS_Shortestpath {
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>(); // Use int[] to store coordinates
        queue.add(new int[]{0, 0, k}); // Store the x, y coordinates and remaining obstacles k
        boolean[][][] visited = new boolean[rows][cols][k + 1]; // 3D visited array

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                int obstaclesLeft = current[2];

                if (x == rows - 1 && y == cols - 1) {
                    return steps; // Reached the destination
                }

                for (int j = 0; j < 4; j++) {
                    int newX = x + dx[j];
                    int newY = y + dy[j];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                        int newObstacles = obstaclesLeft - grid[newX][newY];

                        if (newObstacles >= 0 && !visited[newX][newY][newObstacles]) {
                            queue.add(new int[]{newX, newY, newObstacles});
                            visited[newX][newY][newObstacles] = true;
                        }
                    }
                }
            }

            steps++; // Increment steps for each level of BFS
        }

        return -1; // No path found
    }

    public static void main(String[] args) {
        BFS_Shortestpath solution = new BFS_Shortestpath();
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0}
        };
        int k = 1;
        int shortestPath = solution.shortestPath(grid, k);
        System.out.println("Shortest Path Length: " + shortestPath);
    }
}
