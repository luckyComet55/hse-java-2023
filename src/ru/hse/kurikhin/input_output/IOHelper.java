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
    double a = 0, b = 0;
    try(Scanner in = new Scanner(System.in)) {
      a = in.nextDouble();
      b = in.nextDouble();
    } catch (InputMismatchException exc) {
      System.out.println("Неверный формат ввода");
    }
    return new Complex(a, b);
  }
  
  public static Matrix inputMatrix() {
    System.out.println("Введите размеры матрицы:");
    Scanner in = new Scanner(System.in);
    int x = 0, y = 0;
    try {
      x = in.nextInt();
      y = in.nextInt();
      if (x <= 0 || y <= 0) {
        throw new IllegalArgumentException("Nope, below 0");
      }
    } catch (InputMismatchException | IllegalArgumentException exception) {
      System.out.println("Неверный формат ввода");
    }
    Matrix matrix = new Matrix(x, y);
    System.out.println("Введите элементы матрицы в формате комплексного числа");
    for (int i = 0; i < x; ++i) {
      for (int j = 0; j < y; ++j) {
        try {
          double a = in.nextDouble();
          double b = in.nextDouble();
          matrix.setElement(i + 1, j + 1, new Complex(a, b));
        } catch (InputMismatchException exception) {
          System.out.println("Неверный формат ввода");
        }
      }
    }
    return matrix;
  }
  
  /**
   * Вывод комлексного числа в тригонометрической форме
   */
  public static void printComplexTrigonometrical(Complex complex) {
    double a = complex.getA(), b = complex.getB();
    if (a == 0 && b == 0) {
      System.out.print("0");
    }
    double modulo = Math.sqrt(Math.pow(a, 2.) + Math.pow(b, 2.));
    double cosine = a / modulo;
    double phi = Math.acos(cosine);
    double sine = b / modulo;
    char signSine = sine >= 0 ? '+' : '-';
    String out = String.format("%.3f(cos%.3f %c sin%.3fi)", modulo, phi, signSine, phi);
    System.out.print(out);
  }
  
  /**
   * Вывод комплексного числа в виде вектора
   */
  public static void printComplexVector(Complex complex) {
    System.out.print("(" + complex.getA() + "," + complex.getB() + ")");
  }
  
  /**
   * Вывод комплексного числа в виде a + bi
   */
  public static void printComplexRegular(Complex complex) {
    double a = complex.getA(), b = complex.getB();
    if (a == 0 && b == 0) {
      System.out.print("0");
      return;
    }
    if (b == 0) {
      System.out.print(a);
      return;
    }
    if (a == 0) {
      System.out.print(b + "i");
      return;
    }
    char signB = b > 0 ? '+' : '-';
    String out = String.format("%.3f%c%.3fi", a, signB, Math.abs(b));
    System.out.print(out);
  }
  
  /**
   * Выводит матрицу
   */
  public static void printMatrix(Matrix matrix) {
    for (int i = 0; i < matrix.getX(); ++i) {
      for (int j = 0; j < matrix.getY(); ++j) {
        printComplexRegular(matrix.getElement(i + 1, j + 1));
        System.out.print(' ');
      }
      System.out.println();
    }
  }
}
