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
    for (int i = 0; i < matrix.x; ++i) {
      for (int j = 0; j < matrix.y; ++j) {
        double a = in.nextDouble();
        double b = in.nextDouble();
        matrix.matrix[i][j] = new Complex(a, b);
      }
    }
  }

  public void printMatrix() {
    for (int i = 0; i < x; ++i) {
      for (int j = 0; j < y; ++j) {
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
    for (int i = 0; i < first.x; ++i) {
      for (int j = 0; j < first.y; ++j) {
        sum.matrix[i][j] = Complex.sum(first.matrix[i][j], second.matrix[i][j]);
      }
    }
    return sum;
  }

  public static Matrix product(Matrix first, Matrix second) throws IllegalArgumentException {
    if (first.y != second.x) {
      throw new IllegalArgumentException("Перемножение матриц с данными размерами невозможно");
    }
    Matrix product = new Matrix(first.x, second.y);
    for (int i = 0; i < product.x; ++i) {
      for (int j = 0; j < product.y; ++j) {
        Complex res = new Complex();
        for (int k = 0; k < first.y; ++k) {
          res.addComplex(Complex.product(first.matrix[i][k], second.matrix[k][j]));
        }
        product.matrix[i][j] = res;
      }
    }
    return product;
  }

  public static void main(String[] args) {
    try {
      Matrix matrix1 = Matrix.createEmptyMatrix();
      Matrix.inputMatrix(matrix1);
      matrix1.printMatrix();
      Matrix matrix2 = Matrix.createEmptyMatrix();
      Matrix.inputMatrix(matrix2);
      matrix2.printMatrix();
      System.out.println("Результат:");
      (Matrix.product(matrix1, matrix2)).printMatrix();
    } catch (IllegalArgumentException exception) {
      System.out.println(exception.getMessage());
    }
  }

}
