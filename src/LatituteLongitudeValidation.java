import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatituteLongitudeValidation {
    public static boolean validateSubString(String stringz, boolean isLat) {
        int len = stringz.length();
        String pattern;

//        if (isLat) {
//            pattern = "^(\\+|-)?(?:90|(?:(?:[0-9]|[1-8][0-9])(\\.0{1,6})?))$";
//        } else {
//            pattern = "^(\\+|-)?(?:180|(?:(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(\\.0{1,6})?))$";
//        }
        if (isLat) {
            pattern = "^(\\+|-)?(?:90(?:\\.(?:\\d{1,6})?)?|(?:[0-9]|[1-8][0-9])(?:\\.(?:\\d{1,6})?))$";
        } else {
            pattern = "^(\\+|-)?(?:180(?:\\.(?:\\d{1,6})?)?|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:\\.(?:\\d{1,6})?))$";
        }


        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(stringz);

        return matcher.matches();
    }
    public static String[] funcValid(String[] inputStr){
        String[] answer = new String[100];
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
        }}}
        return answer;
    }


    public static void main(String[] args){
        String[] arr = {"(90,180)","(+90,+180","(90.0,180)","(90,180.1)","(85S,95W)"};
        String[] res = funcValid(arr);
        for(int i =0;i<5;i++){
            System.out.println(res[i]);
        }
    }
}
