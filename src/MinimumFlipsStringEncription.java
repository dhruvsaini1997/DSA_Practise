import java.util.ArrayList;
import java.util.List;

public class MinimumFlipsStringEncription {
    public static int getMinFlips(String pwd) {
        int len = pwd.length();
        int minFlips = Integer.MAX_VALUE;

        for (int i = 0; i < 2; i++) {
            int flips = 0;

            for (int j = 0; j < len; j++) {
                int expected = (j % 2 == 0) ? i : (1 - i); // Expected character at position j
                if (Character.getNumericValue(pwd.charAt(j)) != expected) {
                    flips++;
                }
            }

            minFlips = Math.min(minFlips, flips);
        }

        return minFlips;
    }


    public static int getFlips(String pwd) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < pwd.length(); i++) {
            arr.add(Character.getNumericValue(pwd.charAt(i)));
        }
        int flips=0;
        for(int i =1;i<arr.size();i+=2){
            if(arr.get(i-1)!=arr.get(i)){
                flips++;
            }
        }

        return flips;
    }
    public static void main(String[] args) {
        int result = getFlips("11100101");
        System.out.println("Minimum flips needed: " + result);  // Output should be 2
    }
}
