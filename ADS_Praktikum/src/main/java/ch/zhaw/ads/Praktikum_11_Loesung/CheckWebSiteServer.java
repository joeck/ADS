package ch.zhaw.ads.Praktikum_11_Loesung;

import ch.zhaw.ads.CommandExecutor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.util.stream.Stream;

/**
 * Findet Verweise auf weitere Webseiten.
 * - funktioniert für https-Seiten
 * - maximale Tiefe ist auf 3 eingeschränkt. Eine Erhöhung der maximalen Tiefe verlängert den Suchprozess massiv.
 * @author bles
 * @version 1.0
 */
public class CheckWebSiteServer implements CommandExecutor {

	private static final String URL_REGEX = "(https:\\/\\/)(www\\.)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)|(https:\\/\\/)(www\\.)?(?!ww)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
	private static final int MAX_DEPTH = 3;
	private List<String> foundUrls = new ArrayList<>();
	
	@Override
	public String execute(String command) throws Exception {
		if (command.length()>0 && command.startsWith("https")) {
			Pattern pat = Pattern.compile(URL_REGEX);
			checkUrls(command, 0, pat);
		} else {
			return "Bitte eine url angeben in der Form <https://www.es.ch>\n";
		}
		return String.join("\n", foundUrls);
	}
	
	/**
	 * Für einen Test kann ein Aufruf ohne die ExBox gemacht werden
	 * @param args
	 */
	public static void main (String[] args) {
		try {
			CheckWebSiteServer testServer = new CheckWebSiteServer();
			System.out.println(testServer.execute("https://www.search.ch"));
		} catch (Exception e) {e.printStackTrace();}
	}
	
	private void checkUrls(String url, int depth, Pattern pat) {
		if(!foundUrls.contains(url)) {
			foundUrls.add(url);
			if (depth<=MAX_DEPTH) {
				String inhalt = HttpsClient.getContentOfURL(url);
				Matcher matcher = pat.matcher(inhalt);
				while (matcher.find()) {
					checkUrls(matcher.group(), depth + 1, pat);
				}
			}
		}
	}

}
