package test;

import java.io.*;

/**
 * 转换流
 */
public class Test7 {
    public static void main(String[] args) {
        InputStream in = null;
        InputStreamReader isr = null;
        OutputStream out = null;
        OutputStreamWriter osw = null;

        try {
            in = new FileInputStream("D:/in.txt");
            isr = new InputStreamReader(in);

            out = new FileOutputStream("D:/kk.txt");
            osw = new OutputStreamWriter(out);

            int ii = isr.read();
            while (ii != -1) {
                System.out.print((char) ii);
//                osw.flush();
                osw.write(ii);
                ii = isr.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
