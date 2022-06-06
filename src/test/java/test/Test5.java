package test;

import java.io.*;

/**
 * 图片复制
 */
public class Test5 {
    public static void main(String[] args) {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream("D:/a.jpg");
            out = new FileOutputStream("C:/a.jpg");
//
//            int count = 0;
//            int i = in.read();
//            while (i != -1) {
//                i = in.read();
//                count++;
//            }
//            System.out.println(count);  //字节数

//            int length = in.available();
//            for (int i = 0; i < length; i++) {
//                int s = in.read();
//                out.write(s);  //输出
//            }

            int i = in.read();
            while (i != -1) {
                out.write(i); // 输出
                i = in.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
