package ru.hse.kurikhin;

import ru.hse.kurikhin.complex.Complex;
import ru.hse.kurikhin.input_output.IOHelper;
import ru.hse.kurikhin.matrix.Matrix;

public class EntryPoint {
  
  public static void main(String[] args) {
    Matrix matrix = IOHelper.inputMatrix();
    IOHelper.printMatrix(matrix);
    System.out.println("Transformed:");
    IOHelper.printMatrix(matrix.transform());
  }
}
