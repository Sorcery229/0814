import java.util.Scanner;
import java.text.DecimalFormat;
public class Lab1 {
    public static void main(String[] args) {
        DecimalFormat ft = new DecimalFormat("#.##");
        Point3d dot1 = new Point3d();
        Point3d dot2 = new Point3d();
        Point3d dot3 = new Point3d();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter first coordinates:");
        System.out.print("Input first number: ");
        dot1.setX(in.nextInt());
        System.out.print("Input second number: ");
        dot1.setY(in.nextInt());
        System.out.print("Input third number: ");
        dot1.setZ(in.nextInt());
        System.out.println("Enter second coordinates:");
        System.out.print("Input first number: ");
        dot2.setX(in.nextInt());
        System.out.print("Input second number: ");
        dot2.setY(in.nextInt());
        System.out.print("Input third number: ");
        dot2.setZ(in.nextInt());
        System.out.println("Enter third coordinates:");
        System.out.print("Input first number: ");
        dot3.setX(in.nextInt());
        System.out.print("Input second number: ");
        dot3.setY(in.nextInt());
        System.out.print("Input third number: ");
        dot3.setZ(in.nextInt());
        if (dot1.Equals(dot2) || dot1.Equals(dot3) || dot2.Equals(dot3)) System.out.println("Some of the points are the same.");
        else
        System.out.println("Your triangle's area equals: " + ft.format(computeArea(dot1, dot2, dot3)));
    }
public static double computeArea(Point3d a, Point3d b, Point3d c) {
        double p = (a.distanceTo(b)+b.distanceTo(c)+c.distanceTo(a))/2;
        double AB = a.distanceTo(b);
        double BC = b.distanceTo(c);
        double AC = c.distanceTo(a);
        return Math.sqrt(p*(p-AB)*(p-BC)*(p-AC));
}

}
