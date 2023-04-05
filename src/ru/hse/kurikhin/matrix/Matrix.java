package ru.hse.kurikhin.matrix;

import ru.hse.kurikhin.complex.Complex;
import java.util.Scanner;

public class Matrix {
  private int x, y;
  private Complex[][] matrix;
  
  /**
   * Конструирует дефолтную матрицу размером 1x1
   */
  public Matrix() {
    this(1, 1);
  }
  
  /**
   * Конструирует матрицу с переданным размером
   * @param x количество строк
   * @param y количество столбцов
   * @throws IllegalArgumentException выбрасывает, если одна их переменных не положительна
   */
  public Matrix(int x, int y) throws IllegalArgumentException {
    if (x <= 0 || y <= 0) {
      throw new IllegalArgumentException("Размерность матрицы не соответствует требованиям");
    }
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
   * Добавляет к себе переданную в аргументе матрицу
   * @param other матрица класса Matrix
   * @return возвращает себя после прибавления
   * @throws IllegalArgumentException выбрасывается, если матрица объекта other имеет другие размеры
   */
  public Matrix add(Matrix other) throws IllegalArgumentException {
    if (x != other.x || y != other.y) {
      throw new IllegalArgumentException("Переданы матрицы разных размеров");
    }
    
    for (int i = 0; i < x; ++i) {
      for (int j = 0; j < y; ++j) {
        matrix[i][j].add(other.matrix[i][j]);
      }
    }
    return this;
  }
  
  /**
   * Производит умножение хранимой матрицы не переданную
   * @param other матрица класса Matrix
   * @return возвращает себя после перемножения
   * @throws IllegalArgumentException выбрасывает, если перемножение невозможно
   */
  public Matrix multiply (Matrix other) throws IllegalArgumentException {
    if (y != other.x) {
      throw new IllegalArgumentException("Перемножение матриц с данными размерами невозможно");
    }
    Matrix product = new Matrix(x, other.y);
  
    for (int i = 0; i < product.x; ++i) {
      for (int j = 0; j < product.y; ++j) {
        Complex res = new Complex();
        for (int k = 0; k < y; ++k) {
          res.add(matrix[i][k].add(other.matrix[k][j]));
        }
        product.matrix[i][j] = res;
      }
    }
    matrix = product.matrix;
    x = product.x;
    y = product.y;
    return this;
  }

  /**
   * Транспониурет матрицу
   * @return возвращает себя после транспонирования
   */
  public Matrix transform() {
    Matrix transformed = new Matrix(y, x);
    for (int i = 0; i < y; ++i) {
      for (int j = 0; j < x; ++j) {
        transformed.matrix[i][j] = matrix[j][i];
      }
    }
    matrix = transformed.matrix;
    x = transformed.x;
    y = transformed.y;
    return this;
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
