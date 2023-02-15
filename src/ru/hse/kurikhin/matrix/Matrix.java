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

  public static Matrix sum(Matrix first, Matrix second) throws IllegalArgumentException {
    if (first.x != second.x || first.y != second.y) {
      throw new IllegalArgumentException("Переданы матрицы разных размеров");
    }
    Matrix sum = new Matrix(first.x, first.y);
    for (int i = 0; i < first.y; ++i) {
      for (int j = 0; j < first.x; ++j) {
        sum.matrix[i][j] = Complex.sum(first.matrix[i][j], second.matrix[i][j]);
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    try {
      Matrix matrix1 = Matrix.createEmptyMatrix();
      Matrix.inputMatrix(matrix1);
      matrix1.printMatrix();
      Matrix matrix2 = Matrix.createEmptyMatrix();
      Matrix.inputMatrix(matrix2);
      matrix2.printMatrix();
      (Matrix.sum(matrix1, matrix2)).printMatrix();
    } catch (IllegalArgumentException exception) {
      System.out.println(exception.getMessage());
    }
  }

}
