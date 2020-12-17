package ch.zhaw.ads.Praktikum_11;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelNrSearchServerTest {
    @Test
    public void testRegex() {
        String test = "<ul class=\"tel-result-actions sl-screenonly sl-floatlist\"><li><a class=\"tel-result-action sl-icon-call\" href=\"tel:+4233750375\" data-entrytype=\"Business\" data-ponp=\"0\" data-stats=\"\">+423 375 03 75 *</a></li>\n";
        Pattern pattern = Pattern.compile("tel:\\+([0-9]){10}");
        Matcher matcher;
        System.out.println(test);
        matcher = pattern.matcher(test);
        if (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.start());
            System.out.println(matcher.end());
        }
    }
}
