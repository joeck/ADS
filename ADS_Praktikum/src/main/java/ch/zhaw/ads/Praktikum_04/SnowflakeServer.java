package ch.zhaw.ads.Praktikum_04;

import ch.zhaw.ads.CommandExecutor;

import java.util.Scanner;

public class SnowflakeServer implements CommandExecutor {
    private Turtle turtle;

    @Override
    public String execute(String command) throws Exception {
        Scanner scanner = new Scanner(command);
        int stufe = scanner.nextInt();
        double dist = scanner.nextDouble();
        turtle = new Turtle();
        schneeflocke(stufe, dist);
        return turtle.getTrace();
    }

    void schneeflocke(int stufe, double dist) {
        // calculate height from equilateral triangle h = (sqrt(3) / 2) * side
        double heightLittle = (Math.sqrt(3.0) / 2.0) * (dist * (1.0 / 3.0));
        double heightBig = (Math.sqrt(3.0) / 2.0) * dist;
        double heightGesamt = heightLittle + heightBig;
        if(dist == 1.0) {
            turtle.reset(((1.0 - dist) / 2.0), 1.0 - heightLittle);
        } else {
            turtle.reset(((1.0 - dist) / 2.0), 1.0 - (((1.0 - heightGesamt) / 2.0) + heightLittle));
        }

        for(int i = 0; i < 3; i++){
            schneeflockeSide(stufe, dist);
            turtle.turn(-120);
        }
    }

    void schneeflockeSide(int stufe, double dist) {
        if (stufe == 0) {
            turtle.move(dist);
        } else {
            stufe--;
            dist = dist / 3;
            schneeflockeSide(stufe, dist);
            turtle.turn(60);
            schneeflockeSide(stufe, dist);
            turtle.turn(-120);
            schneeflockeSide(stufe, dist);
            turtle.turn(60);
            schneeflockeSide(stufe, dist);
        }
    }
}
