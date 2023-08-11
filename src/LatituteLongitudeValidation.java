//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class LatituteLongitudeValidation {

    public static boolean validateSubString(String stringz, boolean isLat) {


        if (stringz.charAt(0) == '+' || stringz.charAt(0) == '-') {
            stringz = stringz.substring(1);
        }
        int len = stringz.length();
        if (!Character.isDigit(stringz.charAt(0)) || !Character.isDigit(stringz.charAt(len - 1))) {
            return false;
        }

        try {
            double value = Double.parseDouble(stringz);

            if (isLat && (value > 90 || value < -90)) {
                return false;
            }

            if (!isLat && (value > 180 || value < -180)) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false; // Parsing to double failed, invalid format
        }

        return true;
    }

    public static String[] funcValid(String[] inputStr){
        String[] answer = new String[inputStr.length];
        int size = inputStr.length;
        for(int i =0;i<size;i++){
            int strLen = inputStr[i].length();
            String str = inputStr[i];
            System.out.println(str);
            if(str.charAt(0) != '(' || str.charAt(strLen-1)!=')'){
                answer[i]="Invalid";
            }else{
            int div=0;
            for(int j =1;i<strLen-1;j++){
                if(str.charAt(j)==',') {
                    div = j;

                    break;
                }
            }
            String lat = str.substring(1, div);
            String longitude = str.substring(div + 1, strLen-1);
            Boolean validLat = validateSubString(lat,true);
            Boolean validLong = validateSubString(longitude,false);
           if(validLong && validLat){
                answer[i]="Valid ";
            }
            else{answer[i]="Invalid ";
        }

                System.out.println(answer[i]);
            }}

        return answer;
    }


    public static void main(String[] args){
        String[] arr =
                {
                "(75,180)",
                "(+90.0,-147.45)",
                "(77.11112223331,149.99999999)",
                "(+90,+180)",
                "(90,180)",
                "(-90.00000,-180.0000)",
                "(75,280)",
                "(+190.0, -147.45)",
                "(77.11112223331,249.99999999)",
                "(+90,+180.2)",
//                "(90.,180.)",
//                "(-090.00000,-180.0000)"
        };
//        {"(90,180)","(+90,+180)","(90., 180)","(90.0,180.1)","(85S,95W)"};
        String[] res = funcValid(arr);
        for(int i =0;i<arr.length;i++){
            System.out.println(res[i]);
        }
    }
}
