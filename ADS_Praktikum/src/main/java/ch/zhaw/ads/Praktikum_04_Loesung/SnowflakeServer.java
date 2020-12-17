package ch.zhaw.ads.Praktikum_04_Loesung;

import ch.zhaw.ads.CommandExecutor;

public class SnowflakeServer implements CommandExecutor {
    Turtle turtle;

    private void snowflake(int level, double dist) {
        if (level == 0) {
            turtle.move(dist);
        } else {
            dist = dist / 3;
            level--;
            snowflake(level, dist);
            turtle.turn(60);
            snowflake(level, dist);
            turtle.turn(-120);
            snowflake(level, dist);
            turtle.turn(60);
            snowflake(level, dist);
        }
    }

    public String execute(String command) {
        turtle = new Turtle(0.1, 0.7);

        snowflake(Integer.parseInt(command), 0.8);
        turtle.turn(-120);
        snowflake(Integer.parseInt(command), 0.8);
        turtle.turn(-120);
        snowflake(Integer.parseInt(command), 0.8);
        return turtle.getTrace();
    }

}