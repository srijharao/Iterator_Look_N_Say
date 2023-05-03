package lookandsay;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * test class for look and say iterator.
 */
public class LookAndSayIteratorTest {

  @Test
  public void testCustomValidSeedValidEnd() {
    LookAndSayIterator lookAndSayIterator = new LookAndSayIterator(BigInteger.ONE,
        new BigInteger("12"));
    assertEquals("LookAndSayIterator startSeed=1, endValue=12", lookAndSayIterator.toString());

  }

  @Test
  public void testReverseDefaultSeed() {
    LookAndSayIterator lookAndSayIterator = new LookAndSayIterator(BigInteger.ONE);
    assertEquals(
        "LookAndSayIterator startSeed=1, "
            + "endValue=999999999999999999999999999999999999999999999999"
            + "9999999999999999999999999999999999999999999999999999",
        lookAndSayIterator.toString());
  }

  @Test
  public void testDefaultSeed() {
    LookAndSayIterator lookAndSayIterator = new LookAndSayIterator();
    assertEquals(
        "LookAndSayIterator startSeed=1, "
            + "endValue=999999999999999999999999999999999999999999999999"
            + "9999999999999999999999999999999999999999999999999999",
        lookAndSayIterator.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOneInputConstructorWhenSeedNegative() {
    new LookAndSayIterator(BigInteger.valueOf(-2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOneInputConstructorWhenSeedZero() {
    new LookAndSayIterator(BigInteger.valueOf(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOneInputConstructorWhenSeedContainsZero() {
    new LookAndSayIterator(BigInteger.valueOf(404));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOneInputConstructorWhenSeedBiggerThanEndVal() {
    new LookAndSayIterator(new BigInteger("9".repeat(101)));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testTwoInputConstructorWhenSeedNegative() {
    new LookAndSayIterator(BigInteger.valueOf(-5), BigInteger.valueOf(999));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTwoInputConstructorWhenSeedZero() {
    new LookAndSayIterator(BigInteger.valueOf(0), BigInteger.valueOf(999));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTwoInputConstructorWhenSeedHasZero() {
    new LookAndSayIterator(BigInteger.valueOf(0), BigInteger.valueOf(999));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTwoInputConstructorWhenSeedGreaterThanEndVal() {
    new LookAndSayIterator(BigInteger.valueOf(999), BigInteger.valueOf(998));
  }

  @Test
  public void testTwoInputConstructor() {
    String expected = "LookAndSayIterator startSeed=11, endValue=999";
    assertEquals(expected,
        new LookAndSayIterator(BigInteger.valueOf(11), BigInteger.valueOf(999)).toString());
  }

  @Test
  public void testOneInputConstructor() {

    String expected = "LookAndSayIterator startSeed=11, endValue=" + "9".repeat(100);
    assertEquals(expected,
        new LookAndSayIterator(BigInteger.valueOf(11)).toString());
  }

  @Test
  public void testDefaultConstructor() {

    String expected = "LookAndSayIterator startSeed=1, endValue=" + "9".repeat(100);
    assertEquals(expected,
        new LookAndSayIterator().toString());
  }

  @Test
  public void testNextUsingDefaultConstructor() {
    LookAndSayIterator seq = new LookAndSayIterator();
    assertEquals("1", seq.next().toString());
    assertEquals("11", seq.next().toString());
    assertEquals("21", seq.next().toString());
    assertEquals("1211", seq.next().toString());
    assertEquals("111221", seq.next().toString());
    assertEquals("312211", seq.next().toString());
    assertEquals("13112221", seq.next().toString());
    assertEquals("1113213211", seq.next().toString());
    assertEquals("31131211131221", seq.next().toString());
    assertEquals("13211311123113112211", seq.next().toString());
    assertEquals("11131221133112132113212221", seq.next().toString());
    assertEquals("3113112221232112111312211312113211", seq.next().toString());
    assertEquals("1321132132111213122112311311222113111221131221",
        seq.next().toString());
  }

  @Test
  public void testNextUsingOneInputConstructor() {
    LookAndSayIterator seq = new LookAndSayIterator(new BigInteger("121"),
        new BigInteger("111411151116"));
    assertEquals("121", seq.next().toString());
    assertEquals("111211", seq.next().toString());
    assertEquals("311221", seq.next().toString());

    LookAndSayIterator seq3 = new LookAndSayIterator(new BigInteger("1234567899"), new BigInteger(
        "13211231133114311531163117311811121119"));
    assertEquals("1234567899", seq3.next().toString());
    assertEquals("111213141516171829", seq3.next().toString());
    assertEquals("31121113111411151116111711181219", seq3.next().toString());
    assertEquals("13211231133114311531163117311811121119", seq3.next().toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testWhenNextInSequenceIsInvalid() {
    LookAndSayIterator seq3 = new LookAndSayIterator(new BigInteger("1234567899"), new BigInteger(
        "13211231133114311531163117311811121119"));
    for (int index = 0; index < 5; index++) {
      seq3.next();
    }
    assertEquals("LookAndSayIterator "
        + "startSeed=13211231133114311531163117311811121119, "
        + "endValue=13211231133114311531163117311811121119", seq3.toString());
    seq3.next();
  }

  @Test
  public void testNextGreaterThanMaxEndVal() {
    LookAndSayIterator testSeq = new LookAndSayIterator(new BigInteger("121".repeat(10)));
    testSeq.next();
    assertEquals("LookAndSayIterator "
            + "startSeed=111221122112211221122112211221122112211211, "
            + "endValue=99999999999999999999999999999999999999999999999"
            + "99999999999999999999999999999999999999999999999999999",
        testSeq.toString());
  }

  @Test
  public void testHasNext() {
    LookAndSayIterator seq = new LookAndSayIterator(new BigInteger("121"),
        new BigInteger("111411151116"));
    assertTrue(seq.hasNext());
    assertEquals("121", seq.next().toString());
    assertTrue(seq.hasNext());
    assertEquals("111211", seq.next().toString());
    assertEquals("311221", seq.next().toString());
    assertTrue("Next sequence not available", seq.hasNext());

    assertTrue(seq.hasNext());
    assertTrue(seq.hasNext());
  }

  @Test
  public void testPrev() {
    LookAndSayIterator seq = new LookAndSayIterator();
    assertEquals("1", seq.next().toString());
    assertEquals("11", seq.next().toString());
    assertEquals("21", seq.next().toString());
    assertEquals("1211", seq.next().toString());
    assertEquals("111221", seq.next().toString());
    assertEquals("312211", seq.prev().toString());
    assertEquals("111221", seq.prev().toString());
    assertEquals("1211", seq.prev().toString());
    assertEquals("21", seq.prev().toString());
    assertEquals("11", seq.prev().toString());

    LookAndSayIterator seq1 = new LookAndSayIterator(new BigInteger("121"),
        new BigInteger("111411151116"));
    assertEquals("121", seq1.next().toString());
    assertEquals("111211", seq1.next().toString());
    assertEquals("311221", seq1.prev().toString());
    assertEquals("111211", seq1.prev().toString());

    LookAndSayIterator seq3 = new LookAndSayIterator(new BigInteger("1234567899"), new BigInteger(
        "13211231133114311531163117311811121119"));
    assertEquals("1234567899", seq3.next().toString());
    assertEquals("111213141516171829", seq3.next().toString());
    assertEquals("31121113111411151116111711181219", seq3.next().toString());
    assertEquals("13211231133114311531163117311811121119", seq3.prev().toString());
    assertEquals("31121113111411151116111711181219", seq3.prev().toString());
    assertEquals("111213141516171829", seq3.prev().toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testWhenPreviousInvalid() {
    LookAndSayIterator seq = new LookAndSayIterator(new BigInteger("121"),
        new BigInteger("111411151116"));
    seq.next();
    seq.next();
    seq.next();

    assertEquals("13212211", seq.prev().toString());
    assertEquals("311221", seq.prev().toString());
    seq.prev();
    seq.prev();
    seq.prev();
  }


  @Test
  public void testHasPrevious() {
    LookAndSayIterator seq = new LookAndSayIterator(new BigInteger("456"),
        new BigInteger("111411151116"));
    seq.next();
    seq.next();
    assertEquals("LookAndSayIterator startSeed=111411151116, endValue=111411151116",
        seq.toString());
    assertTrue(seq.hasPrevious());
    assertEquals("111411151116", seq.prev().toString());
    assertTrue(seq.hasPrevious());
    assertEquals("141516", seq.prev().toString());
    assertFalse(seq.hasPrevious());
  }

  @Test
  public void testPreviousCausesDifferentSequence() {
    LookAndSayIterator newSequence = new LookAndSayIterator(BigInteger.valueOf(123434));
    assertEquals("123434", newSequence.next().toString());
    assertEquals("111213141314", newSequence.next().toString());
    assertEquals("31121113111411131114", newSequence.prev().toString());
    assertEquals("111213141314", newSequence.prev().toString());
    assertEquals("123434", newSequence.prev().toString());

    LookAndSayIterator newSequence2 = new LookAndSayIterator(new BigInteger("1".repeat(100)));
    assertEquals("1".repeat(100), newSequence2.next().toString());
    assertEquals("1001", newSequence2.next().toString());
    assertEquals("112011", newSequence2.next().toString());
    assertEquals("21121021", newSequence2.prev().toString());
    assertEquals("112011", newSequence2.prev().toString());
    assertEquals("1001", newSequence2.prev().toString());
  }


}