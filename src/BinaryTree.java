import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int val) {
        root = insertNode(root, val);
    }

    public TreeNode insertNode(TreeNode currNode, int val) {
        if (currNode == null) {
            return new TreeNode(val);
        }

        if (val < currNode.value) {
            currNode.left = insertNode(currNode.left, val);
        } else if (val > currNode.value) {
            currNode.right = insertNode(currNode.right, val);
        }

        return currNode;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");
        dfs(root.left);
        dfs(root.right);
    }
    public void bfs() {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            System.out.print(currentNode.value + " ");

            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }

            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }}

    public boolean isLeafNodesSimilar(TreeNode root1,TreeNode root2){
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        dfs_arr(root1,arr1);
        dfs_arr(root2,arr2);
        if(arr1.size()!=arr2.size()){
            return false;
        }
        for(int i =0;i<arr1.size();i++){
            if(arr1.get(i)!=arr2.get(i)){
                return false;
            }
        }
        return true;
    }
    public  void  dfs_arr(TreeNode node, ArrayList<Integer> arr){
        if(node==null){
            return;
        }
        if(node.right==null && node.left==null){
            arr.add(node.value);
        }
        dfs_arr(node.left,arr);
        dfs_arr(node.right,arr);
    }
    public boolean isValidBst(TreeNode tree,Integer min,Integer max){
        if(tree==null){
            return true;
        }
        else if ((min!=null && tree.value<= min ) || (max!=null && max<= tree.value)) {
            return false;
        }
        return isValidBst(tree.left,min, tree.value) && isValidBst(tree.right, tree.value, max);
    }
}
