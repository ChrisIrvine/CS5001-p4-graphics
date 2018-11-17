package Delegate;

import Model.MandelbrotCalculator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Controller {

    private static MandelbrotCalculator mdc = new MandelbrotCalculator();
    private static double sx; //scaled double value of x coordinate
    private static double sy; //scaled double value of y coordinate


    static int[][] generateMandel(){
        return mdc.calcMandelbrotSet(Configuration.WIDTH, Configuration.HEIGHT,
                MandelbrotCalculator.getInitialMinReal(),
                MandelbrotCalculator.getInitialMaxReal(),
                MandelbrotCalculator.getInitialMinImaginary(),
                MandelbrotCalculator.getInitialMaxImaginary(),
                MandelbrotCalculator.getInitialMaxIterations(),
                MandelbrotCalculator.getDefaultRadiusSquared());
    }

    static void paintMandelbrot(GraphicsContext point, int[][] mandelbrotSet) {
        for(int i = 0; i < mandelbrotSet.length; i++) {
            for(int j = 0; j < mandelbrotSet[i].length; j++) {
                scaleXY(j, i);
                if(mandelbrotSet[j][i] == 50 || mandelbrotSet[j][i] <= 2) {
                    point.setFill(Color.BLACK);
                    //System.out.println("painting black");
                } else if (mandelbrotSet[j][i] <= 20){
                    point.setFill(Color.rgb(255/mandelbrotSet[j][i], 0, 0));
                } else if (mandelbrotSet[j][i] > 21){
                    point.setFill(Color.rgb(255, mandelbrotSet[j][i]/255, mandelbrotSet[j][i]/255));
                }
                point.fillRect(i, j, 1, 1);
            }
        }
    }

    private static void scaleXY(int j, int i) {
        //calculate the distance from min x to max x
        //divide by number of points in x
        //multiple by j
        double xGap = (MandelbrotCalculator.getInitialMinReal() - MandelbrotCalculator.getInitialMaxReal()) / Configuration.WIDTH;
        sx = xGap * j;

        //repeat using the imaginary numbers to get the sy
        double yGap = (MandelbrotCalculator.getInitialMinImaginary() - MandelbrotCalculator.getInitialMaxImaginary()) / Configuration.HEIGHT;
        sy = yGap * i;
    }
}
