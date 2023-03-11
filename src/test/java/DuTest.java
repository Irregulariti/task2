import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuTest {

    public String returnActual(String command) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream pStream = new PrintStream(stream);
        System.setOut(pStream);
        Parser.main(command.split(" "));
        System.out.flush();
        System.setOut(System.out);
        return stream.toString();
    }

    @Test
    void test1() throws Exception {
        assertEquals("src/test/resources - 3KB\r\n",returnActual("du -h src/test/resources"));
    }
    @Test
    void test2() throws Exception {
        assertEquals("Total sum - 2\r\n",returnActual("du -c src/test/resources/file1.txt src/test/resources/file2.txt src/test/resources/file3.txt"));
    }
    @Test
    void test3() throws Exception {
        assertEquals("src/test/resources/file1.txt - 1KB\r\n" +
                "src/test/resources/file2.txt - 899B\r\n" +
                "src/test/resources/file3.txt - 27B\r\n",returnActual("du -h -si src/test/resources/file1.txt src/test/resources/file2.txt src/test/resources/file3.txt"));
    }
    @Test
    void test4() throws Exception {
        assertEquals("Total sum - 10KB\r\n",returnActual("du -h -si -c src/test/resources src/test/resources src/test/resources"));
    }
}
