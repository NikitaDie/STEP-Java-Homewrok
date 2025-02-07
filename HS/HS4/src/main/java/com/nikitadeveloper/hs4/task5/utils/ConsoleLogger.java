package com.nikitadeveloper.hs4.task5.utils;

import org.slf4j.LoggerFactory;

public class ConsoleLogger {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ConsoleLogger.class);

    private ConsoleLogger() {
    }

    public static void logError(String message, Exception e) {
        String coloredMessage = ConsoleColorFormatter.formatText(message, ConsoleColorFormatter.RED);
        logger.error(coloredMessage, e);
    }

    public static void logError(String message) {
        String coloredMessage = ConsoleColorFormatter.formatText(message, ConsoleColorFormatter.RED);
        logger.error(coloredMessage);
    }

    public static void logInfo(String message) {
        String coloredMessage = ConsoleColorFormatter.formatText(message, ConsoleColorFormatter.GREEN);
        logger.info(coloredMessage);
    }

    public static void logWarning(String message) {
        String coloredMessage = ConsoleColorFormatter.formatText(message, ConsoleColorFormatter.YELLOW);
        logger.warn(coloredMessage);
    }

    public static void printResponse(String message) {
        String coloredMessage = ConsoleColorFormatter.formatText(message, ConsoleColorFormatter.BLUE);
        System.out.println(coloredMessage);
    }

    public static void printProgramMessage(String message) {
        String coloredMessage = ConsoleColorFormatter.formatText(message, ConsoleColorFormatter.PURPLE);
        System.out.println(coloredMessage);
    }
}
