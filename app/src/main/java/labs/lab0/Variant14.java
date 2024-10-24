package labs.lab0;

import java.util.Arrays;

public class Variant14 {
  /**
    * 
    * @param number - тризначне число
    * @return число після переміщення останньої цифри в початок
    */

  public int rotateDigit(int number) {
    assert (number >= 100 && number <= 999) : "Число повинно бути тризначним";
    int lastDigit = number % 10;
    int remainingDigits = number / 10;
    return lastDigit * 100 + remainingDigits;
  }


  /**
     * Перевіряє, чи рівно одне з трьох чисел є додатнім.
     * 
     * @param A перше число
     * @param B друге число
     * @param C третє число
     * @return true, якщо рівно одне число додатнє; інакше false
     */
    public boolean isExactlyOnePositive(int A, int B, int C) {
      int positiveCount = 0;
      if (A > 0) positiveCount++;
      if (B > 0) positiveCount++;
      if (C > 0) positiveCount++;
      return positiveCount == 1;
  }


  /**
     * Знаходить найменше і найбільше число серед трьох даних чисел, використовуючи умовний оператор.
     * 
     * @param A перше число
     * @param B друге число
     * @param C третє число
     * @return масив, де перший елемент — найменше число, а другий — найбільше число
     */
    public int[] findMinAndMax(int A, int B, int C) {
      int min, max;

      if (A <= B && A <= C) {
          min = A;
      } else if (B <= A && B <= C) {
          min = B;
      } else {
          min = C;
      }

      if (A >= B && A >= C) {
          max = A;
      } else if (B >= A && B >= C) {
          max = B;
      } else {
          max = C;
      }

      return new int[]{min, max};
  }


  /**
     * Обчислює всі елементи рівностороннього трикутника.
     * 
     * @param elementNumber номер елемента (1 - сторона, 2 - радіус вписаного кола, 3 - радіус описаного кола, 4 - площа)
     * @param value значення елемента
     * @return масив, де [0] - сторона a, [1] - радіус вписаного кола R1, [2] - радіус описаного кола R2, [3] - площа S
     */
    public double[] calculateEquilateralTriangle(int elementNumber, double value) {
      double a, R1, R2, S;
      switch (elementNumber) {
          case 1: // сторона a
              a = value;
              R1 = (a * Math.sqrt(3)) / 6;
              R2 = 2 * R1;
              S = (a * a * Math.sqrt(3)) / 4;
              break;
          case 2: // радіус вписаного кола R1
              R1 = value;
              a = (6 * R1) / Math.sqrt(3);
              R2 = 2 * R1;
              S = (a * a * Math.sqrt(3)) / 4;
              break;
          case 3: // радіус описаного кола R2
              R2 = value;
              R1 = R2 / 2;
              a = (6 * R1) / Math.sqrt(3);
              S = (a * a * Math.sqrt(3)) / 4;
              break;
          case 4: // площа S
              S = value;
              a = Math.sqrt((4 * S) / Math.sqrt(3));
              R1 = (a * Math.sqrt(3)) / 6;
              R2 = (a * Math.sqrt(3)) / 3;
              break;
          default:
              throw new IllegalArgumentException("Номер елемента має бути від 1 до 4");
      }
      return new double[]{a, R1, R2, S};
  }


  /**
     * Обчислює і виводить квадрати всіх чисел від 1 до N, використовуючи послідовне додавання непарних чисел.
     *
     * @param N число, до якого обчислюються квадрати
     * @return масив квадратів чисел від 1 до N
     */
    public int[] calculateSquaresUpToN(int N) {
      assert N > 0 : "N має бути додатним числом";

      int[] squares = new int[N];
      int sum = 0;
      // int k = 0;

      for (int i = 0; i < N; i++) {
          sum += 2 * i + 1;  
          squares[i] = sum;
      }

      return squares;
  }


  /**
     * Знаходить найбільше ціле число K, для якого сума 1 + 1/2 + … + 1/K < A.
     *
     * @param A число, більше за 1
     * @return масив, де [0] - максимальне значення K, [1] - сума ряду
     */
    public double[] findMaxKForSumLessThanA(double A) {
      assert A > 1 : "A має бути більше за 1";

      double sum = 0.0;
      int K = 0;

      while (sum + 1.0 / (K + 1) < A) {
          K++;
          sum += 1.0 / K;
      }

      return new double[]{K, sum};
  }


  /**
     * Повертає новий масив, в якому спочатку розташовані елементи з парними номерами, а потім — з непарними.
     *
     * @param A вхідний масив розміру N
     * @return масив з елементами в заданому порядку
     */
    public int[] rearrangeArray(int[] A) {
      int[] result = new int[A.length];
      int evenIndex = 0;  
      int oddIndex = A.length / 2;  
  
      for (int i = 1; i < A.length; i += 2) { 
          result[evenIndex++] = A[i];
      }
  
      for (int i = 0; i < A.length; i += 2) { 
          result[oddIndex++] = A[i];
      }
  
      return result;
  }


  /**
     * Виводить елементи квадратної матриці A порядку M по куточках.
     *
     * @param A квадратна матриця розміру M x M
     * @return масив, що містить елементи матриці у порядку виведення по куточках
     */
    public int[] printCorners(int[][] A) {
      int M = A.length;
      int[] result = new int[M * M];
      int index = 0;

      for (int layer = 0; layer < (M + 1) / 2; layer++) {
          // Виведення елементів першого стовпця 
          for (int i = layer; i < M - layer; i++) {
              result[index++] = A[i][layer];
          }

          // Виведення елементів нижнього рядка (з останнього стовпця)
          for (int j = layer + 1; j < M - layer; j++) {
              result[index++] = A[M - layer - 1][j];
          }

          // Виведення елементів останнього стовпця (з нижнього рядка)
          for (int i = M - layer - 2; i >= layer; i--) {
              result[index++] = A[i][M - layer - 1];
          }

          // Виведення елементів верхнього рядка (з першого стовпця)
          for (int j = M - layer - 2; j > layer; j--) {
              result[index++] = A[layer][j];
          }
      }

      return result;
  }


  public static void main(String... strings) {
      System.out.println("START");

      System.out.println(new Variant14().rotateDigit(123));

      System.out.println(new Variant14().isExactlyOnePositive(3, -2, -5));

      System.out.println(Arrays.toString(new Variant14().findMinAndMax(1, 2, 3)));

      System.out.println(Arrays.toString(new Variant14().calculateEquilateralTriangle(1, 5)));

      System.out.println(Arrays.toString(new Variant14().calculateSquaresUpToN(5)));

      System.out.println(Arrays.toString(new Variant14().findMaxKForSumLessThanA(2.5)));

      System.out.println(Arrays.toString(new Variant14().rearrangeArray(new int[]{1, 2, 3, 4, 5, 6})));

      System.out.println(Arrays.toString(new Variant14().printCorners(new int[][]{{1, 2}, {3, 4}})));

      System.out.println("END");
  }
}
