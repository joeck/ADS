package ch.zhaw.ads.Praktikum_01;

import ch.zhaw.ads.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * ch.zhaw.ads.Praktikum_01.BracketServer -- Praktikum Einführung in Algorithmen
 * Aufgabe 3 - Klammertester
 *
 * @author Tim Rhomberg
 * @version 1.0, 17.09.2000
 */
public class BracketServer implements CommandExecutor {
    private char[] argumentChars;
    private int currentIndex;
    private static final List<Character> openBrackets = new ArrayList<>();
    private static final List<Character> closeBrackets = new ArrayList<>();
    static {
        openBrackets.add('[');
        closeBrackets.add(']');
        openBrackets.add('(');
        closeBrackets.add(')');
        openBrackets.add('{');
        closeBrackets.add('}');
        openBrackets.add('<');
        closeBrackets.add('>');
    }

    @Override
    public String execute(String command) throws Exception {
        if(checkBrackets(command)) {
            return "Correct clamping :)!\n";
        } else {
            return "Error: Wrong clamping!\n";
        }
    }

    /**
     * Make a while loop with the expression to look that nextChar is not a '\0'.
     * If the character is a close bracket, it will check if the previous item on the stack is the related openBracket.
     * If so it will pop both from the stack. At the endit will check if the stack is empty. If so the programm ran successful.
     *
     * @param arg the argument.
     * @return True if the bracket matching is valid.
     */
    public boolean checkBrackets(String arg) {
        ListStack listStack = new ListStack(arg.length());
        argumentChars = arg.toCharArray();
        currentIndex = 0;

        char currentBracket;
        Character previousBracket;
        // Wie viele Character wurden schon überprüft
        int charactersChecked = 0;

        currentBracket = nextChar();

        while (currentBracket != '\0') {
            previousBracket = (Character) listStack.peek(); //null
            if (charactersChecked != 0) currentBracket = nextChar();
            if (currentBracket != '\0') listStack.push(currentBracket);
            if (closeBrackets.contains(currentBracket)) {
                int indexOfCloseBracket = closeBrackets.indexOf(currentBracket);
                int indexOfOpenBracket = openBrackets.indexOf(previousBracket);
                if (indexOfCloseBracket == indexOfOpenBracket) {
                    listStack.pop();
                    listStack.pop();
                }
            }
            charactersChecked++;
        }
        return listStack.isEmpty();
    }

    /**
     * Gives an character which is a bracket.
     * For this it iterate over an char array with the argument. And it checks it the character is in one of the ArrayLists.
     * If it is so, it will return the character. And if not it will return '\0'.
     *
     * @return the next bracket character.
     */
    private char nextChar() {
        char nextChar = '\0';
        boolean result = false;
        while (!result && argumentChars.length > currentIndex) {
            char characterInExpression = argumentChars[currentIndex];
            boolean resultOpen = openBrackets.stream()
                    .anyMatch(character -> character.equals(characterInExpression));
            boolean resultClose = closeBrackets.stream()
                    .anyMatch(character -> character.equals(characterInExpression));
            result = resultClose || resultOpen;
            if (result) nextChar = argumentChars[currentIndex];
            currentIndex++;
        }
        return nextChar;
    }

}
