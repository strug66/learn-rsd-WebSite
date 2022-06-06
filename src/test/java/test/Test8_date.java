package test;

import java.io.*;
import java.util.Date;

/**
 * 复制文件-缓冲流
 * 1440KB  [时间 312]
 */
public class Test8_date {
    public static void main(String[] args) {
        Date date1 = new Date();

        InputStream in = null;
        OutputStream out = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            in = new FileInputStream("D:/1.pdf");
            out = new FileOutputStream("C:/1.pdf");
            bis = new BufferedInputStream(in);
            bos = new BufferedOutputStream(out);

            int i = bis.read();
            while (i != -1) {
                bos.write(i);
                i = bis.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Date date2 = new Date();
        long l = date2.getTime() - date1.getTime();
        System.out.println(l);
    }
}
