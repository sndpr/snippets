package org.psc.strings;

public class StringFormatting {

    public static void main(String[] args) {
        var init = "test";
        var extendedWidth = String.format("%50s", init);
        System.out.println(extendedWidth);
    }
}
