package fr.epita.mnist.datamodel;

public class Image {

    public double[][] getPixels() {
        return pixels;
    }

    public void setPixels(double[][] pixels) {
        this.pixels = pixels;
    }

    public Integer getDigit() {
        return digit;
    }

    public void setDigit(Integer digit) {
        this.digit = digit;
    }

    double[][] pixels = new double[28][28];

    Integer digit;

    public Image(Integer digit,double[] flattenPixels){
        this.digit = digit;
        double[][] matrix = new double[28][28];

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length ; j++ ){
                matrix[i][j] = flattenPixels[i * matrix.length + j];
            }
        }

        this.pixels = matrix;
    }

}
