package test;

import com.rsd.bean.SysFunction;

import javax.jws.soap.SOAPBinding;
import java.io.*;

public class Test10_out {
    public static void main(String[] args) {
        InputStream in = null;
        ObjectInputStream oin = null;

        try {
            in = new FileInputStream("D:/functionObj.data");
            oin = new ObjectInputStream(in);

            Object o = oin.readObject(); //读对象
//            System.out.println(o);
            SysFunction sysFunction = (SysFunction) o;
            System.out.println(sysFunction.getId() + "\t" + sysFunction.getName());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (oin != null) {
                try {
                    oin.close();
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
        }

    }
}
