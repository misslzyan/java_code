import java.util.stream.IntStream;

public class Test {

  @org.junit.Test
  public void test1() {
    IntStream.range(1, 4).forEach(i -> System.out.println(i));
  }
}
