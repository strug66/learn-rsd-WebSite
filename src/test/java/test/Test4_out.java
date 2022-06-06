package test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

/**
 * 字符流 输出流
 */
public class Test4_out {
    public static void main(String[] args) {
        Writer writer = null;
        try {
            writer = new FileWriter("D:/data_r.txt");

            writer.write(49);
            writer.write(50);
            writer.write(51);
            writer.write(97);
            writer.write("b");
            writer.write(99);
            writer.write(20013);
            writer.write("我是中国人");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
