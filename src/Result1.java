import java.util.*;

class Result {
  public static long findMaximumSum(int treeNodes, List<Integer> treeFrom, List<Integer> treeTo, List<Integer> weight) {
    List<List<Integer>> tree = new ArrayList<>();
    for (int i = 0; i < treeNodes; i++) {
      tree.add(new ArrayList<>());
    }

    for (int i = 0; i < treeFrom.size(); i++) {
      int from = treeFrom.get(i);
      int to = treeTo.get(i);
      tree.get(from).add(to);
      tree.get(to).add(from);
    }

    long[][] dp = new long[treeNodes][2];

    dfs(0, -1, tree, weight, dp);

    // Return the maximum of the two possible sums
    return Math.max(dp[0][0], dp[0][1]);
  }

  private static void dfs(int node, int parent, List<List<Integer>> tree, List<Integer> weights, long[][] dp) {
    dp[node][0] = 0;
    dp[node][1] = weights.get(node);

    for (int child : tree.get(node)) {
      if (child != parent) {
        dfs(child, node, tree, weights, dp);
        dp[node][0] += Math.max(dp[child][0], dp[child][1]);
        dp[node][1] += dp[child][0];
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> treeFrom = new ArrayList<>();
    treeFrom.add(0);
    treeFrom.add(0);

    List<Integer> treeTo = new ArrayList<>();
    treeTo.add(1);
    treeTo.add(2);

    List<Integer> weights = new ArrayList<>();
    weights.add(2);
    weights.add(2);
    weights.add(3);

    System.out.println(findMaximumSum(3, treeFrom, treeTo, weights));
  }
}
