
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yassine
 */
public class Cardano {
    
    
    static class Point {
        double px;
        double py;
    }
    
    final static int numberOfPoints = 4;
    static Point[] controllPoints = new Point[numberOfPoints];
    static Point[] coefficients = new Point[numberOfPoints];
    static Point bezierPoint = new Point();
    static double P, Q, K, A, B, squareRoot;
    static double t, y;
    
    public static void main(String[] args) {

        allocateMemoryForPoints();
        getInputs();
        doCalculation1();
        printCubicEquation();
        doCalculation2();
        printFinalResult();
        
    }
    
 
    private static void printFinalResult() {
        System.out.println(" t = " + t);
        bezierPoint.py = coefficients[0].py * t * t * t + coefficients[1].py * t * t + coefficients[2].py * t + coefficients[3].py;
        System.out.println("By = " + bezierPoint.py);
    }
    
    private static void doCalculation2() {
        
        coefficients[0].py = -controllPoints[0].py + 3 * controllPoints[1].py - 3 * controllPoints[2].py + controllPoints[3].py;
        coefficients[1].py = 3 * controllPoints[0].py - 6 * controllPoints[1].py + 3 * controllPoints[2].py;
        coefficients[2].py = (-3) * controllPoints[0].py + 3 * controllPoints[1].py;
        coefficients[3].py = controllPoints[0].py;
        
        P = (3 * coefficients[0].px * coefficients[2].px - coefficients[1].px * coefficients[1].px) / (3 * coefficients[0].px * coefficients[0].px);
        Q = (2 * coefficients[1].px * coefficients[1].px * coefficients[1].px - 9 * coefficients[0].px * coefficients[1].px * coefficients[2].px + 27 * coefficients[0].px * coefficients[0].px * coefficients[3].px)/(27 * coefficients[0].px * coefficients[0].px *  coefficients[0].px);
        
        K = (P/3) * (P/3) * (P/3) + (Q/2) * (Q/2);
        squareRoot = Math.sqrt(K);
        
        if ((-Q/2)+squareRoot>=0) 
            A = Math.pow(((-Q/2)+squareRoot),1./3.);
        else {
            A = Math.pow(Math.abs(((-Q/2)+squareRoot)),1./3.);
            A = -A;
	}
	if ((-Q/2)-squareRoot>=0) B = Math.pow((-Q/2-squareRoot),1./3.);
	else {
            B = Math.pow(Math.abs((-Q/2-squareRoot)),1./3.);
            B = -B;
	}
		
        y = A+B;
	t = y - (coefficients[1].px/(3*coefficients[0].px));
        
    }
    
    private static void printCubicEquation() {
        System.out.println("The resulting cubic equation : ");
        System.out.println( coefficients[0].px + "t^3 + " + coefficients[1].px + "t^2 + " + coefficients[2].px + "t + " + coefficients[3].px + " = 0");
    }
    
    private static void doCalculation1() {
        coefficients[0].px = -controllPoints[0].px + 3 * controllPoints[1].px - 3 * controllPoints[2].px + controllPoints[3].px;
        coefficients[1].px = 3 * controllPoints[0].px - 6 * controllPoints[1].px + 3 * controllPoints[2].px;
        coefficients[2].px = (-3) * controllPoints[0].px + 3 * controllPoints[1].px;
        coefficients[3].px = controllPoints[0].px - bezierPoint.px;
    }
    
    private static void allocateMemoryForPoints() {
        
        for(int k = 0; k<numberOfPoints; k++) {
            controllPoints[k] = new Point();
            coefficients[k] = new Point();
        }
    }
     
    private static void getInputs() {
        
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while( i < numberOfPoints) {
            System.out.println("Input the coordinates (x,y) of the controll point : #" + (i+1));
            controllPoints[i].px = sc.nextDouble();
            controllPoints[i].py = sc.nextDouble();
            i++;
        }
        System.out.println("Input the x-coordinate of Bezier curve :");
        System.out.print("Bx : ");
        bezierPoint.px = sc.nextDouble();
    }
    
}
