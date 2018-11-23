package Model;

/**
 * Model class to save the parameters for the Mandelbrot set at a given point
 * in time.
 */
public class Setting {

    private final double minReal;
    private final double maxReal;
    private final double minImaginary;
    private final double maxImaginary;
    private final int iterations;

    /**
     * Constructor for the Setting Class.
     * @param minReal the lower real bound for the complex constant C (equivalent to lower bound X value in Mandelbrot set)
     * @param maxReal the upper real bound for the complex constant C (equivalent to upper bound X value in Mandelbrot set)
     * @param minImaginary the lower imaginary bound for the complex constant C (equivalent to lower bound Y value in Mandelbrot set)
     * @param maxImaginary the upper imaginary bound for the complex constant C (equivalent to upper bound Y value in Mandelbrot set)
     * @param iterations the maximum number of iterations to iterate the complex formula
     */
    public Setting(double minReal, double maxReal, double minImaginary,
                   double maxImaginary, int iterations) {
        this.minReal = minReal;
        this.maxReal = maxReal;
        this.minImaginary = minImaginary;
        this.maxImaginary = maxImaginary;
        this.iterations = iterations;
    }

    public double getMinReal() {
        return minReal;
    }

    public double getMaxReal() {
        return maxReal;
    }

    public double getMinImaginary() {
        return minImaginary;
    }

    public double getMaxImaginary() {
        return maxImaginary;
    }

    public int getIterations() {
        return iterations;
    }
}