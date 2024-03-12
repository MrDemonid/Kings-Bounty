package com.mygdx.game;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Console {
    static PrintStream printStream;

    static {
        try {
            printStream = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static  void println(String s)
    {
        printStream.println(s);
    }
    public static void print(String s)
    {
        printStream.print(s);
    }
    
}
