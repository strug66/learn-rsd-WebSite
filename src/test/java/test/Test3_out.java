package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 字节流  输出流
 */
public class Test3_out {
    public static void main(String[] args) {
        OutputStream output = null;
        try {
            output = new FileOutputStream("D:/data.txt");
            output.write(49); //1
            output.write(50);
            output.write(51);
            output.write(97);
            output.write(98);
            output.write(99);
            output.write(20013); //字节流-不能正确输出中文

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        String s1 = "a";  //97
//        System.out.println(s1.getBytes()[0]);
//
//        String s2 = "中";  //-28   -72 -83
//        byte[] bytes = s2.getBytes();
//        for (byte b : bytes) {
//            System.out.println(b);
//        }

//        String s2 = "中";
//        System.out.println(s2.hashCode());  //20013

    }
}
