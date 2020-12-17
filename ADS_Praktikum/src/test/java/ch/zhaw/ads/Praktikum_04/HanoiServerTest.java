package ch.zhaw.ads.Praktikum_04;

import ch.zhaw.ads.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

public class HanoiServerTest implements CommandExecutor {
    private List<String> hanoiSteps = new ArrayList<>();

    @Override
    public String execute(String command) throws Exception {
        String[] results = command.split(" ");
        moveDisk(Integer.parseInt(results[0]), results[1].toCharArray()[0], results[2].toCharArray()[0], results[3].toCharArray()[0]);
        StringBuilder stringBuilder = new StringBuilder();
        for (String step: hanoiSteps) {
            stringBuilder.append(step);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private void moveDisk(int n, char from, char to, char help) {
        if (n > 0) {
            // bewege Stapel n-1 von from auf help
            moveDisk(n-1, from, help, to);
            // bewege von from nach to
            hanoiSteps.add("move " + from + " to " + to); // bewege Stapel n-1 von help auf to
            moveDisk(n-1, help, to, from);
        }
    }
}
