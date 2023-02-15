package ru.hse.kurikhin.complex;

import java.util.Scanner;

public class Complex {
  private double a, b;

  public Complex() {
    this(0, 0);
  }

  public Complex(double a, double b) {
    this.a = a;
    this.b = b;
  }

  public double getA() { return a; }
  public void setA(double a) { this.a = a; }
  public double getB() { return b; }
  public void setB(double b) { this.b = b; }

  public Complex sumComplex(Complex other) {
    return new Complex(this.a + other.a, this.b + other.b);
  }

  public Complex productComplex(Complex other) {
    return new Complex(
        this.a * other.a - this.b * other.b,
        this.a * other.b + this.b * other.a
    );
  }

  public void printRegular() {
    if (a == 0 && b == 0) {
      System.out.println("0");
    }
    char signB = b >= 0 ? '+' : '-';
    String out = String.format("%.3f %c %.3fi", a, signB, Math.abs(b));
    System.out.println(out);
  }

  public void printTrigonometrical() {
    if (a == 0 && b == 0) {
      System.out.println("0");
    }
    double modulo = Math.sqrt(Math.pow(a, 2.) + Math.pow(b, 2.));
    double cosine = a / modulo;
    double sine = b / modulo;
    char signSine = sine >= 0 ? '+' : '-';
    String out = String.format("%.3f(%.3f %c %.3fi)", modulo, cosine, signSine, Math.abs(sine));
    System.out.println(out);
  }

  public static Complex inputComplex() {
    System.out.println("Введите параметры комплексного числа:");
    Scanner in = new Scanner(System.in);
    double a = in.nextDouble();
    double b = in.nextDouble();
    return new Complex(a, b);
  }

  public static void main(String[] args) {
    Complex c = Complex.inputComplex();
    c.printRegular();
  }
}
