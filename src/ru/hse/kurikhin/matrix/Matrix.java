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
   * Возвращает элемент матрицы на позиции (x, y). Нумерация по каждой из координат начинается с 1
   * @param x номер столбца
   * @param y номер строки
   * @return элемент на позиции (x, y) типа Complex
   * @throws IllegalArgumentException выбрасывает, если позиция за пределами матрицы
   */
  public Complex getElement (int x, int y) throws IllegalArgumentException {
    if (this.x < x || x <= 0 || this.y < y || y <= 0) {
      throw new IllegalArgumentException("Неверные координаты");
    }
    return matrix[x - 1][y - 1];
  }
  
  /**
   * Устанавливает комплексное число на позиции (x, y). Нумерация по каждой из координат начинается с 1
   * @param x номер столбца
   * @param y номер строки
   * @param toInsert комплескное число, которое надо вставить
   * @throws IllegalArgumentException выбрасывает, если позиция за пределами матрицы
   */
  public void setElement (int x, int y, Complex toInsert) throws IllegalArgumentException {
    if (this.x < x || x <= 0 || this.y < y || y <= 0) {
      throw new IllegalArgumentException("Неверные координаты");
    }
    matrix[x - 1][y - 1] = toInsert;
  }
  
  
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
}
