package org.redrock.shiinaframework.util;

import java.io.*;

public class StreamUtil {
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
