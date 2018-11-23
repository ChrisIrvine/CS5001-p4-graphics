package Delegate;

import Model.MandelbrotCalculator;
import Model.Setting;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * Class to control the View components of the Main Class
 */
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


    static void updateIterations(int newIterations) {
        currentIterations = newIterations;
    }

    static int getIterations() {
        return currentIterations;
    }

    static double getZoomFactor() {
        return zoomFactor;
    }

    /**
     * Method that will generate the default Mandelbrot Set based on the
     * parameters found the Model.MandelbrotCalculator.java. Will add the
     * parameters as a new Setting object to Settings.
     * @return double integer array containing the default Mandelbrot Set
     */
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

    /**
     * Method to generate a mutated Mandelbrot Set based on the parameters found
     * in the chosen Setting object within the Setting ArrayList.
     * @return double integer array containing the mutated Mandelbrot Set
     */
    static int[][] generateMandel() {
        return mdc.calcMandelbrotSet(Configuration.WIDTH, Configuration.HEIGHT,
                settings.get(settingStep).getMinReal(),
                settings.get(settingStep).getMaxReal(),
                settings.get(settingStep).getMinImaginary(),
                settings.get(settingStep).getMaxImaginary(),
                settings.get(settingStep).getIterations(),
                MandelbrotCalculator.getDefaultRadiusSquared());
    }

    /**
     * Extensive method that will take a MandelbrotSet, iterate through the set
     * and at each point assign the appropriate colour (which is dictated by a
     * integer parameter). Uses the GraphicsContext from the host Canvas
     * for the Mandelbrot Set Image.
     * @param point - GraphicsContext for the Canvas hosting the Mandelbrot
     *              Set Image
     * @param mandelbrotSet - Mandelbrot Set Numbers inside double Integer Array
     * @param colour - integer that dictates what colour the pixels will be.
     */
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

    /**
     * Method to scale the position selected by the user on the canvas to the
     * X and Y axis of the Mandelbrot Set, so that parameters can be updated
     * and a mutated Mandelbrot Set can be generated in the future.
     * @param range - range between the start and end of the X/Y axis
     * @param pos - position selected on the grid by the user
     * @param dimension - X or Y axis size
     * @param initial - the previous start of the given axis (minReal or minImaginary)
     * @return - scaled position as a double
     */
    private static double scalePos(double range , double pos, int dimension, double initial) {
        double realGap = pos / (double) dimension;
        return (realGap * range) + initial;
    }

    /**
     * Method to reset the Controller's class variables that dictate the
     * parameters for Mandelbrot set to the defaults found in the
     * MandelbrotCalculator class.
     */
    static void restoreDefaults() {
        currentMinReal = MandelbrotCalculator.getInitialMinReal();
        currentMaxReal = MandelbrotCalculator.getInitialMaxReal();
        currentMinImaginary = MandelbrotCalculator.getInitialMinImaginary();
        currentMaxImaginary = MandelbrotCalculator.getInitialMaxImaginary();
        currentIterations = MandelbrotCalculator.getInitialMaxIterations();
    }

    /**
     * Method to move one Settings ArrayList point down one position. Will not
     * go below 0.
     */
    static void undo() {
        if(settingStep != 0) settingStep--;
    }

    /**
     * Method to move the Settings ArrayList point up one position. Will not go
     * above the length of the Settings ArrayList (adjusted for starting at 0).
     */
    static void redo() {
        if(settingStep != settings.size()-1) settingStep++;
    }

    /**
     * Method to create a new Setting Object given the current parameters held
     * by the Controller class, which is then added to the Settings ArrayList.
     * Finally the pointer for the Settings ArrayList is incremented by one.
     */
    static void createSetting() {
        Setting s = new Setting(currentMinReal, currentMaxReal,
                currentMinImaginary, currentMaxImaginary, currentIterations);
        settings.add(s);
        settingStep++;
    }

    /**
     * Method to update the parameters held by the Controller class which
     * dictates the form of the Mandelbrot Set Numbers. Will take the new
     * coordinates from the Canvas (for the zoom) and scale them according to
     * the real and imaginary ranges that dictate the Mandelbrot Set.
     * @param x - start position on X axis
     * @param x1 - end position on X axis
     * @param y - start position on Y axis
     * @param y1 - end position on Y axis
     */
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

    /**
     * Method to calculate the distance in which the user wants to pan, and then
     * scale that distance according to the ranges of the Mandelbrot Set
     * parameters. The scaled distance is then added to the parameters,
     * effectively moving the image in the desired direction.
     * @param startX - start position on X axis
     * @param endX - end position on X axis
     * @param startY - start position on Y axis
     * @param endY - end position on Y axis
     */
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

    /**
     * Method to calculate the Zoom Factor of a given Zoom. Will assign the
     * result to the class variable zoomFactor. The larger of the result from
     * the X or Y axis is taken.
     */
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
