package Praktikum_02_Code;

import java.util.HashMap;
import java.util.List;

public class BracketServer {

    ListStack<String> stack = new ListStack<>();
    HashMap<String, String> brackets = new HashMap<>();

    public BracketServer(){
        brackets.put("(",")");
        brackets.put("{", "}");
        brackets.put("[", "]");
        brackets.put("<", ">");
    }

    public boolean checkBrackets(String s){
        stack.empty();
        for (int c = 0; c < s.length(); ++c){
            String curr = String.valueOf(s.charAt(c));
            String next = c + 1 < s.length() ? String.valueOf(s.charAt(c+1)) : "";
            if ("<".equals(curr) && "*".equals(next)){
                stack.push("<*");
                ++c; //skip *
            } else if(brackets.containsKey(curr)){
                stack.push(curr);
            } else if ("*".equals(curr) && ">".equals(next)) {
                if(!"<*".equals(stack.pop())) return false;
                ++c; //skip * symbol
            } else if (brackets.containsValue(curr)){
                if (!(curr).equals(brackets.get(stack.pop()))) return false;
            }
        }
        return stack.isEmpty();
    }
}
