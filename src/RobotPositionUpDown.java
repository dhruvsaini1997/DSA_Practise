public class RobotPositionUpDown {
    public static String getPosition(String input){
        int countU=0;
        int countD=0;
        for(int i =0;i<input.length();i++){
            if (input.charAt(i)=='U'){
                countU++;
            }
            else{
                countD++;
            }
        }
        if(countD==countU){
            return "";
        }
        return countU>countD?"U":"D";
    }
    public static void main(String [] args){
        System.out.println(getPosition("DDUDDDUUUU"));
    }
}
