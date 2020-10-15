import static java.lang.Integer.parseInt;

public class SnowflakeServer implements CommandExecutor {

    private Turtle turd;

    void schneeflocke(int stufe, double dist) {
        if (stufe== 0) {
            turd.move(dist);
        } else {
            stufe--;
            dist= dist/3;
            schneeflocke(stufe, dist);
            turd.turn(60);
            schneeflocke(stufe, dist);
            turd.turn(-120);
            schneeflocke(stufe, dist);
            turd.turn(60);
            schneeflocke(stufe, dist);
        }
    }

    @Override
    public String execute(String command) throws Exception {
        turd = new Turtle(0.1, 0.7);
        for (int i = 0; i < 3; i++){
            schneeflocke(parseInt(command),0.8);
            turd.turn(-120);
        }
        return turd.getTrace();
    }
}
