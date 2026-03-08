package de.hhn.it.pp.components.librarymanagementsystem.helper;

import de.hhn.it.pp.components.librarymanagementsystem.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.librarymanagementsystem.exceptions.NullPointerException;

/**
 * Helper class to check simple things.
 */
public class CheckingHelper {

  /**
   * Checks if the string parameter has some readable string within.
   *
   * @param string          string to be checked
   * @param nameInException name of the parameter to be shown in the exception
   * @throws IllegalParameterException if the string to be checked is a null reference or
   *                                   contains no or only whitespace chars
   */
  public static void assertThatIsReadableString(String string, String nameInException) throws
          IllegalParameterException, NullPointerException {
    if (string == null) {
      throw new NullPointerException("String for " + nameInException + " is null reference.");
    }
    if (string.trim().length() == 0) {
      throw new IllegalParameterException("String  " + nameInException + " consists only of "
              + "whitespace.");
    }
  }

  /**
   * Checks if the given object reference is a null reference.
   *
   * @param object          reference to be checked
   * @param nameInException name of the object within an exception
   * @throws NullPointerException if the object is a null reference
   */
  public static void assertThatIsNotNull(Object object, String nameInException) throws
          NullPointerException {
    if (object == null) {
      throw new NullPointerException("Reference for " + nameInException + " is null "
              + "reference.");
    }
  }

  /**
   * Checks if the integer parameter has some readable/logical integer within.
   *
   * @param integer                    integer to be checked
   * @param integerInException         name of the integer to be checked
   * @throws NullPointerException      if the integer is a null reference
   * @throws IllegalParameterException if the integer is a zero
   */
  public static void assertThatIsReadableInteger(Integer integer, String integerInException) throws IllegalParameterException, NullPointerException {
    if (integer == null) {
      throw new NullPointerException("String for " + integerInException + " is null reference.");
    }

    if (integer == 0) {
      throw new IllegalParameterException("Integer  " + integerInException + " can't be 0");
    }
  }
}
