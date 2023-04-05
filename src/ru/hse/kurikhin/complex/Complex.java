package ru.hse.kurikhin.complex;

import ru.hse.kurikhin.input_output.IOHelper;

import java.util.Scanner;

public class Complex {
  private double a, b;

  public Complex() {
    this(0, 0);
  }

  public Complex(double a) {
    this(a, 0);
  }

  public Complex(double a, double b) {
    this.a = a;
    this.b = b;
  }

  public double getA() { return a; }
  public void setA(double a) { this.a = a; }
  public double getB() { return b; }
  public void setB(double b) { this.b = b; }

  /**
   * Прибавление к себе переданного комплексного числа
   * @param other комплексное число класса Complex
   * @return возвращает себя после прибавления
   */
  public Complex add(Complex other) {
    a += other.a;
    b += other.b;
    return this;
  }

  /**
   * Умножение себя на переданное комплексное число класса Complex
   * @param other комплексное число класса Complex
   * @return возвращает себя после перемножения
   */
  public Complex multiply(Complex other) {
    a = a * other.a - b * other.b;
    b = a * other.b + b * other.a;
    return this;
  }
}
