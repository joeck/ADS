package Praktikum_01_Code;

import java.util.HashMap;
import java.util.List;

public class BracketServer {

    ListStack<String> stack = new ListStack<>();
    List<String> openingBrackets = List.of("(", "{", "[");
    List<String> closingBrackets = List.of(")", "}", "]");
    HashMap<String, String> brackets = new HashMap<>();

    public BracketServer(){
        brackets.put("(",")");
        brackets.put("{", "}");
        brackets.put("[", "]");
    }

    public boolean checkBrackets(String s){
        stack.empty();
        for (int c = 0; c < s.length(); ++c){
            String curr = String.valueOf(s.charAt(c));
           if(brackets.containsKey(curr)){
               stack.push(curr);
           } else if (brackets.containsValue(curr)){
               if (!brackets.get(stack.pop()).equals(curr)) return false;
           }
        }
        return stack.isEmpty();
    }

    //slower algorithm, works as well
    public boolean checkBracketsSlow(String s){
        stack.empty();
        for (int c = 0; c < s.length(); ++c){
            String curr = String.valueOf(s.charAt(c));
            if(openingBrackets.contains(curr)){
                stack.push(curr);
            } else if (closingBrackets.contains(curr)){
                if (openingBrackets.indexOf(stack.pop()) != closingBrackets.indexOf(curr)) return false; //not closing latest bracket
            }
        }
        return stack.isEmpty();
    }
}
