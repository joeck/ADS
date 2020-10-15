import static java.lang.Integer.parseInt;

public class HanoiServer implements CommandExecutor {

    public String instructions = "";

    public void moveDisk(int n, char from, char to, char help) {
        if (n > 0) {
            // bewege Stapel n-1 von from auf help
            moveDisk(n-1, from, help, to);

            // bewegevon from nach to
            instructions = instructions.concat("move " + from + " to" + to + "\n");

            // bewegeStapeln-1 von help auf to
            moveDisk(n-1, help, to, from);
        }
    }

    @Override
    public String execute(String command) throws Exception {
        HanoiServer hanoi = new HanoiServer();
        moveDisk(parseInt(command), 'A', 'C', 'B');
        return instructions;
    }
}
