package ch.zhaw.ads.Praktikum_11_Loesung;

import ch.zhaw.ads.CommandExecutor;

import java.util.regex.*;

public class TelNrSearchServer implements CommandExecutor {



    //----- Dies implementiert das CommandExecutor Interface.
    @Override
    public String execute(String nameToSearch) {
        String textOfHomePage;

        textOfHomePage = HttpsClient.getContentOfURL("https://tel.search.ch/?was="+nameToSearch);
        return "Gesuchter Name: " + nameToSearch + "\n" + extractPhoneNumbers(textOfHomePage) + "\n";
    }

    private String extractPhoneNumbers(String textToSearch) {
        String extractedPhoneNumbers = "";

        Pattern pat = Pattern.compile("0(2[124]|3[1234]|4[134]|5[12568]|6[12]|7[1456789]|81|92)\\s{0,1}\\d{3}\\s{0,1}\\d{2}\\s{0,1}\\d{2}");
        Matcher matcher = pat.matcher(textToSearch);
        while (matcher.find()) {
            String group = matcher.group();
            extractedPhoneNumbers += (group + "\n");
        }
        return extractedPhoneNumbers;
    }
}
