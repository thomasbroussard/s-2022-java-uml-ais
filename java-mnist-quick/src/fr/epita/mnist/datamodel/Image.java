package fr.epita.mnist.datamodel;

public class Image {
    private int digit;
    private double[][] pixels;

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public double[][] getPixels() {
        return pixels;
    }

    public void setPixels(double[][] pixels) {
        this.pixels = pixels;
    }
}
