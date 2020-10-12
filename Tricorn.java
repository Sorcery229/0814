import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2;
        range.width = 4;
        range.height = 4;
    }


    public int numIterations(double x, double y) {
        int count = 0;
        Complex z = new Complex(0,0);
        Complex c = new Complex(x,y);


        while ((z.real*z.real+z.imag*z.imag)<4 && count<=MAX_ITERATIONS) {
            z = z.Sopr().Times(z.Sopr()).Plus(c);
            count++;
        }
        if (count>=MAX_ITERATIONS) return -1;
        else return count;
    }
    public String toString() {
        return "Tricorn";
    }
}
