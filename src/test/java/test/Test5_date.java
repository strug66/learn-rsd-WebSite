package test;

import java.io.*;
import java.util.Date;

/**
 * 复制文件
 * 1440KB  [时间 9523]
 */
public class Test5_date {

    public static void main(String[] args) {
        Date date1 = new Date();

        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream("D:/1.pdf");
            out = new FileOutputStream("C:/1.pdf");

            int i = in.read();
            while (i != -1) {
                out.write(i); // 输出
                i = in.read();
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
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Date date2 = new Date();
        long l = date2.getTime() - date1.getTime();
        System.out.println(l);
    }
}
