package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;

/**
 * Iterator class. Returns the numbers as BigInteger objects.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {

  private static final BigInteger MAX = new BigInteger("9".repeat(100));
  private static final BigInteger SEED = BigInteger.ONE;
  private final BigInteger endValue;
  //number at which the sequence must begin
  private BigInteger curr;
  private BigInteger next;
  private BigInteger prev;


  /**
   * Declares seed and end values.
   *
   * @param startSeed seed value
   * @param endValue  end number
   * @throws IllegalArgumentException when seed is not positive, or is not less than the end or has
   *                                  any zeroes in it
   */
  public LookAndSayIterator(BigInteger startSeed, BigInteger endValue)
      throws IllegalArgumentException {
    if ((startSeed.signum() <= 0) || (startSeed).compareTo(endValue) >= 0 || String.valueOf(
        startSeed).contains("0")) {
      throw new IllegalArgumentException("Incorrect seed");
    }
    this.curr = startSeed;
    this.endValue = endValue;
    this.next = getNext(this.curr);
    this.prev = getPrevious(this.curr);
    //The iterator should stop incrementing once a number greater than end is reached.
  }

  /**
   * Constructor takes in starting seed as argument.
   *
   * @param startSeed number at which sequence must begin
   * @throws IllegalArgumentException when seed is not positive, or is not less than the end or has
   *                                  any zeroes in it
   */
  public LookAndSayIterator(BigInteger startSeed) throws IllegalArgumentException {
    this(startSeed, MAX);
  }


  /**
   * Default constructor.
   */
  public LookAndSayIterator() {
    this(SEED, MAX);
  }

  /**
   * previous value.
   *
   * @return T
   * @throws NoSuchElementException if previous sequence does not exist.
   */
  @Override
  public BigInteger prev() throws NoSuchElementException {
    //return the current number in the sequence and revert to the previous number in the sequence.
    if (this.curr == null) {
      throw new NoSuchElementException("iteration has no more elements");
    }
    BigInteger retValue = this.curr;
    this.next = this.curr;
    this.curr = this.prev;
    if (this.hasPrevious()) {
      this.prev = this.getPrevious(this.curr);
    } else {
      this.prev = null;
    }
    return retValue;
  }

  private BigInteger getPrevious(BigInteger seq) {

    char[] currSequence = seq.toString().toCharArray();
    if (currSequence.length % 2 == 1) {
      return seq;
    }
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < currSequence.length; i += 2) {
      int count = currSequence[i] - '0';
      char currentDigit = currSequence[i + 1];
      builder.append(String.valueOf(currentDigit).repeat(Math.max(0, count)));
    }

    return new BigInteger(builder.toString());
  }

  /**
   * Checks whether there is a previous value.
   *
   * @return true when previous value exists
   */
  @Override
  public boolean hasPrevious() {
    if (this.curr == null) {
      return false;
    }
    return this.curr.toString().length() % 2 == 0
        && this.curr.compareTo(endValue) <= 0;
  }

  /**
   * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true}
   * if {@link #next} would return an element rather than throwing an exception.)
   *
   * @return {@code true} if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    if (this.curr == null) {
      return false;
    }
    return this.curr.compareTo(this.endValue) < 0;
  }

  /**
   * Returns the next element in the iteration.
   *
   * @return the next element in the iteration
   * @throws NoSuchElementException if the iteration has no more elements
   */
  @Override
  public BigInteger next() throws NoSuchElementException {
    //return the current number in the sequence
    if (this.curr == null) {
      throw new NoSuchElementException("iteration has no more elements");
    }
    BigInteger retVal = this.curr;
    this.prev = this.curr;
    this.curr = this.next;
    if (this.hasNext()) {
      this.next = this.getNext(this.curr);
    } else {
      this.next = null;
    }
    return retVal;
  }

  private BigInteger getNext(BigInteger seed) {
    if (seed == null || seed.toString().isEmpty()) {
      throw new IllegalArgumentException("Seed is null or empty");
    }
    char[] seedChar = seed.toString().toCharArray();
    int count = 1;
    StringBuilder stringBuilder = new StringBuilder();
    int prevSeed = Character.getNumericValue(seedChar[0]);
    for (int i = 1; i < seedChar.length; i++) {
      if (Character.getNumericValue(seedChar[i]) == prevSeed) {
        count++;
      } else {
        stringBuilder.append(count).append(prevSeed);
        count = 1;
      }
      prevSeed = Character.getNumericValue(seedChar[i]);
    }
    stringBuilder.append(count).append(prevSeed);
    return new BigInteger(stringBuilder.toString());
  }

  @Override
  public String toString() {
    return "LookAndSayIterator"
        + " startSeed=" + curr
        + ", endValue=" + endValue;
  }

}
