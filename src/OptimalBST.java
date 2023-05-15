public class OptimalBST {

    public static double optimalSearchTree(double p[], double q[], int n) {

       int[][] root = new int[n+1][n+1];
       double[][] C = new double[n+2][n+2];
       double[][] w = new double[n+2][n+2];

        for (int i = 1;i <= n + 1;++i)
        {
            w[i][i - 1] = q[i - 1];
            C[i][i - 1] = q[i - 1];
        }

        for (int L = 1; L <= n; L++) {

            for (int i = 1;i <= n - L + 1;++i){

                int j = i + L - 1;

                C[i][j] = Integer.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j] + q[j];
                for (int r = i; r <= j; r++) {

                    double val = C[i][r - 1] + C[r + 1][j] + w[i][j];
                    if (val < C[i][j])
                    {
                        C[i][j] = val;
                        root[i][j] = r;
                    }
            }
        }}
        System.out.println("Root of the tree is :" +root[1][n]);
        return C[1][n];
    }
    public static void main(String[] args) {

        double p[] = { -1,0.04,0.06,0.08,0.02,0.1,0.12,.14 };
        double q[] = { 0.06,0.06,0.06,0.05,0.05,0.05,0.05,0.05 };
        int n = p.length-1;
        System.out.println("Cost of Optimal BST is " + optimalSearchTree(p, q, n));
    }
}
