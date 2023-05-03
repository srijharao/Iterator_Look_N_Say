package lookandsay;

import java.util.Iterator;

/**
 * Iterator for look-and-say sequence.
 * @param <T> generic
 */
public interface RIterator<T> extends Iterator<T> {

  /**
   * previous value in iteration.
   * @return T previous element in iteration
   */
  T prev();

  /**
   * Checks whether there is a previous value.
   * @return true when previous value exists
   */
  boolean hasPrevious();

}
