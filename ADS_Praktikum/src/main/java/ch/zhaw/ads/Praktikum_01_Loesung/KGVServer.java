/**
 * AnyServer -- Praktikum Experimentierkasten -- ADS
 *
 * @author E. Mumprecht
 * @version 1.0 -- Geruest fuer irgendeinen Server
 */
package ch.zhaw.ads.Praktikum_01_Loesung;

import ch.zhaw.ads.CommandExecutor;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class KGVServer implements CommandExecutor {

    //----- Dies implementiert das CommandExecutor Interface.
    @Override
    public String execute(String command) {

        return kgv(command);
    }

    private String kgv(String s)
    {
        Scanner scanner = new Scanner(new ByteArrayInputStream(s.getBytes()));
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        return Integer.toString(kgv(a,b)).concat("\n");
    }

    public int kgv(int a, int b)
    {
        return (a * b)/ ggt(a,b);
    }

    private static int ggt(int number1, int number2) {
        while (number2 != 0) {
            if (number1 > number2) {
                number1 = number1 - number2;
            } else {
                number2 = number2 - number1;
            }
        }
        return number1;
    }

}
