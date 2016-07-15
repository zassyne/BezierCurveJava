/**
 *
 * @author yassine
 */
public class BezierCurve {

    // Array where we will store our factorials
    private double[] factorials;

    public BezierCurve() {
        initializeFactorials();
    }

    // To make it more fast
    // We don't need to calculate factorials in the runtime
    private void initializeFactorials() {
        //Lets fill up to 33!
        factorials = new double[33];
        factorials[0] = 1.0;
        factorials[1] = 1.0;
        factorials[2] = 2.0;
        factorials[3] = 6.0;
        factorials[4] = 24.0;
        factorials[5] = 120.0;
        factorials[6] = 720.0;
        factorials[7] = 5040.0;
        factorials[8] = 40320.0;
        factorials[9] = 362880.0;
        factorials[10] = 3628800.0;
        factorials[11] = 39916800.0;
        factorials[12] = 479001600.0;
        factorials[13] = 6227020800.0;
        factorials[14] = 87178291200.0;
        factorials[15] = 1307674368000.0;
        factorials[16] = 20922789888000.0;
        factorials[17] = 355687428096000.0;
        factorials[18] = 6402373705728000.0;
        factorials[19] = 121645100408832000.0;
        factorials[20] = 2432902008176640000.0;
        factorials[21] = 51090942171709440000.0;
        factorials[22] = 1124000727777607680000.0;
        factorials[23] = 25852016738884976640000.0;
        factorials[24] = 620448401733239439360000.0;
        factorials[25] = 15511210043330985984000000.0;
        factorials[26] = 403291461126605635584000000.0;
        factorials[27] = 10888869450418352160768000000.0;
        factorials[28] = 304888344611713860501504000000.0;
        factorials[29] = 8841761993739701954543616000000.0;
        factorials[30] = 265252859812191058636308480000000.0;
        factorials[31] = 8222838654177922817725562880000000.0;
        factorials[32] = 263130836933693530167218012160000000.0;

    }

    // Method that calculates binome (n i)
    private double binomeN_I(int n, int i) {
        double ni;
        double a1 = factorial(n);
        double a2 = factorial(i);
        double a3 = factorial(n - i);
        ni = a1 / (a2 * a3);
        return ni;
    }

    // Method that returns a factorial of given input n
    private double factorial(int n) {
        if (n < 0 || n > 32) {
            return -1;
        }
        return factorials[n];
    }

    private double bernstein(int n, int i, double t) {
        double basis;
        double ti; // a variabe where we store t^i

        double tni; // the same.. (1 - t)^i 

        // We check some known and specific values of t and i..
        if (t == 0.0 && i == 0) {
            ti = 1.0;
        } else {
            ti = Math.pow(t, i);
        }

        if (n == i && t == 1.0) {
            tni = 1.0;
        } else {
            tni = Math.pow((1 - t), (n - i));
        }

        // We return the bernestein coefficient
        basis = binomeN_I(n, i) * ti * tni;
        return basis;
    }
    
    // Almost the main method of our Class
    // it calculates and stores the values in the array p
    public void Bezier2D(double[] b, int cpts, double[] p) {
        int numberOfpoints = (b.length) / 2;
        int icounter, jcounter;
        double step, t;

        // We calculate the points of the curve
        icounter = 0;
        t = 0;
        step = (double) 1.0 / (cpts - 1);

        for (int i1 = 0; i1 != cpts; i1++) {
            if ((1.0 - t) < 5e-6) {
                t = 1.0;
            }

            jcounter = 0;
            p[icounter] = 0.0;
            p[icounter + 1] = 0.0;
            for (int i = 0; i != numberOfpoints; i++) {
                double basis = bernstein(numberOfpoints - 1, i, t);
                p[icounter] += basis * b[jcounter];
                p[icounter + 1] += basis * b[jcounter + 1];
                jcounter = jcounter + 2;
            }

            icounter += 2;
            t += step;
        }
    }

}
