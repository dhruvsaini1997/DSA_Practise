import java.util.*;

public class CongestedMountainTrail {
    public static List<Integer> getResult(List<Integer> arrivals, List<Integer> directions) {

        int[] res = new int[directions.size()];
        PriorityQueue<Map.Entry<Integer, Integer>> pqAsc = new PriorityQueue<>((a, b) -> a.getKey() - b.getKey());
        PriorityQueue<Map.Entry<Integer, Integer>> pqDesc = new PriorityQueue<>((a, b) -> a.getKey() - b.getKey());

        for( Integer i = 0; i<arrivals.size() ; i++){
            Map.Entry<Integer, Integer> entry = new HashMap.SimpleEntry<>(arrivals.get(i), i);
            if(directions.get(i)==1){
               pqDesc.add(entry);
            }else{
                pqAsc.add(entry);
            }
        }


        int currTimer = Math.min(pqAsc.peek().getKey(),pqDesc.peek().getKey());
        PriorityQueue<Map.Entry<Integer,Integer>> currQ = new PriorityQueue<>();
        Integer prevCandidate=null;

        while(!pqAsc.isEmpty() || !pqDesc.isEmpty()){
            if(pqAsc.isEmpty()){
                currQ = pqDesc;
            } else if (pqDesc.isEmpty()) {
                currQ = pqAsc;
            } else if (prevCandidate!=null) {
                if(prevCandidate == 1){
                    if(currTimer<pqDesc.peek().getKey()){
                        prevCandidate = null;
                        currQ=null;
                    }else{
                    currQ = pqDesc;
                    prevCandidate = 1;
                    }
                }else{
                    if(currTimer<pqAsc.peek().getKey()){
                        prevCandidate = null;
                        currQ=null;
                    }else{
                    currQ = pqAsc;
                    prevCandidate =0;}
                }
            } else if (prevCandidate==null) {
                System.out.println("Desc"+pqDesc.peek().getKey());
                System.out.println("Acs"+pqAsc.peek().getKey());
                boolean isDesc = pqDesc.peek().getKey()<=pqAsc.peek().getKey()?
                        true:false;
                if(isDesc){
                    currQ = pqDesc;
                    prevCandidate=1;
                }else{
                    currQ = pqAsc;
                    prevCandidate =0;
                }
            }
            if(currQ!=null && !currQ.isEmpty()){
                res[currQ.poll().getValue()]= currTimer;
                currTimer++;
            }
            Integer minTimer = Math.min(pqDesc.isEmpty()?Integer.MAX_VALUE:pqDesc.peek().getKey(),
                    pqAsc.isEmpty()?Integer.MAX_VALUE:pqAsc.peek().getKey());
            if(currTimer < minTimer ){
                prevCandidate =null;
                currTimer = minTimer;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int j = 0; j < res.length; j++) {
            ans.add(res[j]);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> arrivals = new ArrayList<>();
        List<Integer> directions = new ArrayList<>();
        arrivals.add(0); arrivals.add(0);
        directions.add(0); directions.add(1);

//        arrivals.add(0); arrivals.add(1); arrivals.add(1); arrivals.add(3);arrivals.add(3);
//        directions.add(1); directions.add(0); directions.add(0); directions.add(1);directions.add(0);
        List<Integer> result = getResult(arrivals, directions);
        for (Integer i : result) {
            System.out.println(i);
        }
    }
}
