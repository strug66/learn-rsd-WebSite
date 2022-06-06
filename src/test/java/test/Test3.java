package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 字节流 输入流
 */
public class Test3 {
    public static void main(String[] args) {

        InputStream in = null;

        try {
            in = new FileInputStream("D:/in.txt");
            int i = in.read();

            while (i != -1) {
                System.out.print((char) i);
                i = in.read();//取出当前对象 指针下移
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
