import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.stream.IntStream;

public class Test {

  @org.junit.Test
  public void test1() {
    IntStream.range(1, 4).forEach(i -> System.out.println(i));
  }


  @org.junit.Test
  public void test2() throws URISyntaxException {
    URI uri = new URI("file:///Users/duanweidong/test/tt.sh");
    URI basic = uri.resolve("basic");
    System.out.println(basic.getPath());
    System.out.println(basic.toString());
  }
  @org.junit.Test
  public void test3() {
    Random r = new Random();
    for (int i = 0;i<1000;i++){
      System.out.println(r.nextInt(1000));
    }
  }
}
