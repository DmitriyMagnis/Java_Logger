package org.example.app.controllers;

import org.example.app.utils.AppLogger;

public class LoggingController {
    public void run() {
        try (AppLogger logger = AppLogger.getInstance()) {
            logger.log("INFO", "Starting program.");

            try {
                loggingTask();
            } catch (Exception e) {
                logger.log("ERROR", "ERROR occurs: " + e.getMessage());
            }

            logger.log("INFO", "Program finishes work.");
        }
    }
    private void loggingTask() throws Exception {
        AppLogger taskLogger = AppLogger.getInstance();
        taskLogger.log("DEBUG", "Start logging...");

        for (int i = 1; i < 5; i++) {
            taskLogger.log("LOG", "Step " + i);
            Thread.sleep(200);
        }

        taskLogger.log("DEBUG", "Logging finished.");
    }
}
