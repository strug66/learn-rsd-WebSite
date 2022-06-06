package test;

import java.io.*;

/**
 * 复制文件-字符流
 */
public class Test5_out {
    public static void main(String[] args) {
        Reader reader = null;
        Writer writer = null;

        try {
            reader = new FileReader("D:/in.txt"); //不能复制图片 D:/in.txt
            writer = new FileWriter("C:/in.txt");

            int i = reader.read();
            while (i != -1) {
                writer.write(i); //输出
                i = reader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
