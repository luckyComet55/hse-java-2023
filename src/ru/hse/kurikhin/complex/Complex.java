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

  public static void main(String[] args) {
    Complex c = IOHelper.inputComplex();
    c.printTrigonometrical();
  }
}
