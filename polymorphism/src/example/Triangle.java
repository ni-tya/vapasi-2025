package example;

public class Triangle extends Shape {
    int a, b, c;

    public Triangle(int side1, int side2, int side3) {
        this.a = side1;
        this.b = side2;
        this.c = side3;
    }

    @Override
    public double calculateArea() {
        if (a <= 0 || b <= 0 || c <= 0 || (a + b <= c) || (a + c <= b) || (b + c <= a)) {
            System.out.println("Invalid triangle sides provided.");
            return -1.0; // Or throw an exception
        }

        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double calculatePerimeter() {
        if (a + b <= c || a + c <= b || b + c <= a) {
            System.out.println("Warning: These side lengths do not form a valid triangle.");
        }
        return a + b + c;
    }
}
