package test;

import java.io.*;

/**
 * 数据流 输出流
 */
public class Test9 {
    public static void main(String[] args) {
        OutputStream out = null;
        DataOutputStream dos = null;

        try {
            out = new FileOutputStream("D:/aaa.data");
            dos = new DataOutputStream(out);

            dos.writeByte(100);
            dos.writeUTF("我爱北京");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(dos!=null){
                try {
                    dos.close();
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
