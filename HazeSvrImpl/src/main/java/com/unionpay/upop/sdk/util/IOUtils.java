package com.unionpay.upop.sdk.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import com.unionpay.upop.sdk.SdkConf;

public class IOUtils {
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    public static final char FILE_PATH_SEPARATOR = '/';

    public static String readFully(InputStream in) throws IOException {
        return readFully(in, SdkConf.DEFAULT_ENCODING);
    }

    public static String readFully(InputStream in, String encoding) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = in.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, length, encoding));
            }
            return sb.toString();
        } finally {
            close(in);
        }
    }

    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // ignore
                System.out.println("Failed to close " + c.getClass().getSimpleName());
                e.printStackTrace();
            }
        }
    }
}
