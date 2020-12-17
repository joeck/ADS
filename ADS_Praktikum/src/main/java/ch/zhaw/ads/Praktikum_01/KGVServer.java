package ch.zhaw.ads.Praktikum_01;

import ch.zhaw.ads.CommandExecutor;

/**
 * ch.zhaw.ads.Praktikum_01.BracketServer -- Praktikum Einführung in Algorithmen
 * Aufgabe 1 – Kleinstes gemeinsames Vielfaches (kgV)
 *
 * @author Tim Rhomberg
 * @version 1.0, 17.09.2000
 */
public class KGVServer implements CommandExecutor {

    @Override
    public String execute(String command) throws Exception {
        String[] numbers = command.split(" ");
        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);
        return Integer.toString(kgv(a,b)) + '\n';
    }

    /**
     * Berechnet das kgv. Dazu wird folgende Formel verwendet:
     * kgv(a,b) = |a * b| / ggt(a,b)
     *
     * @param a erste Zahl.
     * @param b zweite Zahl.
     * @return gibt das kgv als int zurück.
     */
    public int kgv(int a, int b) {
        return (a * b) / ggt(a, b);
    }

    /**
     * Berechnet das ggT.
     *
     * @param a erste Zahl.
     * @param b zweite Zahl.
     * @return gibt das ggT als int zurück.
     */
    public int ggt(int a, int b) {
        while (b != 0) {
            if (a > b) a = a - b;
            else b = b - a;
        }
        return a;
    }
}
