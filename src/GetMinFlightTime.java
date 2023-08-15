import java.util.ArrayList;
import java.util.List;

public class GetMinFlightTime {

public static int getMin(List<Integer> a2b,List<Integer> b2a,int trip){

    int main_start = a2b.get(0);
    int start = main_start;
    int back=0;
    int t =trip;
while(t!=0){

    System.out.println("Trip: "+(trip-t+1));

    if(t!=trip){

        for(int i =0;i<a2b.size();i++){
            if(a2b.get(i)>=start){
                start=a2b.get(i);
                break;
            }
        }
    }
    System.out.println("Start : " +start);
    a2b.remove(0);

    int reach = start+100;

    System.out.println("Reach:"+reach);

    int return_time = Integer.MAX_VALUE;

    for(int i =0;i<b2a.size();i++){
        if(b2a.get(i) >= reach && b2a.get(i)<return_time){
            return_time = b2a.get(i);
            }
    }

    System.out.println("Return Flight " +return_time);
     back = return_time+100;
    start = back;
    t--;
}

    return back;

}
    public static void main(String[] args) {
        List<Integer> a2b = new ArrayList<>();
        List<Integer> b2a = new ArrayList<>();

        // Adding elements to a2b
        a2b.add(0);
        a2b.add(200);
        a2b.add(500);

// Adding elements to b2a
        b2a.add(99);
        b2a.add(210);
        b2a.add(450);

        // Printing the initial contents of the nested lists
        System.out.println(getMin(a2b,b2a,1));

}}
