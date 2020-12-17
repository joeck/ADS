package ch.zhaw.ads.Praktikum_01_Loesung;

import ch.zhaw.ads.CommandExecutor;

import java.util.regex.*;

public class WellFormedXmlServer implements CommandExecutor {
    private final static String EOT = ""; // end of text
    private final static Pattern pat = Pattern.compile("<[^>]+>");
    private Matcher matcher;

    private String getNextToken() {
        if (matcher.find()) {
            return matcher.group();
        }	else {
            return EOT;
        }
    }

    public String execute(String command) {
        ListStack stack = new ListStack();
        matcher = pat.matcher(command);
        String token = getNextToken();

        while (!EOT.equals(token)) {
            if (token.indexOf('/') < 0 ) {
                stack.push(token);
            }
            else if (token.indexOf('/') > 0) {
                if (stack.isEmpty()) return "too many closing tokens\n";
                String token1 = (String)stack.pop();
                if (!token1.equals(token.replace("/","")))
                    return "token "+token1 + " does not match \n";
            }
            token = getNextToken();
        }
        if (stack.isEmpty())
            return "ok\n";
        else
            return "token \""+stack.pop()+"\" not closed\n";
    }
}