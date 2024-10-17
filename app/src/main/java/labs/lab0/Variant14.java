package labs.lab0;

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

      // Знаходимо найменше число
      if (A <= B && A <= C) {
          min = A;
      } else if (B <= A && B <= C) {
          min = B;
      } else {
          min = C;
      }

      // Знаходимо найбільше число
      if (A >= B && A >= C) {
          max = A;
      } else if (B >= A && B >= C) {
          max = B;
      } else {
          max = C;
      }

      return new int[]{min, max};
  }

  public static void main(String... strings) {
      System.out.println("Start of zero lab");
      System.out.println("Done!!!");
  }
}
