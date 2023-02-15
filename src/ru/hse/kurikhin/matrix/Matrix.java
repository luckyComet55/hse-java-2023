package ru.hse.kurikhin.matrix;

import ru.hse.kurikhin.complex.Complex;
import java.util.Scanner;

public class Matrix {
  private int x, y;
  private Complex[][] matrix;

  public Matrix() {
    this(1, 1);
  }

  public Matrix(int x, int y) {
    this.x = x;
    this.y = y;
    matrix = new Complex[x][y];
  }

  public static Matrix createEmptyMatrix() throws IllegalArgumentException {
    Scanner in = new Scanner(System.in);
    System.out.println("Введите размеры матрицы (размер не может быть меньше либо равен нулю):");
    int x = in.nextInt();
    int y = in.nextInt();
    if (x <= 0 || y <= 0) {
      throw new IllegalArgumentException("Размерность матрицы не соответствует требованиям");
    }
    return new Matrix(x, y);
  }

  public static void inputMatrix(Matrix matrix) {
    Scanner in = new Scanner(System.in);
    System.out.println("Введите элементы матрицы:");
    for (int i = 0; i < matrix.y; ++i) {
      for (int j = 0; j < matrix.x; ++j) {
        double a = in.nextDouble();
        double b = in.nextDouble();
        matrix.matrix[i][j] = new Complex(a, b);
      }
    }
  }

  public void printMatrix() {
    for (int i = 0; i < y; ++i) {
      for (int j = 0; j < x; ++j) {
        matrix[i][j].printVector();
        System.out.print(' ');
      }
      System.out.println();
    }
  }

  public int getX() { return x; }
  public int getY() { return y; }




  public static void main(String[] args) {
    try {
      Matrix matrix = Matrix.createEmptyMatrix();
      Matrix.inputMatrix(matrix);
      matrix.printMatrix();
    } catch (IllegalArgumentException exception) {
      System.out.println(exception.getMessage());
    }
  }

}
