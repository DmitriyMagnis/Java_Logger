package org.example.app.controllers;

import org.example.app.utils.AppLogger;

public class LoggingController {
    public void run() {
        try (AppLogger logger = AppLogger.getInstance()) {
            logger.log("INFO", "Starting program.");

            try {
                loggingTask(logger);
            } catch (Exception e) {
                logger.log("ERROR", "ERROR occurs: " + e.getMessage());
            }

            logger.log("INFO", "Program finishes work.");
        }
    }
    private void loggingTask(AppLogger logger) throws Exception {
        logger.log("DEBUG", "Start logging...");

        for (int i = 1; i < 5; i++) {
            logger.log("LOG", "Step " + i);
            Thread.sleep(200);
        }

        logger.log("DEBUG", "Logging finished.");
    }
}
