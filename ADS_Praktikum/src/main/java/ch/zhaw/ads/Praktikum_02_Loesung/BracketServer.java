package ch.zhaw.ads.Praktikum_02_Loesung;

import ch.zhaw.ads.CommandExecutor;
import ch.zhaw.ads.Praktikum_01_Loesung.ListStack;

public class BracketServer implements CommandExecutor {

    private ListStack bracketStack = new ListStack();
    private char[] stringToCheck;
    private int characterToCheck;

    public String execute(String inputString) {

        if (checkBrackets(inputString))
        {
            return "okay\n";
        }
        else
        {
            return "not okay\n";
        }
    }

    public boolean checkBrackets (String arg)
    {
        char currentBracket;
        char previousBracket;

        stringToCheck = arg.toCharArray();
        bracketStack.removeAll();
        characterToCheck = -1;
        currentBracket = nextChar();
        while (currentBracket != '\0')
        {
            if ((currentBracket == '(') || (currentBracket == '[') || (currentBracket == '{'))
            {
                bracketStack.push(currentBracket);
            }
            else
            {
                if (bracketStack.isEmpty()) return false;
                previousBracket = (char)bracketStack.pop();
                if ((previousBracket == '(' && currentBracket != ')') ||
                        (previousBracket == '[' && currentBracket != ']') ||
                        (previousBracket == '{' && currentBracket != '}'))
                {
                    return false;
                }
            }
            currentBracket = nextChar();
        }
        if (!bracketStack.isEmpty()) return false;
        return true;
    }

    private char nextChar()
    {
        char currentChar;

        while (characterToCheck + 1 < stringToCheck.length)
        {
            characterToCheck++;
            currentChar = stringToCheck[characterToCheck];
            if ((currentChar == '(') || (currentChar == ')') ||
                    (currentChar == '[') || (currentChar == ']') ||
                    (currentChar == '{') || (currentChar == '}') )
            {
                return currentChar;
            }
        }
        return '\0';
    }


}
