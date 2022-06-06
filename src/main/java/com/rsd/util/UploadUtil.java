package com.rsd.util;

import java.io.*;
import java.util.UUID;

public class UploadUtil {

    public static final String baseDir = "/uploadFiles/";

    public static String getNewFileName(String fileName) {
        String newFileName = "";
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        newFileName = UUID.randomUUID().toString().replace("-", "") + "." + suffix;
        return newFileName;
    }

    public static boolean upload(InputStream in, String path) throws FileNotFoundException {
        boolean result = false;
        OutputStream out = null;

        try {
            out = new FileOutputStream(path);
            int b = in.read();
            while (b != -1) {
                out.write(b);
                b = in.read();
            }
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
        return result;
    }
}
