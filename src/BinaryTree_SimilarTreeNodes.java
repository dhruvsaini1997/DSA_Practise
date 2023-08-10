import java.util.ArrayList;
import java.util.List;

public class BinaryTree_SimilarTreeNodes {



        public static void main(String args[]){
            BinaryTree binaryTree1 = new BinaryTree();

            binaryTree1.insert(4);
            binaryTree1.insert(2);
            binaryTree1.insert(6);
            binaryTree1.insert(1);
            binaryTree1.insert(3);
            binaryTree1.insert(7);

            BinaryTree binaryTree2 = new BinaryTree();

            binaryTree2.insert(4);
            binaryTree2.insert(2);
            binaryTree2.insert(6);
            binaryTree2.insert(1);
            binaryTree2.insert(3);
            binaryTree2.insert(7);

            System.out.print("DFS Tree 1: ");
            binaryTree1.dfs(binaryTree1.root);
            System.out.println();
            System.out.print("DFS Tree 2: ");
            binaryTree1.dfs(binaryTree2.root);
            System.out.println();
            System.out.print("BFS Tree 1: ");
            binaryTree1.bfs();
            System.out.println();
            System.out.print("BFS Tree 2: ");

            binaryTree2.bfs();
            System.out.println();
            System.out.println("Are LeafNodes Similar ? ");
            System.out.println(binaryTree1.isLeafNodesSimilar(binaryTree1.root,binaryTree2.root));









        }

}
