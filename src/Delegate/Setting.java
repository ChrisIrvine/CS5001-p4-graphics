package Delegate;

public class Setting {

    private final double minReal;
    private final double maxReal;
    private final double minImaginary;
    private final double maxImaginary;
    private final int iterations;

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