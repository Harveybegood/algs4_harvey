package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

import static javafx.application.Application.launch;


/*
*   Modify DoublingTest to use StdDraw to produce plots like the standard and log-log plots in the text, rescaling as
*   necessary so that the plot always fills a substantial portion of the window.
*
* */
public class Ex03_DoublingTestPlot extends Application{
/*    public static int[] a;

    public static class ThreeSum{
        public static int count(int[] a){
            int cnt = 0;
            for (int i = 0; i < a.length; i++){
                for (int j = i+1; j < a.length; j++){
                    for (int k = j+1; k < a.length; k++){
                        if (a[i] + a[j] + a[k] == 0){
                            cnt++;
                        }
                    }
                }
            }
            return cnt;
        }
    }
    public static class DoublingTest{

        public static double timeTrial(int n){
            int max = 10000;
            a = new int[n];
            for (int i = 0; i < n; i++){
                a[i] = StdRandom.uniform(-max, max);
            }
            Stopwatch timer = new Stopwatch();
            int cnt = ThreeSum.count(a);
            return timer.elapsedTime();
        }
    }
    //Point2D[] p = new Point2D[3];
    public Point2D[] drawCoordinatePoints(){
        java.awt.geom.Point2D[] p = new java.awt.geom.Point2D[3];
        //Point2D[] p = new Point2D[3];
        for (int n = 2000; n <= 8000; n *= 2) {
            double time = DoublingTest.timeTrial(n);
            StdOut.printf("%7d %5.1f\n", n, time);
            StdDraw.setXscale(0, 8500);
            StdDraw.setYscale(0, 35);
            StdDraw.setPenColor(StdDraw.BLACK);

            StdDraw.text(200, 15, "Running time T(N)", 90);
            for (double i = 5, j = 0; i <= 30; i += 5) {

                StdDraw.text(600, i, (int) ++j + "0");
            }
            for (double i = 5; i <= 30; i += 5) {
                StdDraw.line(900, i , 1000, i);
            }
            StdDraw.line(1000, 3.5, 1000, 34);


            StdDraw.text(4000, 1, "problem size N", 0);
            for (double i = 1000; i <= 8000; i *= 2) {
                StdDraw.text(i, 2.0,  (int)i/1000 + "k");
            }
            for (double i = 2000; i <= 8000; i += 1000) {
                StdDraw.line(i, 3.0, i, 3.5);
            }
            StdDraw.line(1000, 3.5, 8000, 3.5);

            StdDraw.setPenRadius(.01);
            StdDraw.setPenColor(StdDraw.RED);
            //StdDraw.point(n, time);
            StdDraw.point(Math.log(n), Math.log(time));
            *//*for (int i = 0; i < 3; i++){
                p[i] = new java.awt.geom.Point2D.Double(n, time);
            }*//*
        }
        return p;
    }
    public static void main(String[] args) {
        Ex03_DoublingTestPlot doublingTestPlot = new Ex03_DoublingTestPlot();
        java.awt.geom.Point2D[] p = doublingTestPlot.drawCoordinatePoints();

    }*/
public void start(Stage stage) {
    //Creating a QuadCurve
    QuadCurve quadCurve = new QuadCurve();

    //Adding properties to the Quad Curve
    quadCurve.setStartX(100.0);
    quadCurve.setStartY(220.0f);
    quadCurve.setEndX(500.0f);
    quadCurve.setEndY(220.0f);
    quadCurve.setControlX(250.0f);
    quadCurve.setControlY(0.0f);

    //Creating a Group object
    Group root = new Group(quadCurve);

    //Creating a scene object
    Scene scene = new Scene(root, 600, 300);

    //Setting title to the Stage
    stage.setTitle("Drawing a Quad curve");

    //Adding scene to the stage
    stage.setScene(scene);

    //Displaying the contents of the stage
    stage.show();
}
    public static void main(String args[]){
        launch(args);
    }
}
