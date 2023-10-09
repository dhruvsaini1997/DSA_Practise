import java.util.*;

public class MatrixPath {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 1}
        };

        int[] src = {0, 0};
        int[] dest = {3, 3};

        List<List<int[]>> allPaths = findPaths(matrix, src, dest);

        for (List<int[]> path : allPaths) {
            System.out.print("Path: ");
            for (int[] cell : path) {
                System.out.print("(" + cell[0] + "," + cell[1] + ") ");
            }
            System.out.println(); // Add a line break after each path
        }

    }

    public static List<List<int[]>> findPaths(int[][] matrix, int[] src, int[] dest) {
        List<List<int[]>> allPaths = new ArrayList<>();
        List<int[]> currPath = new ArrayList<>();
dfs(matrix,src[0],src[1],dest[0],dest[1],currPath,allPaths);
        return allPaths;
    }
    public static void dfs(int[][] matrix, int row, int col, int destRow,
                           int destCol, List<int[]> currPath, List<List<int[]>> allPaths
    ){
        if(row<0 || row>=matrix.length
                || col<0 || col>=matrix[0].length
                || matrix[row][col]==0){
            return;
        };

            currPath.add(new int[]{row,col});

        if(row==destRow && col == destCol){
            allPaths.add(new ArrayList<>(currPath));

        }else {
            matrix[row][col] = 0; // mark as visited
            //explore all directions from that row and col
            dfs(matrix, row + 1, col, destRow, destCol, currPath, allPaths);
            dfs(matrix, row - 1, col, destRow, destCol, currPath, allPaths);
            dfs(matrix, row, col + 1, destRow, destCol, currPath, allPaths);
            dfs(matrix, row, col - 1, destRow, destCol, currPath, allPaths);
            matrix[row][col] = 1;//for backtracking
        }
            currPath.remove(currPath.size() - 1);

    }

    public static boolean isValid(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length && matrix[row][col] == 1;
    }
}
