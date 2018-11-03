import java.util.*;
class testMath {
        public static void main (String [] args) {
                System.out.println("enter a value: ");
                Scanner sc = new Scanner(System.in);
                double value = sc.nextDouble();
                System.out.println("enter a power: ");
                int power = sc.nextInt();
                System.out.println("Power is: " + Math.power(value,power));
                System.out.println("Using API Math, Power is: " + Math.pow(value,power));
        }
}