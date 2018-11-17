package Delegate;

import Model.MandelbrotCalculator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.event.MouseAdapter;


public class Controller {

    private static MandelbrotCalculator mdc = new MandelbrotCalculator();
    private static int currentIterations = MandelbrotCalculator.getInitialMaxIterations();
    private static double currentMinReal = MandelbrotCalculator.getInitialMinReal();
    private static double currentMaxReal = MandelbrotCalculator.getInitialMaxReal();
    private static double currentMinImaginary = MandelbrotCalculator.getInitialMinImaginary();
    private static double currentMaxImaginary = MandelbrotCalculator.getInitialMaxImaginary();

    static int[][] generateDefaultMandel(){
        return mdc.calcMandelbrotSet(Configuration.WIDTH, Configuration.HEIGHT,
                MandelbrotCalculator.getInitialMinReal(),
                MandelbrotCalculator.getInitialMaxReal(),
                MandelbrotCalculator.getInitialMinImaginary(),
                MandelbrotCalculator.getInitialMaxImaginary(),
                MandelbrotCalculator.getInitialMaxIterations(),
                MandelbrotCalculator.getDefaultRadiusSquared());
    }

    static int[][] generateZoomedMandel(double newMinReal, double newMaxReal, double newMinImagine, double newMaxImagine) {
        newMinReal = scalePos(MandelbrotCalculator.getInitialMinReal(), MandelbrotCalculator.getInitialMaxReal(), newMinReal);
        newMaxReal = scalePos(MandelbrotCalculator.getInitialMinReal(), MandelbrotCalculator.getInitialMaxReal(), newMaxReal);
        newMinImagine = scalePos(MandelbrotCalculator.getInitialMinReal(), MandelbrotCalculator.getInitialMaxReal(), newMinImagine);
        newMaxImagine = scalePos(MandelbrotCalculator.getInitialMinReal(), MandelbrotCalculator.getInitialMaxReal(), newMaxImagine);

        currentMinReal = newMinReal;
        currentMaxReal = newMaxReal;
        currentMinImaginary = newMinImagine;
        currentMaxImaginary = newMaxImagine;

        return mdc.calcMandelbrotSet(Configuration.WIDTH, Configuration.HEIGHT,
                newMinReal, newMaxReal, newMinImagine, newMaxImagine,
                currentIterations,
                MandelbrotCalculator.getDefaultRadiusSquared());
    }

    static void paintMandelbrot(GraphicsContext point, int[][] mandelbrotSet) {
        for(int i = 0; i < mandelbrotSet.length; i++) {
            for(int j = 0; j < mandelbrotSet[i].length; j++) {
                if(mandelbrotSet[j][i] == currentIterations || mandelbrotSet[j][i] <= 2) {
                    point.setFill(Color.BLACK);
                } else if (mandelbrotSet[j][i] <= currentIterations/2){
                    point.setFill(Color.rgb(255/mandelbrotSet[j][i], 0, 0));
                } else if (mandelbrotSet[j][i] > currentIterations/2){
                    point.setFill(Color.rgb(255, mandelbrotSet[j][i]/255, mandelbrotSet[j][i]/255));
                }
                point.fillRect(i, j, 1, 1);
            }
        }
    }

    private static double scalePos(double startMinReal, double startMaxReal, double realPos) {
        double realGap = (startMinReal - startMaxReal) / Configuration.WIDTH;
        return realGap * realPos;
    }

    static void updateIterations(int newIterations) {
        currentIterations = newIterations;
    }

    static int getIterations() {
        return currentIterations;
    }

    static double getCurrentMinReal() {
        return currentMinReal;
    }

    static int[][] generateMandel() {
        return mdc.calcMandelbrotSet(Configuration.WIDTH, Configuration.HEIGHT,
                currentMinReal, currentMaxReal, currentMinImaginary,
                currentMaxImaginary, currentIterations, MandelbrotCalculator.getDefaultRadiusSquared());
    }

    static void updateParams(double x, double x1, double y, double y1) {
        double tempHolder = currentMinReal;
        currentMinReal = scalePos(currentMinReal, currentMaxReal, x);
        currentMaxReal = scalePos(tempHolder, currentMaxReal, x1);
        tempHolder = currentMaxImaginary;
        currentMinImaginary = scalePos(currentMinImaginary, currentMaxImaginary, y);
        currentMaxImaginary = scalePos(tempHolder, currentMaxImaginary, y1);
    }
//
//
//    private static double[] scaleXY(double j, double i) {
//        //calculate the distance from min x to max x
//        //divide by number of points in x
//        //multiple by j
//        double xGap = (MandelbrotCalculator.getInitialMinReal() - MandelbrotCalculator.getInitialMaxReal()) / Configuration.WIDTH;
//        sx = xGap * j;
//
//        //repeat using the imaginary numbers to get the sy
//        double yGap = (MandelbrotCalculator.getInitialMinImaginary() - MandelbrotCalculator.getInitialMaxImaginary()) / Configuration.HEIGHT;
//        sy = yGap * i;
//
//        return new Double[2]{sx, sy};
//    }
}
