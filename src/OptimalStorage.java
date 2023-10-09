import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimalStorage {

    public static String getStoredWord(String word, int maxOperations) {
        Map<Character, List<Integer>> myMap = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (myMap.containsKey(ch)) {
                List<Integer> value = myMap.get(ch);
                value.add(i);
                myMap.put(ch, value);
            } else {
                List<Integer> value = new ArrayList<>();
                value.add(i);
                myMap.put(ch, value);
            }
        }

        int k = 0;
        StringBuilder temp = new StringBuilder(word);

        for (int i = 0; i < maxOperations;) {
            char ch = temp.charAt(k);

            if (ch == 'a') {
                k = k + 1;
                continue;
            }

            List<Integer> value = new ArrayList<>();

            if (myMap.containsKey(ch)) {
                value = myMap.get(ch);
                myMap.remove(ch);
            }

            ch = (char) (ch - 1);

            if (myMap.containsKey(ch)) {
                List<Integer> val = myMap.get(ch);

                for (int j = 0; j < value.size(); j++) {
                    val.add(value.get(j));
                }

                myMap.put(ch, val);
            } else {
                myMap.put(ch, value);
            }

            for (Map.Entry<Character, List<Integer>> entry : myMap.entrySet()) {
                char x = entry.getKey();
                List<Integer> val = entry.getValue();

                for (int j = 0; j < val.size(); j++) {
                    temp.setCharAt(val.get(j), x);
                }
            }

            i++;
        }

        return temp.toString();
    }

    public static void main(String[] args) {
        String word = "baddb";
        int maxOperations = 2;
        String result = getStoredWord(word, maxOperations);
        System.out.println(result);
    }
}