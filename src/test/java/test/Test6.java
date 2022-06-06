package test;

import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * 复制 新文件的后缀和名字
 */
public class Test6 {
    public static void main(String[] args) {

        InputStream in = null;
        OutputStream out = null;

        try {
            File file = new File("D:/in.txt");
            String fileName = file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);//获取后缀

            String newFileName = UUID.randomUUID().toString().replace("-", "") + "." + suffix;//创建新文件的名字

            in = new FileInputStream(file);
            out = new FileOutputStream("D:/" + newFileName);

            int i = in.read();
            while (i != -1) {
                out.write(i);
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
    }
}
