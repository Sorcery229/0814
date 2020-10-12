public class Complex {
    public double real;
    public double imag;
    public Complex(double x, double y) {
        real = x;
        imag = y;
    }
    public Complex Plus(Complex a) {
        return new Complex (this.real+a.real, this.imag+a.imag);
    }
    public Complex Times(Complex a) {
        double real = this.real * a.real - this.imag * a.imag;
        double imag = this.real * a.imag + this.imag * a.real;
        return new Complex(real,imag);
    }
    public Complex Sopr() {
        return new Complex(real,-imag);
    }
    public Complex Abs() {
    return new Complex(Math.sqrt(real*real), Math.sqrt(imag*imag));
    }
}
