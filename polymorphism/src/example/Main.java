package example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Shape circle1 = new Circle(8);
        Shape circle2 = new Circle(7);
        Shape circle3 = new Circle(6);

        Shape rectangle1 = new Rectangle(5, 8);
        Shape rectangle2 = new Rectangle(9, 3);
        Shape rectangle3 = new Rectangle(2, 1);

        Shape triangle1 = new Triangle(3, 4, 5);
        Shape triangle2 = new Triangle(5, 7, 10);
        Shape triangle3 = new Triangle(6, 8, 10);

        List<Shape> shapesList = new ArrayList<>();
        shapesList.add(circle1);
        shapesList.add(circle2);
        shapesList.add(circle3);
        shapesList.add(rectangle1);
        shapesList.add(rectangle2);
        shapesList.add(rectangle3);
        shapesList.add(triangle1);
        shapesList.add(triangle2);
        shapesList.add(triangle3);

        for (Shape s : shapesList) {
            String shape = s.getClass().getSimpleName();
            System.out.println("Shape: " + shape + ", AREA: " + s.calculateArea());
            System.out.println("Shape: " + shape + ", PERIMETER: " + s.calculatePerimeter() + "\n");
        }
    }
}