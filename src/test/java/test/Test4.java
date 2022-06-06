package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 字符流 输入流
 */
public class Test4 {
    public static void main(String[] args) {
        Reader reader = null;
        try {
            reader = new FileReader("D:/in.txt");
            int i = reader.read();
            while (i != -1) {
                System.out.print((char) i);
                i = reader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
