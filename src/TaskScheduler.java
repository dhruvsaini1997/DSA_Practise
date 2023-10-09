public class TaskScheduler {
    private static long getMinCost(int[] taskCost, int[] taskTime) {
        return dfs(0, taskCost, 0, taskTime);
    }

    private static long dfs(int i, int[] taskCost, int timeSoFar, int[] taskTime) {
        int n = taskCost.length;
        if (i == n) return timeSoFar >= 0 ? 0 : Integer.MAX_VALUE;
        if (timeSoFar >= n - i) return 0;

        long resPaid = taskCost[i] +
                dfs(i + 1, taskCost, timeSoFar + taskTime[i], taskTime); // paid server

        long resFree =
                dfs(i + 1, taskCost, timeSoFar - 1, taskTime); // free server

        return Math.min(resPaid, resFree);
    }

    public static void main(String[] args) {
        int[] taskCost = {5, 6, 10};
        int[] taskTime = {1, 10, 10};
        long result = getMinCost(taskCost, taskTime);
        System.out.println(result);
    }
}
