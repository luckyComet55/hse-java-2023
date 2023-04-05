package ru.hse.kurikhin.input_output;

import ru.hse.kurikhin.complex.Complex;
import ru.hse.kurikhin.matrix.Matrix;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOHelper {
  
  /**
   * Ввод комплексного числа
   * @return комплексное число класса Complex
   */
  public static Complex inputComplex() {
    System.out.println("Введите параметры комплексного числа:");
    boolean isCorrect = false;
    double a = 0, b = 0;
    Scanner in = new Scanner(System.in);
    while (!isCorrect) {
      try {
        a = in.nextDouble();
        b = in.nextDouble();
        isCorrect = true;
      } catch (InputMismatchException exc) {
        System.out.println("Неверный формат ввода, попробуйте ещё раз");
      }
    }
    return new Complex(a, b);
  }
  
  public static Matrix inputMatrix() {
    System.out.println("Введите размеры матрицы:");
    boolean isCorrect = false;
    Scanner in = new Scanner(System.in);
    int x = 0, y = 0;
    while (!isCorrect) {
      try {
        x = in.nextInt();
        y = in.nextInt();
        if (x <= 0 || y <= 0) {
          throw new IllegalArgumentException("Nope, below 0");
        }
        isCorrect = true;
      } catch (InputMismatchException | IllegalArgumentException exception) {
        System.out.println("Неверный формат ввода, попробуйте ещё раз");
      }
    }
    Matrix matrix = new Matrix(x, y);
    isCorrect = false;
    System.out.println("Введите элементы матрицы в формате комплексного числа");
    for (int i = 0; i < x; ++i) {
      for (int j = 0; j < y; ++j) {
        while (!isCorrect){
          try {
            double a = in.nextDouble();
            double b = in.nextDouble();
            matrix.setElement(i, j, new Complex(a, b));
            isCorrect = true;
          } catch (InputMismatchException exception) {
            System.out.println("Неверный формат ввода, попробуйте ещё раз");
          }
        }
        isCorrect = false;
      }
    }
    return matrix;
  }
}
