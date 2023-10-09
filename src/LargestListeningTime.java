//public class LargestListeningTime
import java.util.*;

public class LargestListeningTime {
    public int highestListenTime(int[] audiobooks, String[] logs) {

        int[] listenTimes = new int[audiobooks.length];
        int currentIndex = 0;

        for (String log : logs) {
            String[] logParts = log.split(" ");
            String action = logParts[0];
            int value = Integer.parseInt(logParts[1]);

            if (action.equals("LISTEN")) {
                int minutes = value;
                while (minutes > 0) {
                    if (listenTimes[currentIndex] == audiobooks[currentIndex] ||
                            audiobooks[currentIndex] == 0) {
                        currentIndex = (currentIndex + 1) % audiobooks.length;
                        continue;
                    }

                    int timeToListen = Math.min(minutes, audiobooks[currentIndex] - listenTimes[currentIndex]);
                    listenTimes[currentIndex] += timeToListen;
                    minutes -= timeToListen;
                    currentIndex = (currentIndex + 1) % audiobooks.length;
                }
            } else if (action.equals("DROP")) {
                int indexToDrop = value;
                audiobooks[indexToDrop] = 0;
            }
        }

        Map<Integer, List<Integer>> hashmap = new HashMap<>();
        for (int i = 0; i < listenTimes.length; i++) {
            if (!hashmap.containsKey(listenTimes[i])) {
                hashmap.put(listenTimes[i], new ArrayList<>());
            }
            hashmap.get(listenTimes[i]).add(i);
        }

        int maxKey = Collections.max(hashmap.keySet());
        return Collections.max(hashmap.get(maxKey));
    }

    public static void main(String[] args) {
        LargestListeningTime s = new LargestListeningTime();
        int[] audiobooks = {5, 6, 7, 8};
        String[] logs = {
                "LISTEN 5",
                "LISTEN 5",
                "LISTEN 4",
                "LISTEN 4",
                "DROP 1",
                "LISTEN 1"
        };
        System.out.println(s.highestListenTime(audiobooks, logs));
    }
}
