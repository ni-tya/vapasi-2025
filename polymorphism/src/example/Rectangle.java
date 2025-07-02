package example;

public class Rectangle extends Shape {
    int length, width;
    private boolean isSquare = false;

    public Rectangle(int width, int length) {
        if (length == width) {
            setDimensions(length);
            isSquare = true;
        } else
            setDimensions(length, width);
    }

    public void setDimensions(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public void setDimensions(int length) {
        this.length = length;
        this.width = length;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        if (isSquare)
            return 4 * length;
        return 2 * (length + width);
    }
}
