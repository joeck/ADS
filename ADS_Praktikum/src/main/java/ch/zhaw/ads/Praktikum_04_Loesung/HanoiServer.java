package ch.zhaw.ads.Praktikum_04_Loesung;

import ch.zhaw.ads.CommandExecutor;

public class HanoiServer implements CommandExecutor {

    public String execute(String command) {
        int nrOfSlices = Integer.parseInt(command);
        String solution = "Bewegt " + nrOfSlices + " Scheiben von Turm a nach Turm c\n";
        return solution + moveDisk('a', 'b', 'c', nrOfSlices);
    }

    /**
     * Bewegt n Scheiben von Turm a nach Turm c und benutzt als
     * Zwischenspeicher Turm b.
     */
    private static String moveDisk(char a, char b, char c, int n)
    {
        String result;
        if (n == 1)
            result = "Lege die oberste Scheibe von Turm " + a + " auf Turm " + c + ".\n";
        else {
            result = moveDisk(a, c, b, n-1);
            result += moveDisk(a, b, c, 1);
            result += moveDisk(b, a, c, n-1);
        }
        return result;
    }

}
