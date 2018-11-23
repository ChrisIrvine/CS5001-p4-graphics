package Delegate;

import Model.MandelbrotCalculator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;


public class Controller {

    private static MandelbrotCalculator mdc = new MandelbrotCalculator();
    private static int settingStep = -1;
    private static int currentIterations = MandelbrotCalculator.getInitialMaxIterations();
    private static double currentMinReal = MandelbrotCalculator.getInitialMinReal();
    private static double currentMaxReal = MandelbrotCalculator.getInitialMaxReal();
    private static double currentMinImaginary = MandelbrotCalculator.getInitialMinImaginary();
    private static double currentMaxImaginary = MandelbrotCalculator.getInitialMaxImaginary();

    // Setting Array = [minReal, maxReal, minImaginary, maxImaginary, iterations]
    private static ArrayList<Setting> settings = new ArrayList<>();


    static int[][] generateDefaultMandel(){
        Setting s = new Setting(MandelbrotCalculator.getInitialMinReal(),
                MandelbrotCalculator.getInitialMaxReal(),
                MandelbrotCalculator.getInitialMinImaginary(),
                MandelbrotCalculator.getInitialMaxImaginary(),
                MandelbrotCalculator.getInitialMaxIterations());

        settings.add(s);
        settingStep++;

        return mdc.calcMandelbrotSet(Configuration.WIDTH, Configuration.HEIGHT,
                s.getMinReal(), s.getMaxReal(), s.getMinImaginary(),
                s.getMaxImaginary(), s.getIterations(),
                MandelbrotCalculator.getDefaultRadiusSquared());
    }
//
//    static int[][] generateZoomedMandel(double newMinReal, double newMaxReal, double newMinImagine, double newMaxImagine) {
//        newMinReal = scalePos(MandelbrotCalculator.getInitialMinReal(), MandelbrotCalculator.getInitialMaxReal(), newMinReal, Configuration.WIDTH);
//        newMaxReal = scalePos(MandelbrotCalculator.getInitialMinReal(), MandelbrotCalculator.getInitialMaxReal(), newMaxReal, Configuration.WIDTH);
//        newMinImagine = scalePos(MandelbrotCalculator.getInitialMinReal(), MandelbrotCalculator.getInitialMaxReal(), newMinImagine, Configuration.HEIGHT);
//        newMaxImagine = scalePos(MandelbrotCalculator.getInitialMinReal(), MandelbrotCalculator.getInitialMaxReal(), newMaxImagine, Configuration.HEIGHT);
//
//        currentMinReal = newMinReal;
//        currentMaxReal = newMaxReal;
//        currentMinImaginary = newMinImagine;
//        currentMaxImaginary = newMaxImagine;
//
//
//
//        System.out.println("Real: " + currentMinReal + ", " + currentMaxReal);
//        System.out.println("Imaginary: " + currentMinImaginary + ", " + currentMaxImaginary);
//
//        return mdc.calcMandelbrotSet(Configuration.WIDTH, Configuration.HEIGHT,
//                newMinReal, newMaxReal, newMinImagine, newMaxImagine,
//                currentIterations,
//                MandelbrotCalculator.getDefaultRadiusSquared());
//    }

    static void paintMandelbrot(GraphicsContext point, int[][] mandelbrotSet) {
        for(int i = 0; i < mandelbrotSet.length; i++) {
            for(int j = 0; j < mandelbrotSet[i].length; j++) {
                if(mandelbrotSet[j][i] == settings.get(settingStep).getIterations() || mandelbrotSet[j][i] <= 2) {
                    point.setFill(Color.BLACK);
                } else if (mandelbrotSet[j][i] <= settings.get(settingStep).getIterations()/2){
                    point.setFill(Color.rgb(255/mandelbrotSet[j][i], 0, 0));
                } else if (mandelbrotSet[j][i] > settings.get(settingStep).getIterations()/2){
                    point.setFill(Color.rgb(255, mandelbrotSet[j][i]/255, mandelbrotSet[j][i]/255));
                }
                point.fillRect(i, j, 1, 1);
            }
        }
    }

    private static double scalePos(double startMin, double startMax, double pos, int dimension) {
        double realGap = (startMin - startMax) / dimension;
        System.out.println(realGap);
        return realGap * pos;
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
        System.out.println("Settings: " + settingStep);
        return mdc.calcMandelbrotSet(Configuration.WIDTH, Configuration.HEIGHT,
                settings.get(settingStep).getMinReal(),
                settings.get(settingStep).getMaxReal(),
                settings.get(settingStep).getMinImaginary(),
                settings.get(settingStep).getMaxImaginary(),
                settings.get(settingStep).getIterations(),
                MandelbrotCalculator.getDefaultRadiusSquared());
    }

    static void updateParams(double x, double x1, double y, double y1) {
        double tempHolder = currentMinReal;
        currentMinReal = scalePos(currentMinReal, currentMaxReal, x, Configuration.WIDTH);
        currentMaxReal = scalePos(tempHolder, currentMaxReal, x1, Configuration.WIDTH);
        tempHolder = currentMaxImaginary;
        currentMinImaginary = scalePos(currentMinImaginary, currentMaxImaginary, y, Configuration.HEIGHT);
        currentMaxImaginary = scalePos(tempHolder, currentMaxImaginary, y1, Configuration.HEIGHT);
    }

    static void restoreDefaults() {
        currentMinReal = MandelbrotCalculator.getInitialMinReal();
        currentMaxReal = MandelbrotCalculator.getInitialMaxReal();
        currentMinImaginary = MandelbrotCalculator.getInitialMinImaginary();
        currentMaxImaginary = MandelbrotCalculator.getInitialMaxImaginary();
        currentIterations = MandelbrotCalculator.getInitialMaxIterations();
    }

    static void undo() {
        if(settingStep != 0) {
            settingStep--;
        } else {
            System.out.println("Nothing else to undo");
        }
    }

    static void redo() {
        if(settingStep != settings.size()-1) {
            settingStep++;
        } else {
            System.out.println("Nothing else to redo");
        }
    }

    static void createSetting() {
        Setting s = new Setting(currentMinReal, currentMaxReal,
                currentMinImaginary, currentMaxImaginary, currentIterations);

        settings.add(s);
        settingStep++;
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
