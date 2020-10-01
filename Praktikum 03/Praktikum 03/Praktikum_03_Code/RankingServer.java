package Praktikum_03_Code;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class RankingServer implements CommandExecutor {
    private List<Competitor> competitors = new ArrayList<>();

    private void readCSV(String csv){
        String[] lines = csv.split("\n");
        for (String line : lines){
            String[] attributes = line.split(";");
            competitors.add(new Competitor(
                    parseInt(attributes[0]),
                    attributes[1],
                    parseInt(attributes[2]),
                    attributes[3],
                    attributes[4]
            ));
        }
    }

    @Override
    public String execute(String command) throws Exception {
        readCSV(command);
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < competitors.size(); ++i){
            result.append(competitors.get(i).toString());
        }
        return result.toString();
    }
}
