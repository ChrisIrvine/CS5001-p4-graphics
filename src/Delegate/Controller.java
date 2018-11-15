package Delegate;

import Model.MandelbrotCalculator;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class Controller {

    private static MandelbrotCalculator mdc = new MandelbrotCalculator();


    static int[][] generateMandel(){
        return mdc.calcMandelbrotSet(Configuration.HEIGHT, Configuration.WIDTH,
                Configuration.INITIAL_MIN_REAL, Configuration.INITIAL_MAX_REAL,
                Configuration.INITIAL_MIN_IMAGINARY,
                Configuration.INITIAL_MAX_IMAGINARY,
                Configuration.INITIAL_MAX_ITERATIONS,
                Configuration.DEFAULT_RADIUS_SQUARED);
    }

    static void paintMandelbrot(GraphicsContext point, int[][] mandelbrotSet) {
        for(int i = 0; i < mandelbrotSet.length; i++) {
            for(int j = 0; j < mandelbrotSet[i].length; j++) {
                if(mandelbrotSet[j][i] >= 50) {
                    point.setFill(Color.BLACK);
                    //System.out.println("painting black");
                } else {
                    point.setFill(Color.GREY);
                }
                point.fillRect(i, j, 1.0, 1.0);
            }
        }
    }
}
