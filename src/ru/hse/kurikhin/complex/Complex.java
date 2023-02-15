package ru.hse.kurikhin.complex;

public class Complex {
  private double A, B;

  public Complex(double a, double b) {
    A = a;
    B = b;
  }

  public double getA() { return A; }
  public void setA(double a) { A = a; }
  public double getB() { return B; }
  public void setB(double b) { B = b; }

  public Complex sumComplex(Complex other) {
    return new Complex(this.A + other.A, this.B + other.B);
  }

  public Complex productComplex(Complex other) {
    return new Complex(
        this.A * other.A - this.B * other.B,
        this.A * other.B + this.B * other.A
    );
  }

  public void printRegular() {
    if (A == 0 && B == 0) {
      System.out.println("0");
    }
    char signB = B >= 0 ? '+' : '-';
    String out = String.format("%.3f %c %.3fi", A, signB, B);
    System.out.println(out);
  }

  public void printTrigonometrical() {
    if (A == 0 && B == 0) {
      System.out.println("0");
    }
    double modulo = Math.sqrt(Math.pow(A, 2.) + Math.pow(B, 2.));
    double cosine = A / modulo;
    double sine = B / modulo;
    char signSine = sine >= 0 ? '+' : '-';
    String out = String.format("%.3f(%.3f %c %.3fi)", modulo, cosine, signSine, sine);
    System.out.println(out);
  }

  public static void main(String[] args) {
    Complex a = new Complex(2,3);
    Complex b = new Complex(1, 7);
    a.printRegular();
    a.printTrigonometrical();
    b.printRegular();
    b.printTrigonometrical();
    (a.sumComplex(b)).printRegular();
    (a.productComplex(b)).printRegular();
  }
}
