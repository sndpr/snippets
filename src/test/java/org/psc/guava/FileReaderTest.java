package org.psc.guava;

import lombok.extern.slf4j.Slf4j;
import org.intellij.lang.annotations.Language;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class FileReaderTest {

    @Language("TEXT")
    private static final String EXPECTED = "abcdefg\nHello\ndog\ncat\n1 2 3 4;2312312213123";

    @Test
    public void testReadFile() throws IOException {
        var fileReader = new FileReader();
        var result = fileReader.readFile("src/test/resources/text1.txt");
        Assert.assertEquals(EXPECTED, result);
        log.info(result);
    }
}
