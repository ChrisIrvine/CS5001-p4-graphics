package Delegate;

import Model.MandelbrotCalculator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Controller {

    private static MandelbrotCalculator mdc = new MandelbrotCalculator();
    private static int currentIterations = MandelbrotCalculator.getInitialMaxIterations();

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

        System.out.println("New min real: " + newMinReal);
        System.out.println("New max real: " + newMaxReal);
        System.out.println("New min im: " + newMinImagine);
        System.out.println("New max im: " + newMaxImagine);

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
                    //System.out.println("painting black");
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
