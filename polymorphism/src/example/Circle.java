package example;

public class Circle extends Shape {
    int radius;
    static double PI = Math.PI;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        double area;
        area = PI * radius * radius;
        return area;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * PI * radius;
    }
}
