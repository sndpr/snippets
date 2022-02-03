package org.psc.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class FileTesting {

    public static void main(String[] args) throws IOException {
        File f1 = analyzeFile("\\src\\main\\resources\\test.txt");
        File f2 = analyzeFile("\\src\\main\\resources\\test2.txt");
        System.out.println(Files.readString(f1.toPath()));
        System.out.println(Files.readString(f2.toPath()));
    }

    private static File analyzeFile(String pathToFile) throws IOException {
        File file = new File(System.getProperty("user.dir") + pathToFile);
        var fileAttributeView = Files.getFileAttributeView(file.toPath(), BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = fileAttributeView.readAttributes();
        System.out.println(basicFileAttributes.creationTime());
        System.out.println(basicFileAttributes.size());
        return file;
    }
}
