package org.example.app.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AppLoggerTest {
    private static final String LOG_FILE_PATH = "src/main/resources/app.log";

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(Paths.get(LOG_FILE_PATH));
    }
    @AfterEach
    void deleteLogFiles() {
        AppLogger.closeInstance();
    }

    @Test
    void testSingleton() {
        AppLogger logger1 = AppLogger.getInstance();
        AppLogger logger2 = AppLogger.getInstance();
        assertSame(logger1, logger2, "getInstance() returns the same instance.");
    }
    @Test
    void testLogging() throws IOException {
        try (AppLogger logger = AppLogger.getInstance()) {
            logger.log("INFO", "First message");
            logger.log("DEBUG", "Second message");
            logger.log("ERROR", "Third message");

            assertTrue(Files.exists(Paths.get(LOG_FILE_PATH)), "Log file should be created.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String firstLine = reader.readLine();
            String secondLine = reader.readLine();
            String thirdLine = reader.readLine();

            assertNotNull(firstLine, "First message is in file.");
            assertTrue(firstLine.contains("First message"), "First message contains correct text.");

            assertNotNull(secondLine, "Second message is in file.");
            assertTrue(secondLine.contains("Second message"), "Second message contains correct text.");

            assertNotNull(thirdLine, "Third message is in file.");
            assertTrue(thirdLine.contains("Third message"), "Third message contains correct text.");
        }
    }
}
