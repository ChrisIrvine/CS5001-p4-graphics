package Delegate;

import Model.MandelbrotCalculator;
import Model.Setting;
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
    private static double zoomFactor = 1.00;
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


    static void paintMandelbrot(GraphicsContext point, int[][] mandelbrotSet, int colour) {
        for(int i = 0; i < mandelbrotSet.length; i++) {
            for(int j = 0; j < mandelbrotSet[i].length; j++) {
                switch (colour) {
                    case 0:
                        if (mandelbrotSet[j][i] == settings.get(settingStep).getIterations() || mandelbrotSet[j][i] <= 2) {
                            point.setFill(Color.BLACK);
                        } else if (mandelbrotSet[j][i] <= settings.get(settingStep).getIterations() / 2) {
                            point.setFill(Color.rgb(255, 255, 255));
                        } else if (mandelbrotSet[j][i] > settings.get(settingStep).getIterations() / 2) {
                            point.setFill(Color.rgb(255, 255, 255));
                        }
                        break;
                    case 1:
                        if (mandelbrotSet[j][i] == settings.get(settingStep).getIterations() || mandelbrotSet[j][i] <= 2) {
                            point.setFill(Color.BLACK);
                        } else if (mandelbrotSet[j][i] <= settings.get(settingStep).getIterations() / 2) {
                            point.setFill(Color.rgb(255 / mandelbrotSet[j][i], 0, 0));
                        } else if (mandelbrotSet[j][i] > settings.get(settingStep).getIterations() / 2) {
                            point.setFill(Color.rgb(255, mandelbrotSet[j][i] / 255, mandelbrotSet[j][i] / 255));
                        }
                        break;
                    case 2:
                        if(mandelbrotSet[j][i] == settings.get(settingStep).getIterations() || mandelbrotSet[j][i] <= 2) {
                            point.setFill(Color.BLACK);
                        } else if (mandelbrotSet[j][i] <= settings.get(settingStep).getIterations()/2){
                            point.setFill(Color.rgb(0, 255/mandelbrotSet[j][i], 0));
                        } else if (mandelbrotSet[j][i] > settings.get(settingStep).getIterations()/2){
                            point.setFill(Color.rgb(mandelbrotSet[j][i]/255, 255, mandelbrotSet[j][i]/255));
                        }
                        break;
                    case 3:
                        if(mandelbrotSet[j][i] == settings.get(settingStep).getIterations() || mandelbrotSet[j][i] <= 2) {
                            point.setFill(Color.BLACK);
                        } else if (mandelbrotSet[j][i] <= settings.get(settingStep).getIterations()/2){
                            point.setFill(Color.rgb(0, 0, 255/mandelbrotSet[j][i]));
                        } else if (mandelbrotSet[j][i] > settings.get(settingStep).getIterations()/2){
                            point.setFill(Color.rgb(mandelbrotSet[j][i]/255, mandelbrotSet[j][i]/255, 255));
                        }
                        break;
                    case 4:
                        if(mandelbrotSet[j][i] == settings.get(settingStep).getIterations() || mandelbrotSet[j][i] <= 2) {
                            point.setFill(Color.BLACK);
                        } else if (mandelbrotSet[j][i] <= settings.get(settingStep).getIterations()/2){
                            point.setFill(Color.rgb(0, 255/mandelbrotSet[j][i], 255/mandelbrotSet[j][i]));
                        } else if (mandelbrotSet[j][i] > settings.get(settingStep).getIterations()/2){
                            point.setFill(Color.rgb(mandelbrotSet[j][i]/255, 255, 255));
                        }
                        break;
                    case 5:
                        if(mandelbrotSet[j][i] == settings.get(settingStep).getIterations() || mandelbrotSet[j][i] <= 2) {
                            point.setFill(Color.BLACK);
                        } else if (mandelbrotSet[j][i] <= settings.get(settingStep).getIterations()/2){
                            point.setFill(Color.rgb(255/mandelbrotSet[j][i], 255/mandelbrotSet[j][i], 0));
                        } else if (mandelbrotSet[j][i] > settings.get(settingStep).getIterations()/2){
                            point.setFill(Color.rgb(255, 255, mandelbrotSet[j][i]/255));
                        }
                        break;
                    case 6:
                        if(mandelbrotSet[j][i] == settings.get(settingStep).getIterations() || mandelbrotSet[j][i] <= 2) {
                            point.setFill(Color.BLACK);
                        } else if (mandelbrotSet[j][i] <= settings.get(settingStep).getIterations()/2){
                            point.setFill(Color.rgb(255/mandelbrotSet[j][i], 0, 255/mandelbrotSet[j][i]));
                        } else if (mandelbrotSet[j][i] > settings.get(settingStep).getIterations()/2){
                            point.setFill(Color.rgb(255, mandelbrotSet[j][i]/255, 255));
                        }
                        break;
                    default:
                        if (mandelbrotSet[j][i] == settings.get(settingStep).getIterations() || mandelbrotSet[j][i] <= 2) {
                            point.setFill(Color.BLACK);
                        } else if (mandelbrotSet[j][i] <= settings.get(settingStep).getIterations() / 2) {
                            point.setFill(Color.rgb(255, 255, 255));
                        } else if (mandelbrotSet[j][i] > settings.get(settingStep).getIterations() / 2) {
                            point.setFill(Color.rgb(255, 255, 255));
                        }
                        break;
                }
                point.fillRect(i, j, 1, 1);
            }
        }
    }

    private static double scalePos(double range , double pos, int dimension, double initial) {
        double realGap = pos / (double) dimension;
        System.out.println("Gap: " + realGap);
        return (realGap * range) + initial;
    }

    static void updateIterations(int newIterations) {
        currentIterations = newIterations;
    }

    static int getIterations() {
        return currentIterations;
    }

    static double getZoomFactor() {
        return zoomFactor;
    }


    static int[][] generateMandel() {
        System.out.println("Settings: " + settingStep);
        System.out.println(settings.get(settingStep).getMinReal() + ", " + settings.get(settingStep).getMaxReal());
        return mdc.calcMandelbrotSet(Configuration.WIDTH, Configuration.HEIGHT,
                settings.get(settingStep).getMinReal(),
                settings.get(settingStep).getMaxReal(),
                settings.get(settingStep).getMinImaginary(),
                settings.get(settingStep).getMaxImaginary(),
                settings.get(settingStep).getIterations(),
                MandelbrotCalculator.getDefaultRadiusSquared());
    }

    static void updateParams(double x, double x1, double y, double y1) {
        double realRange = currentMaxReal - currentMinReal;
        double imagineRange = currentMaxImaginary - currentMinImaginary;
        double holder = currentMinReal;
        currentMinReal = scalePos(realRange, x, Configuration.WIDTH, currentMinReal);
        currentMaxReal = scalePos(realRange, x1, Configuration.WIDTH, holder);
        holder = currentMinImaginary;
        currentMinImaginary = scalePos(imagineRange, y, Configuration.HEIGHT, currentMinImaginary);
        currentMaxImaginary = scalePos(imagineRange, y1, Configuration.HEIGHT, holder);
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

    static void calculateDistance(double startX, double endX, double startY,
                                  double endY) {
        double xDistance = (startX - endX);
        double yDistance = (startY - endY);
        xDistance = (xDistance / Configuration.HEIGHT)
                * Math.abs(currentMinReal - currentMaxReal);
        yDistance = (yDistance / Configuration.HEIGHT)
                * Math.abs(currentMinImaginary - currentMaxImaginary);
        currentMinReal += xDistance;
        currentMaxReal += xDistance;
        currentMinImaginary += yDistance;
        currentMaxImaginary += yDistance;
    }

    static void calculateZoom() {
        Controller.zoomFactor *= Math.max(Math.abs(
                MandelbrotCalculator.getInitialMinReal() -
                        settings.get(settingStep).getMinReal())/
                Configuration.WIDTH,
                Math.abs(MandelbrotCalculator.getInitialMinImaginary() -
                        settings.get(settingStep).getMinImaginary())/
                        Configuration.WIDTH);
    }
}
