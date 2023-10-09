import java.util.ArrayList;
import java.util.List;

public class GetMinFlightTime {
    public static int getMin(List<Integer> a2b, List<Integer> b2a, int trip) {
        int mainStart = a2b.get(0);
        int start = mainStart;
        int back = 0;

        for (int t = 1; t <= trip; t++) {
            System.out.println("Trip: " + t);

            int nextFlightIndex = 0;
            while (nextFlightIndex < a2b.size() && a2b.get(nextFlightIndex) < start) {
                nextFlightIndex++;
            }

            if (nextFlightIndex < a2b.size()) {
                start = a2b.get(nextFlightIndex);
            }
            System.out.println("Start: " + start);
            a2b.remove(nextFlightIndex);

            int reach = start + 100;
            System.out.println("Reach: " + reach);

            int returnTime = Integer.MAX_VALUE;
            for (int i = 0; i < b2a.size(); i++) {
                int returnFlightTime = b2a.get(i);
                if (returnFlightTime >= reach && returnFlightTime < returnTime) {
                    returnTime = returnFlightTime;
                }
            }

            System.out.println("Return Flight: " + returnTime);
            back = returnTime + 100;
            start = back;
        }

        return back;
    }
    public static void main(String[] args) {
        List<Integer> a2b = new ArrayList<>();
        List<Integer> b2a = new ArrayList<>();

        // Adding elements to a2b
        a2b.add(109);
        a2b.add(200);
        a2b.add(500);

// Adding elements to b2a
        b2a.add(99);
        b2a.add(210);
        b2a.add(600);

        // Printing the initial contents of the nested lists
        System.out.println(getMin(a2b,b2a,2));

}}
