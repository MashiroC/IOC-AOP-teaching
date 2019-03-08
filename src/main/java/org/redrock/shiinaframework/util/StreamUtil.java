package org.redrock.shiinaframework.util;

import java.io.*;

/**
 * @author: Shiina18
 * @date: 2019/3/8 10:43
 * @description: 流工具类
 */
public class StreamUtil {
    /**
     * 将数据写入输出流
     * @param out 输出流
     * @param text 要写入的文本
     */
    public static void writeStream(OutputStream out, String text) {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(out)
        );
        try {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从输入流读数据
     * @param in 输入流
     * @return 文本数据
     */
    public static String readStream(InputStream in) {

        String res = null;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        in
                )
        );

        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            res = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;

    }
}
