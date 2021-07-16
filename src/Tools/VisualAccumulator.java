package Tools;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class VisualAccumulator {
    private static final String HELVETICA_FONT = "helvetica";
    private double total;
    private int size;
    private double lastComputedValue;
    public VisualAccumulator(int originalValue, double maxX, double maxY, String title, String xAxisLabel, String yAxisLabel){
        StdDraw.setXscale(-(maxX * .05), maxX + (maxX * .05));
        StdDraw.setYscale(-(maxY * .05), maxY + (maxY * .05));
        StdDraw.setPenRadius(.005);
        drawLabels(originalValue, maxX, maxY, title, xAxisLabel, yAxisLabel);
    }
    private void drawLabels(int originalValue, double maxX, double maxY, String title, String xAxisLabel, String yAxisLabel){
        Font font = new Font(HELVETICA_FONT, Font.BOLD, 12);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        // x axis label
        double xAxisLabelHeight = -(maxY * 0.025);
        StdDraw.text(maxX/2, xAxisLabelHeight, xAxisLabel);
        // y axis lable
        StdDraw.text(-(maxY * 15), maxY / 2, yAxisLabel, 90);
        StdDraw.setPenColor(StdDraw.BLACK);
        // Title
        StdDraw.text(maxX/2, maxY - (maxY * .02),  title);
        Font font1 = new Font(HELVETICA_FONT, Font.PLAIN, 12);
        StdDraw.setFont(font1);
        StdDraw.text(-(maxX * .01), xAxisLabelHeight, String.valueOf(originalValue));
        // X axis label
        StdDraw.text(-(maxX * .005), maxY - (maxY * .07), String.valueOf((int)maxY));
        // Y axis label
        StdDraw.text(maxX - (maxX * .06), xAxisLabelHeight, String.valueOf((int)maxX));
    }
    public void drawDataValue(double xCoordinate, double yCoordinate, Color color){
        StdDraw.setPenColor(color);
        StdDraw.point(xCoordinate, yCoordinate);
    }
    public double mean(){
        return total / size;
    }
    public void writeFinalMean(){
        StdDraw.setPenColor(StdDraw.RED);
        long roundMean = Math.round(mean());
        StdDraw.text(size + (size * 0.04), mean(), String.valueOf(roundMean));
    }
    public void writeExactFinalMean(){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(size + (size * 0.04), mean(), String.format("%.1f", mean()));
    }
    public void writeText(String text, double xCoordinate, double yCoordinate, Color color){
        StdDraw.setPenColor(color);
        StdDraw.text(xCoordinate, yCoordinate, text);
    }
    public String toString(){
        return "Mean (" + size + " value); " + String.format("%7.5f", mean());
    }
}
