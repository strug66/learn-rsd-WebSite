package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 缓冲输出流
 */
public class Test8_out {
    public static void main(String[] args) {
        Writer writer = null;
        BufferedWriter bw = null;

        try {
            writer = new FileWriter("D:/88.txt");
            bw = new BufferedWriter(writer);

            bw.write("我是中国人");
            bw.newLine(); //换行
            bw.write("我是好人");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
