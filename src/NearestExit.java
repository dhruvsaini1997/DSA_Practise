import java.util.LinkedList;
import java.util.Queue;

class NearestExit {
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> q = new LinkedList<>();
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        q.add(entrance);
        visited[entrance[0]][entrance[1]]=true;
        int steps = 0;
        int minSteps = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            int s = q.size();
            for(int j =0;j<s;j++){

                int[] currCor = q.poll();
                int x = currCor[0];
                int y = currCor[1];
                if (x != entrance[0] || y != entrance[1]) {
                    if (x == 0 || x == rows - 1 || y == 0 || y == cols - 1) {
                        return steps;
                    }
                }

                for(int i =0;i<4;i++){
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    if(newX>=0 && newX<rows && newY>=0 && newY<cols){
                        if(maze[newX][newY]=='.' && !visited[newX][newY]){
                            q.add(new int[]{newX,newY});
                            visited[newX][newY]=true;
                        }
                    }
                }}
                steps++;

        }
        return  -1;
    }
    public static void main(String[] args){
        char[][] charArr1 = {{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}};
        char[][] charArr2 = {{'.','+'}};
        char[][] charArr3 = {{'+','+','+'},{'.','.','.'},{'+','+','+'}};
char[][] charArr4 = {
                {'+','.','+','+','+','+','+'},
                {'+','.','+','.','.','.','+'},
                {'+','.','+','.','+','.','+'},
                {'+','.','.','.','.','.','+'},
                {'+','+','+','+','.','+','.'}
};


                 NearestExit obj = new NearestExit();
        System.out.println(obj.nearestExit(charArr4,new int[]{0,1}));
    }

}