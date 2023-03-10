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

  /**
   * @return возвращает пустую матрицу с заданным размером
   * @throws IllegalArgumentException выбрасывает ошибку, если размеры матрицы несоответствуют требованиям
   */
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

  /**
   * @param matrix получает на вход матрицу класса Matrix, которую надо заполнить элементами
   */
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

  /**
   * Выводит матрицу
   */
  public void printMatrix() {
    for (int i = 0; i < x; ++i) {
      for (int j = 0; j < y; ++j) {
        matrix[i][j].printVector();
        System.out.print(' ');
      }
      System.out.println();
    }
  }

  /**
   * Геттер для X
   * @return возвращает размерность матрицы по X
   */
  public int getX() { return x; }

  /**
   * Геттер для Y
   * @return возвращает размерность матрицы по Y
   */
  public int getY() { return y; }

  /**
   * Сумма двух матриц одинаковых размеров
   * @param first матрица класса Matrix
   * @param second матрица класса Matrix
   * @return матрица - результат суммы матриц
   * @throws IllegalArgumentException выбрасывается, если переданные матрицы разных размеров
   */
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

  /**
   * Перемножение матриц
   * @param first матрица класса Matrix
   * @param second матрица класса Matrix
   * @return возвращает матрицу результат произведения, размер соответсвует математическим правилам
   * @throws IllegalArgumentException выбрасывается, если перемножение невозможно
   */
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

  /**
   * Транспониурет матрицу, не изменяет исходную
   * @return матрица результат транспонирования
   */
  public Matrix transform() {
    Matrix transformed = new Matrix(y, x);
    for (int i = 0; i < y; ++i) {
      for (int j = 0; j < x; ++j) {
        transformed.matrix[i][j] = matrix[j][i];
      }
    }
    return transformed;
  }

  public static void main(String[] args) {
    try {
      Matrix matrix1 = Matrix.createEmptyMatrix();
      Matrix.inputMatrix(matrix1);
      matrix1.printMatrix();
      System.out.println("Результат:");
      (matrix1.transform()).printMatrix();
    } catch (IllegalArgumentException exception) {
      System.out.println(exception.getMessage());
    }
  }

}
