package ch.zhaw.ads.Praktikum_08;

import java.awt.*;

import static java.lang.Math.sqrt;

public class ServerGraphics {

  private StringBuffer b;

  public ServerGraphics() {
    clear();
  }

  public void clear() {
    b = new StringBuffer();
  }

  public String getTrace() {
    return new String(b);
  }

  private double round(double d) {
    return Math.round(d * 10000) / 10000.0;
  }

  public void drawLine(double x1, double y1, double x2, double y2) {
  	b.append("<line x1=\"");
    b.append(Double.toString(round(x1)));b.append("\" y1=\"");
    b.append(Double.toString(round(y1)));b.append("\" x2=\"");
    b.append(Double.toString(round(x2)));b.append("\" y2=\"");
    b.append(Double.toString(round(y2)));b.append("\"/>\n");
  }

  public void drawRect(double x, double y, double w, double h) {
    b.append("<rect x=\"");
    b.append(Double.toString(round(x)));b.append("\" y=\"");
    b.append(Double.toString(round(y)));b.append("\" ");
    b.append("width=\"");
    b.append(Double.toString(round(w)));b.append("\" height=\"");
    b.append(Double.toString(round(h)));b.append("\" style=\"draw\" />\n");
  }

  public void fillRect(double x, double y, double w, double h) {
    b.append("<rect x=\"");
    b.append(Double.toString(round(x)));b.append("\" y=\"");
    b.append(Double.toString(round(y)));b.append("\" ");
    b.append("width=\"");
    b.append(Double.toString(round(w)));b.append("\" height=\"");
    b.append(Double.toString(round(h)));b.append("\" style=\"fill\" />\n");
  }
  
  public void setColor(Color c) {
    b.append("<color red=\"");
    b.append(Integer.toString(c.getRed()));b.append("\" green=\"");
    b.append(Integer.toString(c.getGreen()));b.append("\" blue=\"");
    b.append(Integer.toString(c.getBlue()));b.append("\"/>\n");
  }

  public void drawTriangle(double x, double y, double w) {
    double h = (w / 2) * sqrt(3);
    drawLine(x,y,x + w, y);
    drawLine(x + w, y, w + x - (w / 2), h);
    drawLine(w + x - (w / 2), h, x, y);
  }
}