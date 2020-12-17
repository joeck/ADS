package ch.zhaw.ads.Praktikum_01;

import ch.zhaw.ads.CommandExecutor;

/**
 * ch.zhaw.ads.Praktikum_01.AnyServer -- Praktikum Experimentierkasten -- ADS
 *
 * @author E. Mumprecht
 * @version 1.0 -- Geruest fuer irgendeinen Server
 */

public class AnyServer implements CommandExecutor {

    //----- Dies implementiert das ch.zhaw.ads.CommandExecutor Interface.
    @Override
    public String execute(String command) {
        String result = "Die Eingabe war \"" +
                command +
                "\"\n";
        return result;
    }
}//ch.zhaw.ads.Praktikum_01.AnyServer
