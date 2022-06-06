package test;

import java.io.File;
import java.io.IOException;

/**
 * File类
 */
public class Test2_file {
    public static void main(String[] args) {
        File file1 = new File("D:/a.jpg");
        boolean b = file1.exists();
        System.out.println("是否存在" + b);
        boolean b1 = file1.isFile();
        System.out.println("是否是文件" + b1);

        String parentName = file1.getParent();
        System.out.println(parentName);
        File parentFile = file1.getParentFile();
        System.out.println(parentFile);

        File file2 = new File("D:/aaa");
        boolean b2 = file2.isDirectory();
        System.out.println("是否是文件夹" + b2);

        File file3 = new File("D:/shuqi2");
//        boolean b3 = file2.isDirectory();
//        System.out.println("--是否文件夹"+b3);
        if (!file3.exists()) {
            file3.mkdirs();  //支持创建多层目录；file3.mkdir();//只能创建单层目录
        }

//        File file4 = new File("D:/ss");
//        try {
//            file4.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        file4.delete();

        File file5 = new File("D:/");
        String[] list = file5.list();
        for (String fileName : list) {
            File file = new File("D:/" + fileName);
            System.out.println((file.isDirectory() ? "[文件夹]" : "[文件]") + "\t" + file);
        }

//        File file6 = new File("D:/");
//        File[] files = file6.listFiles();
//        for (File file : files) {
//            System.out.println((file.isDirectory() ? "[文件夹]" : "[文件]") + "\t" + file.getAbsolutePath());
//        }


    }
}
