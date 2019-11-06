import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private int x;
    private int y;
    private List<Double> pixels;
    private Matrix vectorX0;

    public Rectangle(int x, int y) {
        pixels = new ArrayList<>();
        this.x = x;
        this.y = y;
    }


    public void createVectorX0() {
        double vector[][] = new double[1][pixels.size()];
        for (int i = 0; i < pixels.size(); i++) {
            vector[0][i] = pixels.get(i);
        }
        this.vectorX0 = new Matrix(vector);
    }


    public void addPixel(Color color) {
        this.pixels.add(getNewColorValue(color.getRed()));
        this.pixels.add(getNewColorValue(color.getGreen()));
        this.pixels.add(getNewColorValue(color.getBlue()));
    }

    public void add(double value){
        pixels.add(value);
    }

    private double getNewColorValue(double color) {
        return 2 * color / 255 - 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Matrix getVectorX0() {
        return vectorX0;
    }
}