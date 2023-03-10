package ru.hse.kurikhin.complex;

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
   */
  public void addComplex(Complex other) {
    this.a += other.a;
    this.b += other.b;
  }

  /**
   * Сумма двух комплексных чисел класса Complex
   * @param first комплексное число класса Complex
   * @param second комплексное число класса Complex
   * @return комплексное число результат сложения
   */
  public static Complex sum(Complex first, Complex second) {
    return new Complex(first.a + second.a, first.b + second.b);
  }

  /**
   * Произведение двух комплексных чисел
   * @param first комплексное число класса Complex
   * @param second комплексное число класса Complex
   * @return комплексное число результат произведения
   */
  public static Complex product(Complex first, Complex second) {
    return new Complex(
        first.a * second.a - first.b * second.b,
        first.a * second.b + first.b * second.a
    );
  }

  /**
   * Умножение себя на переданное комплексное число класса Complex
   * @param other комплексное число класса Complex
   */
  public void multiplyComplex(Complex other) {
    this.a = this.a * other.a - this.b * other.b;
    this.b = this.a * other.b + this.b * other.a;
  }

  /**
   * Вывод комплексного числа в виде a + bi
   */
  public void printRegular() {
    if (a == 0 && b == 0) {
      System.out.println("0");
    }
    char signB = b >= 0 ? '+' : '-';
    String out = String.format("%.3f %c %.3fi", a, signB, Math.abs(b));
    System.out.println(out);
  }

  /**
   * Вывод комплексного числа в виде вектора
   */
  public void printVector() {
    System.out.print("(" + a + "," + b + ")");
  }

  /**
   * Вывод комлексного числа в тригонометрической форме
   */
  public void printTrigonometrical() {
    if (a == 0 && b == 0) {
      System.out.println("0");
    }
    double modulo = Math.sqrt(Math.pow(a, 2.) + Math.pow(b, 2.));
    double cosine = a / modulo;
    double phi = Math.acos(cosine);
    double sine = b / modulo;
    char signSine = sine >= 0 ? '+' : '-';
    String out = String.format("%.3f(cos%.3f %c sin%.3fi)", modulo, phi, signSine, phi);
    System.out.println(out);
  }

  /**
   * Ввод комплексного числа
   * @return комплексное число класса Complex
   */
  public static Complex inputComplex() {
    System.out.println("Введите параметры комплексного числа:");
    Scanner in = new Scanner(System.in);
    double a = in.nextDouble();
    double b = in.nextDouble();
    return new Complex(a, b);
  }

  public static void main(String[] args) {
    Complex c = Complex.inputComplex();
    c.printTrigonometrical();
  }
}
