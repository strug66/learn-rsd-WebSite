package test;

import java.io.*;

/**
 * 缓冲输入流
 */
public class Test8 {
    public static void main(String[] args) {
        Reader reader = null;
        BufferedReader br = null;

        try {
            reader = new FileReader("D:/in.txt");
            br = new BufferedReader(reader);

//            String s = br.readLine(); //按行读取
//            System.out.println(s);

            String s = br.readLine(); //按行读取
            while (s != null) {
                System.out.println(s);
                s = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
