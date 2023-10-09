import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Letter_Combinations_Phone_Number {
  public List<String> getCombinations(String digits){
    List<String> ll = new ArrayList<>();
    if(digits.length()==0){
      return ll;
    }
    Map<Character,String> mp = new HashMap<>();
    mp.put('2', "abc");
    mp.put('3', "def");
    mp.put('4', "ghi");
    mp.put('5', "jkl");
    mp.put('6', "mno");
    mp.put('7', "pqrs");
    mp.put('8', "tuv");
    mp.put('9', "wxyz");

    return getCombo(ll,digits,mp,0,"");

  }
  List<String> getCombo(List<String> list, String digits,
                        Map<Character,String> map, int index, String combo){
    if(index==digits.length()){
      list.add(combo);
      return list;
    }
    char currDigit = digits.charAt(index);
    String curStr = map.get(currDigit);
    for( int i =0;i<curStr.length();i++){
      combo+= curStr.charAt(i);
      getCombo(list,digits,map,index+1,combo);
      combo = combo.substring(0,combo.length()-1);
    }

  return list;}


  public static void main(String[] args){
    String digits = "23";
    Letter_Combinations_Phone_Number obj = new Letter_Combinations_Phone_Number();
    System.out.println(obj.getCombinations(digits));
  }
}
