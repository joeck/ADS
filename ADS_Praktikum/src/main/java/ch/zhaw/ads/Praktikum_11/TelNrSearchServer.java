package ch.zhaw.ads.Praktikum_11;

import ch.zhaw.ads.CommandExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelNrSearchServer implements CommandExecutor {

    private final ArrayList<String> phoneNumbers;
    private String nameToSearchNumbersFor;
    private String error;

    public TelNrSearchServer() throws IOException {
        phoneNumbers = new ArrayList<>();
    }

    //----- Dies implementiert das CommandExecutor Interface.
    @Override
    public String execute(String nameToSearchNumbersFor) throws Throwable {

        this.nameToSearchNumbersFor = nameToSearchNumbersFor;
        if (findNumbers()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String number : phoneNumbers) {
                stringBuilder.append(number).append("\n");
            }
            return (stringBuilder.toString().isEmpty()) ? "No numbers found\n" : "Neuer Request Suche: " + nameToSearchNumbersFor + "\n" + stringBuilder.toString();
        } else return error;
    }

    private boolean findNumbers() {
        String inputLine;
        URL telsearch;
        try {
            telsearch = new URL("https://tel.search.ch/?was=" + nameToSearchNumbersFor + "&maxnum=200");
        } catch (MalformedURLException e) {
            error = e.getClass().getName();
            return false;
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(telsearch.openStream()))) {
            Pattern pattern = Pattern.compile("tel:\\+([0-9]){11}");
            Matcher matcher;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("tel:")) {
                    //System.out.println(inputLine);
                    matcher = pattern.matcher(inputLine);
                    if (matcher.find()) {
                        System.out.println(matcher.group());
                        phoneNumbers.add(matcher.group());
                    }
                }

            }
        } catch (IOException e) {
            error = e.getClass().getName();
            return false;
        }
        return true;
    }
}
