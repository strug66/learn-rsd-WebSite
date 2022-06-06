package test;

import java.io.*;

/**
 * 数据流 输入流
 */
public class Test9_out {
    public static void main(String[] args) {
        InputStream in = null;
        DataInputStream dis = null;

        try {
            in = new FileInputStream("D:/aaa.data");
            dis = new DataInputStream(in);

            byte b = dis.readByte();
            System.out.println(b);

            String s = dis.readUTF();
            System.out.println(s);

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
            if(dis!=null){
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
