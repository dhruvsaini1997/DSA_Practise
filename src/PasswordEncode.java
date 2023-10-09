import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordEncode {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String inputString = scanner.nextLine();

    Map<Character, Integer> charFrequency = new HashMap<>();

    for (char c : inputString.toCharArray()) {
      charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
    }

    List<Character> sortedChars = new ArrayList<>(charFrequency.keySet());
    Collections.sort(sortedChars, Collections.reverseOrder());

    StringBuilder modifiedString = new StringBuilder();

    for (char c : sortedChars) {
      int frequency = charFrequency.get(c);

      if (frequency % 2 == 1) {
        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
          char otherChar = entry.getKey();
          int otherFrequency = entry.getValue();

          if (otherFrequency % 2 == 1 && otherChar != c) {
            modifiedString.append(otherChar);
            charFrequency.put(c, frequency - 1);
            entry.setValue(otherFrequency + 1);
            break;
          } else if (otherFrequency % 2 == 1 && otherChar == c) {
            modifiedString.append(otherChar);
            break;
          }
        }
      } else {
        modifiedString.append(c);
      }
    }

    System.out.println("Modified string: " + modifiedString.toString());
  }
}
