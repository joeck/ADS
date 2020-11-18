import java.util.HashMap;
import java.util.concurrent.ExecutorService;

public class HashServer implements CommandExecutor {
    HashMap<Integer, Competitor> map;


    @Override
    public String execute(String command) throws Exception {

        if (command.toUpperCase().startsWith("GET")) {

            String line = command.substring(4);
            String[] words = line.split(";");
            String name = words[0];
            int  jg = Integer.parseInt(words[1]);
            return findByKey(name, jg);

        }
        return createCompHashMap(command);
    }

    private String findByKey(String name, int jg) {
        int hc = hashCode(name, jg);
        Competitor c = map.get(hc);
        System.out.println(c.toString());
        return "\n"+c.toString();
    }

    private String createCompHashMap(String command) {
        map = new HashMap<>();
        String[] lines = command.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String[] words = lines[i].split(";");
            int num = Integer.parseInt(words[0]);
            String name = words[1];
            int jg = Integer.parseInt(words[2]);
            String plz = words[3];
            String time = words[4];

            try {
                Competitor c = new Competitor(num, name, jg, plz, time);
                map.put(c.hashCode(), c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map.toString();
    }


    private int hashCode(String name, int jg) {
        int hash = 1;
        hash = hash * 13 + name.hashCode();
        hash = hash * 17 + jg;
        return hash;
    }
}
