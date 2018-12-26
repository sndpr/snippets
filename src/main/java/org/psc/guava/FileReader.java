package org.psc.guava;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileReader {

    public String readFile(String filename) throws IOException {
        return String.join("\n", Files.readLines(new File(filename), StandardCharsets.UTF_8))
                .concat("2312312213123");
    }
}
