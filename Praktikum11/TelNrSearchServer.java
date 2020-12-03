import java.util.regex.*;
import java.net.*;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

public class TelNrSearchServer implements CommandExecutor {

    //----- Dies implementiert das CommandExecutor Interface.
    @Override
    public String execute(String nameToSearch) throws IOException {
        String textOfHomePage = "";

        URL myUrl = new URL("https://tel.search.ch/?was=joel");
        HttpsURLConnection conn = (HttpsURLConnection)myUrl.openConnection();
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            textOfHomePage += inputLine;
        }
        br.close();
        return extractPhoneNumbers(textOfHomePage);
    }

    private String extractPhoneNumbers(String textToSearch) {
        String result = "";
        String regex = "0\\d\\d\\s?\\d\\d\\d\\s?\\d\\d\\s?\\d\\d";
        Pattern pat = Pattern.compile(regex);
        Matcher matcher = pat.matcher(textToSearch);
        while(matcher.find()) {
            String group = matcher.group();
            result += group + "\n";
        }
        return result;
    }
}