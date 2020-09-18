package Praktikum_01_Code;

public class XMLTester implements CommandExecutor {
    private int cursor = 0;
    private final String OPENING_TAG = "<";
    private final String CLOSING_TAG = ">";
    private String xml;
    private final ListStack<String> stack = new ListStack<>();

    boolean checkWellformed(String text){
        xml = text;
        String token;
        while ((token = getNextToken()) != null){
            if (token.endsWith("/")){
                //self closing bracket, can be ignored
            } else if (token.startsWith("/")){
                //closing bracket
                token = token.substring(1);
                if (!token.equals(stack.pop())){
                    return false;
                }
            } else {
                //opening bracket
                stack.push(token);
            }
        }
        return true;
    }

    private String getNextToken(){
        boolean isToken = false;
        String token = "";
        while (cursor < xml.length()){
            String curr = String.valueOf(xml.charAt(cursor++));
            if(OPENING_TAG.equals(curr)){
                isToken = true;
            } else if (CLOSING_TAG.equals(curr)){
                return token;
            } else if (isToken){
               token = token.concat(curr);
            }
        }
        return null; //no token found
    }

    @Override
    public String execute(String command) throws Exception {
        StringBuilder result = new StringBuilder(100);
        if (checkWellformed(command)){
            result.append("Document is wellformed");
        } else {
            result.append("Document looks wrong...");
        }
        return result.toString();
    }
}
