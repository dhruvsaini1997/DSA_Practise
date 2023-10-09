import java.util.*;
import java.math.*;


interface IPrinter {
    public abstract void OnMessageComplete(String message);
}

class Printer implements IPrinter {
    public void OnMessageComplete(String message)
    {
        System.out.println(message);
    }
}

class Decoder
{
    private IPrinter mPrinter;
    Map<BigInteger,Character> map = new HashMap<>();

    public Decoder(IPrinter printer)
    {
        this.mPrinter = printer;
    }

    public void processSample(BigInteger sequence, char character) {
        if (map.isEmpty()) {
            map.put(sequence, character);
        } else if (character == '-' && map.containsKey(sequence.subtract(BigInteger.ONE))) {
            map.put(sequence, '-');
            StringBuilder messageBuilder = new StringBuilder();

            BigInteger i = sequence.subtract(BigInteger.ONE);
            while (i.compareTo(BigInteger.ZERO) > 0) {
                Character prevChar = map.get(i);
                if (prevChar != null && prevChar != '-') {
                    messageBuilder.insert(0, prevChar);
                } else if (prevChar == '-') {
                    this.mPrinter.OnMessageComplete(messageBuilder.toString());
                    break;
                } else {
                    break;
                }
                i = i.subtract(BigInteger.ONE);
            }
        } else {
            map.put(sequence, character);
        }


    }
}

public class NumberStation {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        IPrinter printer = new Printer();
        Decoder decoder = new Decoder(printer);
//        Map<BigInteger,Character> map = new HashMap<>();
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] parameters = line.split(" ");
            BigInteger sequence = new BigInteger(parameters[0]);
            char character = parameters[1].charAt(0);
            decoder.processSample(sequence,character);
        }
    }
}