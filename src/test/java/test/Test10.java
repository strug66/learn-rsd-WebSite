package test;

import com.rsd.bean.SysFunction;

import java.io.*;

/**
 * 对象专属流 输出流
 */
public class Test10 {
    public static void main(String[] args) {
        OutputStream out = null;
        ObjectOutputStream oos = null;

        SysFunction sysFunction = new SysFunction();
        sysFunction.setId(1);
        sysFunction.setName("AAA");

        try {
            out = new FileOutputStream("D:/functionObj.data");
            oos = new ObjectOutputStream(out);

            oos.writeObject(sysFunction);  //写对象
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (oos!=null){
                try {
                    oos.close();
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
