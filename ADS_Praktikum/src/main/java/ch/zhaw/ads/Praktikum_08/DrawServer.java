package ch.zhaw.ads.Praktikum_08;

import ch.zhaw.ads.CommandExecutor;

import java.awt.*;

public class DrawServer implements CommandExecutor {

    ServerGraphics serverGraphics = new ServerGraphics();

    @Override
    public String execute(String command) {
        return drawTriangleAndRectangle();
    }

    //Aufgabe 2
    private String drawTriangleAndRectangle() {
        serverGraphics.setColor(Color.BLACK);
        serverGraphics.drawRect(0,0,1,1);
        serverGraphics.fillRect(0,0,1,1);
        serverGraphics.setColor(Color.RED);
        serverGraphics.drawTriangle(0.1, 0.1, 0.8);
        return serverGraphics.getTrace();
    }

}
