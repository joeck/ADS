package ch.zhaw.ads.Praktikum_11_Loesung;

import java.net.MalformedURLException;
import java.net.*;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;


public class HttpsClient {

    public static String getContentOfURL(String urlOfHomePage) {
        String textOfHomePage = "";
		String inputLine;

		try {
            URL oracle = new URL(urlOfHomePage);
            BufferedReader in = new BufferedReader( new InputStreamReader(oracle.openStream()));
            while ((inputLine = in.readLine()) != null) {
               textOfHomePage += inputLine;
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return textOfHomePage;
    }

}
