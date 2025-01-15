package org.example.app.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AppLogger implements AutoCloseable {
    private static AppLogger instance;
    private static final String LOG_FILE_PATH = "src/main/resources/app.log";
    private final PrintWriter fileWriter;

    private AppLogger() {
        try {
            fileWriter = new PrintWriter(new FileWriter(LOG_FILE_PATH, true), true);
        } catch (IOException e) {
            throw new RuntimeException("Initialization error", e);
        }
    }

    public static synchronized AppLogger getInstance() {
        if (instance == null) {
            instance = new AppLogger();
        }
        return instance;
    }

    public void log(String level, String message) {
        String timestamp = LocalDateTime.now().toString();
        String log = String.format("[%s] [%s]: %s", timestamp, level, message);

        System.out.println(log);

        fileWriter.println(log);
    }

    @Override
    public void close() {
        fileWriter.close();
    }
}
