package org.example.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckService {
      private static final String CHECK_FOLDER = ApplicationProperties.checkFolder();

    public static void saveCheck(String checkContent) {
        String fileName = "check_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".txt";
        try {
            File folder = new File(CHECK_FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File checkFile = new File(folder, fileName);
            FileWriter writer = new FileWriter(checkFile);
            writer.write(checkContent);
            writer.close();

            System.out.println("Чек успешно сохранен.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении чека: " + e.getMessage());
        }
    }
}

