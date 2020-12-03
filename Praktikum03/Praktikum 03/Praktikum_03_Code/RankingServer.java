package Praktikum_03_Code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    private void calculateRanks(){
        Collections.sort(competitors);
        for(int i = 0; i < competitors.size(); ++i){
            competitors.get(i).setRank(i+1);
        }
    }

    private void generateNameList(){
        Collections.sort(competitors, new Comparator<Competitor>() {
            @Override
            public int compare(Competitor competitor, Competitor t1) {
                int result = competitor.getName().compareTo(t1.getName());
                if (result == 0){
                    if(competitor.getJg() < t1.getJg()){
                        result = -1;
                    } else if (competitor.getJg() > t1.getJg()){
                        result = +1;
                    } else {
                        result = 0;
                    }
                }
                return result;
            }
        });
    }

    private String printList(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < competitors.size(); ++i){
            result.append(competitors.get(i).toString());
        }
        return result.toString();
    }

    @Override
    public String execute(String command) throws Exception {
        readCSV(command);
        calculateRanks();
        generateNameList();
        return printList();
    }
}
