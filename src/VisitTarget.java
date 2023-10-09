public class VisitTarget {
    public static int getTargetIndex(int[] visits,int target){
        int sum=0;
        for(int i =0;i<visits.length;i++){
            sum+=visits[i];
            if(sum>=target){
                return i;
            }
        }
        return -1;
    }
    public static void main (String[] args){
        System.out.println(getTargetIndex(new int[]{900,200,100,200,500},9100));
    }
}
