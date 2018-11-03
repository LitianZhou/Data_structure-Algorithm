public class Math {
        static double Math;
        public Math() {
                Math = 1;
        }
        public static double power(double value, int power) {
                for (int i = 0; i < power; i++)
                 Math *= value;
                return Math;
        }
}