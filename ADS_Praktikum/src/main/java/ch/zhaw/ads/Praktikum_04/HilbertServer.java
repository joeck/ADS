package ch.zhaw.ads.Praktikum_04;

import ch.zhaw.ads.CommandExecutor;

public class HilbertServer implements CommandExecutor {
    Turtle tim;

    @Override
    public String execute(String command) throws Exception {
        int depth = Integer.parseInt(command);
        double dist = 2.0 / (Math.pow(2,depth+1)-1);
        double startCoordinate = (1 - (((Math.pow(2, depth)) - 1) * dist)) / 2;
        tim = new Turtle(startCoordinate, startCoordinate);
        try {
            hilbert(depth, dist, -90);
        } catch (OutOfMemoryError memoryError) {
            return "Number of recursions is too big!";
        }
        return tim.getTrace();
    }

    private void hilbert(int depth, double dist, double angle) {
        if(depth <= 0) return;
        tim.turn(-angle);
        hilbert(depth - 1, dist, -angle);
        tim.move(dist);
        tim.turn(angle);
        hilbert(depth - 1, dist, angle);
        tim.move(dist);
        hilbert(depth - 1, dist, angle);
        tim.turn(angle);
        tim.move(dist);
        hilbert(depth - 1, dist, -angle);
        tim.turn(-angle);
    }
}
