package ch.zhaw.ads.Praktikum_04_Loesung;

import ch.zhaw.ads.CommandExecutor;

public class HilbertServer implements CommandExecutor {
    Turtle turtle;

    private void hilbert(int depth, double dist, double angle) {
        if (depth >= 0) {
            turtle.turn(-angle);
            hilbert(depth-1, dist, -angle);
            turtle.move(dist);
            turtle.turn(angle);
            hilbert(depth-1, dist, angle);
            turtle.move(dist);
            hilbert(depth-1, dist, angle);
            turtle.turn(angle);
            turtle.move(dist);
            hilbert(depth-1, dist, -angle);
            turtle.turn(-angle);
        }
    }

    public String execute(String command) {
        int depth = Integer.parseInt(command);
        double dist = 0.8 / (Math.pow(2,depth+1)-1);
        turtle = new Turtle(0.1, 0.1);
        hilbert(depth, dist, -90);
        return turtle.getTrace();
    }

}
