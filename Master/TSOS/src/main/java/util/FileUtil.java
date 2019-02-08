package util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static String fileToString(String path) {
        String result = "";
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            result = new String(encoded, Charset.defaultCharset());
        } catch (IOException ignored) {
        }
        return result;
    }

    public static String fileToString(File file) {
        String result = "";
        try {
            byte[] encoded = Files.readAllBytes(file.toPath());
            result = new String(encoded, Charset.defaultCharset());
        } catch (IOException ignored) {
        }
        return result;
    }
}