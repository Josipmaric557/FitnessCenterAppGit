package hr.java.FitnessCentar.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class ImageUtil {

    private static final String FOLDER = "assets/";

    public static String saveImage(File file) throws IOException {

        Files.createDirectories(Paths.get(FOLDER));

        String fileName = System.currentTimeMillis() + "_" + file.getName();

        Path target = Paths.get(FOLDER + fileName);

        Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);

        return target.toString();
    }

    public static void deleteImage(String path) {
        try {
            if (path != null) {
                Files.deleteIfExists(Paths.get(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}